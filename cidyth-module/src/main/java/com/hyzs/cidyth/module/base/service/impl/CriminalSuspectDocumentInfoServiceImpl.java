package com.hyzs.cidyth.module.base.service.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.module.UnSafePair;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.HttpUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.service.CriminalSuspectDocumentInfoService;
import com.hyzs.cidyth.module.base.vo.CriminalSuspectDocument;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.jdbc.SQL;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 犯罪嫌疑人档案信息服务
 * 
 * @author derrick
 *
 */
@Service("criminalSuspectDocumentInfoService")
public class CriminalSuspectDocumentInfoServiceImpl implements CriminalSuspectDocumentInfoService {
	private static final Logger logger = LoggerFactory.getLogger(CriminalSuspectDocumentInfoServiceImpl.class);
	private final static ObjectMapper jsonParser = new ObjectMapper();
	private final JacksonUtils jacksonUtils = new JacksonUtils();
	private static final String DEFALUT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private final String[] SUPPORTED_DATE_TIMEP_ATTERNS = new String[] { "yyyyMMddHHmmssSSS", "yyyyMMddHHmmss",
			DEFALUT_DATE_PATTERN, "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "HH:mm:ss.SSS", "HH:mm:ss", "HH:mm",
			"yyyyMMdd", "HHmmssSSS", "HHmmss", "HHmm" };

	/**
	 * 服务连接配置信息
	 * 
	 * @author Administrator
	 *
	 */
	private static final class RemoteServiceConnectionConfig {
		public static final Map.Entry<String, String> USER_IDENTIFICATION = new Map.Entry<String, String>() {
			@Override
			public String getKey() {
				return "REQ_USER_KEY";
			}

			@Override
			public String getValue() {
				return "fb654b12086a4893215c7d532fe72aea";
			}

			@Override
			public String setValue(String value) {
				return null;
			}
		};
		public static final Map.Entry<String, Integer> REMOTE_SERVER_INFO = new Map.Entry<String, Integer>() {
			@Override
			public String getKey() {
				return "68.64.17.43";
			}

			@Override
			public Integer getValue() {
				return 7080;
			}

			@Override
			public Integer setValue(Integer value) {
				return null;
			}
		};
	}

	/**
	 * 请求总类，TABLE or TABLEFIELDS
	 * 
	 * @author Administrator
	 *
	 */
	private static enum RequestSummary {
		TABLE, TABLEFIELDS;
	}

	/**
	 * 请求头key
	 * 
	 * @author Administrator
	 *
	 */
	private static final class RequestHeaderKey {
		/** 请求总类key **/
		public static final String REQ_METHOD = "REQ_METHOD";
		/*** 请求查询字段key *****/
		public static final String REQ_FIELDS = "REQ_FIELDS";

		/*** 请求交易类型key ****/
		public static final String TRANS_TYPE = "REQ_TRANS_TYPE";

		/*** 请求查询每页记录数 *****/
		public static final String REQ_RECORDS_NUM = "REQ_RECORDS_NUM";
		/*** 请求查询当前页码 *****/
		public static final String REQ_CURRENT_PAGE = "REQ_CURRENT_PAGE";
	}

	/**
	 * 请求参数
	 * 
	 * @author Administrator
	 *
	 */
	private static final class RequestParam {
		private SQL sql = new SQL();

		public RequestParam() {
			sql = new SQL();
			sql.SELECT("1").FROM("DUAL");
		}

		public RequestParam and(String paramName, String operator, String paramValue) {
			if (StringUtils.isNotBlank(paramName) && StringUtils.isNotBlank(operator)
					&& StringUtils.isNotBlank(paramValue)) {
				if (sql.toString().contains("WHERE")) {
					sql.AND().WHERE(paramName + operator + paramValue);
				} else {
					sql.WHERE(paramName + operator + paramValue);
				}
			} else {
				logger.error("参数名、运算符、参数值都不能为空!");
			}
			return this;
		}

		public RequestParam or(String paramName, String operator, String paramValue) {
			if (StringUtils.isNotBlank(paramName) && StringUtils.isNotBlank(operator)
					&& StringUtils.isNotBlank(paramValue)) {
				if (sql.toString().contains("WHERE")) {
					sql.OR().WHERE(paramName + operator + paramValue);
				} else {
					sql.WHERE(paramName + operator + paramValue);
				}
			} else {
				logger.error("参数名、运算符、参数值都不能为空!");
			}

			return this;
		}

		public String dump() {
			String str = "";
			int idx = sql.toString().indexOf("WHERE");
			if (idx >= 0) {
				str = sql.toString().substring(idx + "WHERE".length());
			}
			logger.debug(str);
			return str;
		}

		/**
		 * 构建请求参数
		 * 
		 * @param sqlSegment
		 *            sql条件，例如：NAME=abc OR ZJHM=32131231231
		 * @param charset
		 *            编码集
		 * @return
		 */
		public byte[] toByte(Charset charset) {
			return dump().getBytes(charset == null ? Charset.forName("UTF-8") : charset);
		}

		public byte[] toByte() {
			return dump().getBytes(Charset.forName("UTF-8"));
		}

		public byte[] toByte(String content, Charset charset) {
			logger.debug(content);
			return (StringUtils.isBlank(content)) ? new byte[] {}
					: content.getBytes(charset == null ? Charset.forName("UTF-8") : charset);
		}

		public byte[] toByte(String content) {
			return toByte(content, null);
		}
	}

	/**
	 * 响应结果
	 * 
	 * @author Administrator
	 *
	 */
	private static final class ResponseResult {
		private String code;
		private String body;

		public ResponseResult(com.squareup.okhttp.Response response) {
			if (response != null) {
				code = response.header(RESPONSE_CODE);
				if (response.body() != null) {
					InputStream inputStream = null;
					ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
					int ch;
					try {
						inputStream = response.body().byteStream();
						while ((ch = inputStream.read()) != -1) {
							bytestream.write(ch);
						}
						byte data[] = bytestream.toByteArray();
						if (data != null) {
							body = new String(data, Charset.forName("UTF-8"));
						}
					} catch (Exception e) {
						logger.error("Response error: " + e.getMessage());
						e.printStackTrace();
					} finally {
						IOUtils.closeQuietly(bytestream);
						IOUtils.closeQuietly(inputStream);
					}
				}
			}
		}

		public boolean isSuccess() {
			return StringUtils.isNotBlank(this.code) && "200".equals(this.code);
		}

		public String getCode() {
			return this.code;
		}

		public String getMessage() {
			String msg = "";
			if (StringUtils.isNotBlank(this.code)) {
				int c = Integer.parseInt(this.code);
				switch (c) {
				case 200:
					msg = "成功";
					break;
				case 500:
					msg = "系统异常";
					break;
				case 300:
					msg = "非法交易类型";
					break;
				case 310:
					msg = "无效的请求";
					break;
				case 320:
					msg = "无效的用户";
					break;
				case 330:
					msg = "未授权用户";
					break;
				case 340:
					msg = "无效的参数错误";
					break;
				}
			}
			return msg;
		}

		public String getBody() {
			return this.body;
		}

		private static final String RESPONSE_CODE = "RESPONSE_CODE";
		private static final String RESPONSE_BODY = "RESPONSE_BODY";
	}

	public CriminalSuspectDocumentInfoServiceImpl() {
		jsonParser.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	private static String buildUrl(String urlSuffix) {
		if (StringUtils.isBlank(urlSuffix))
			throw new ServiceException("Url 后缀不合法!");
		StringBuilder url = new StringBuilder();
		return url.append("http://").append(RemoteServiceConnectionConfig.REMOTE_SERVER_INFO.getKey()).append(":")
				.append(RemoteServiceConnectionConfig.REMOTE_SERVER_INFO.getValue()).append(urlSuffix).toString();
	}

	/**
	 * @param pageNo
	 *            分页当前页码
	 * @param pageSize
	 *            分页每页记录数
	 * @param requestSummary
	 *            请求总类{@RequestSummary}
	 * @param columns
	 *            要返回的表的列名
	 * @param transType
	 *            交易类型代码:<br/>
	 *            深圳市技侦采集全国顺丰快递信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30877<br/>
	 *            深圳市出租屋人口有效登记表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30041<br/>
	 *            深圳市国内旅客旅馆酒店登记信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30595<br/>
	 *            深圳市出入境出境申请信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30743<br/>
	 *            广东省广铁集团售票信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30793<br/>
	 *            全国航空订座信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30866<br/>
	 *            广东省民航进港旅客信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30814<br/>
	 *            广东省民航订座出港旅客信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30811<br/>
	 *            深圳市警综嫌疑人基本信息表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30187<br/>
	 *
	 * @return
	 */
	private Map<String, String> buildHeader(int pageNo, int pageSize, RequestSummary requestSummary,
			List<String> columns, String transType) {
		Map<String, String> header = new HashMap<String, String>();
		if (requestSummary == null)
			throw new ServiceException("请求总类不能为空!");
		header.put(RequestHeaderKey.REQ_METHOD, requestSummary.name());

		// 该属性可以不用设置，如果不设置的话默认查询所有字段
		StringBuilder reqFields = new StringBuilder();
		if (CollectionUtils.isNotEmpty(columns)) {
			Joiner.on(",").appendTo(reqFields, columns);
		}
		header.put(RequestHeaderKey.REQ_FIELDS, reqFields.toString());

		if (StringUtils.isNotBlank(transType)) {
			header.put(RequestHeaderKey.TRANS_TYPE, transType);
		} else {
			throw new ServiceException("交易类型代码不能为空!");
		}
		header.put(RemoteServiceConnectionConfig.USER_IDENTIFICATION.getKey(),
				RemoteServiceConnectionConfig.USER_IDENTIFICATION.getValue());
		if (pageNo > 0 && pageSize > 0) {
			header.put(RequestHeaderKey.REQ_CURRENT_PAGE, pageNo + "");
			header.put(RequestHeaderKey.REQ_RECORDS_NUM, pageSize + "");
		}
		return header;
	}

	private Map<String, String> buildHeader(RequestSummary requestSummary, List<String> columns, String transType) {
		return this.buildHeader(-1, -1, requestSummary, columns, transType);
	}

	@Override
	public Map<String, Object> loadCriminalSuspectBaseInfo(CriminalSuspectDocument csd) throws Exception {
		Map<String, Object> result = null;
		if (csd == null)
			return result;
		byte[] requestParam = new RequestParam().and("XM", "=", csd.getName()).and("ZJHM", "=", csd.getIdCardNo())
				.toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30187");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				final UnSafePair<Long, Map<String, Object>> latest = new UnSafePair<Long, Map<String, Object>>();
				List<Map<String, Object>> datas = jacksonUtils.deserailize(rootNode.path("data"),
						new JacksonUtils.JsonNode2MapConvertor() {
							@Override
							public Map<String, Object> convert(JsonNode node) {
								Map<String, Object> map = new HashMap<String, Object>();
								JsonNode name = node.path("xm");// 姓名
								map.put("xm", name == null ? "" : name.asText());

								JsonNode gender = node.path("xb");// 性别
								String xb = "";
								String xb_CN = "未知";
								if (gender != null && StringUtils.isNotBlank(gender.asText())) {
									xb = gender.asText();
									if ("1".equals(gender.asText())) {
										xb_CN = "男";
									}
									if ("2".equals(gender.asText())) {
										xb_CN = "女";
									}
								}
								map.put("xb", xb);
								map.put("xb_CN", xb_CN);

								JsonNode birthday = node.path("csrq");// 出生日期
								if (birthday != null && StringUtils.isNotBlank(birthday.asText())) {
									map.put("csrq", birthday.asText());
								} else {
									map.put("csrq", "");
								}
								JsonNode idCardNo = node.path("zjhm");// 证件号码
								if (idCardNo != null && StringUtils.isNotBlank(idCardNo.asText())) {
									map.put("zjhm", idCardNo.asText());
								} else {
									map.put("zjhm", "");
								}

								JsonNode phone = node.path("lxdh");// 联系电话
								if (phone != null && StringUtils.isNotBlank(phone.asText())) {
									map.put("lxdh", phone.asText());
								} else {
									map.put("lxdh", "");
								}

								JsonNode hjdz = node.path("hjdz");// 户籍地址
								if (hjdz != null && StringUtils.isNotBlank(hjdz.asText())) {
									map.put("hjdz", hjdz.asText());
								} else {
									map.put("hjdz", "");
								}

								JsonNode xxdzms = node.path("xxdzms");// 现住地址
								if (xxdzms != null && StringUtils.isNotBlank(xxdzms.asText())) {
									map.put("xxdzms", xxdzms.asText());
								} else {
									map.put("xxdzms", "");
								}
								JsonNode gzdw = node.path("gzdw");// 工作单位
								if (gzdw != null && StringUtils.isNotBlank(gzdw.asText())) {
									map.put("gzdw", gzdw.asText());
								} else {
									map.put("gzdw", "");
								}
								JsonNode id = node.path("_id");// id
								if (id != null && StringUtils.isNotBlank(id.asText())) {
									map.put("_id", id.asText());
								} else {
									map.put("_id", "");
								}

								map.put("photo", "");// 照片

								JsonNode ludt = node.path("lastupdatedtime");// 最后更新时间
								if (ludt != null && StringUtils.isNoneBlank(ludt.asText())) {
									Date date = DateUtil.parse2Date(node.path("lastupdatedtime").asText(),
											SUPPORTED_DATE_TIMEP_ATTERNS);
									if (date != null) {
										Long le = latest.getLeft();
										if (le == null || le.longValue() < date.getTime()) {
											latest.setLeft(date.getTime());
											latest.setRight(map);
										}
									}
								}
								return map;
							}
						});
				datas.clear();
				result = latest.getRight();
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadRelatedPerson(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadCriminalHistoryInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadPhysicalDistributionInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		StringBuilder sql = new StringBuilder();
		String name = csd.getName();
		String phone = csd.getPhone();
		if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(phone)) {
			sql.append("(");
			if (StringUtils.isNotBlank(name)) {
				sql.append("SENNAME=").append(name).append(" AND ");
			}
			if (StringUtils.isNotBlank(phone)) {
				sql.append("SENMOBILE=").append(phone).append(" OR ").append("SENPHONE=").append(phone);
			}
			String[] replacements = new String[] { "AND", "OR" };
			if (StringUtils.endsWithAny(sql.toString().trim(), replacements)) {
				for (int j = 0; j < replacements.length; j++) {
					String replace = replacements[j];
					int idx = sql.toString().indexOf(replace);
					sql.delete(idx, sql.toString().length());
					break;
				}
			}
			sql.append(")");
			sql.append(" OR ");
			sql.append("(");
			if (StringUtils.isNotBlank(name)) {
				sql.append("RECNAME=").append(name).append(" AND ");
			}
			if (StringUtils.isNotBlank(phone)) {
				sql.append("RECMOBILE=").append(phone).append(" OR ").append("RECPHONE=").append(phone);
			}
			if (StringUtils.endsWithAny(sql.toString().trim(), replacements)) {
				for (int j = 0; j < replacements.length; j++) {
					String replace = replacements[j];
					int idx = sql.toString().indexOf(replace);
					sql.delete(idx, sql.toString().length());
					break;
				}
			}
			sql.append(")");
		}
		byte[] requestParam = new RequestParam().toByte(sql.toString());
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30877");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				List<Map<String,Object>> datas = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("mailno") != null) {
							map.put("mailno", node.path("mailno").asText());// 运单号码
						} else {
							map.put("mailno", "");
						}
						if (node.path("nameofcoutents") != null) {
							map.put("nameofcoutents", node.path("nameofcoutents").asText());// 物品名称
						} else {
							map.put("nameofcoutents", "");
						}
						if (node.path("senname") != null) {
							map.put("senname", node.path("senname").asText());// 寄件人姓名
						} else {
							map.put("senname", "");
						}
						if (node.path("senmobile") != null) {
							map.put("senmobile", node.path("senmobile").asText());// 寄件人电话
						} else {
							map.put("senmobile", "");
						}
						if (node.path("senphone") != null) {
							map.put("senphone", node.path("senphone").asText());// 寄件人固定电话
						} else {
							map.put("senphone", "");
						}
						if (node.path("senprovcode") != null) {
							map.put("senprovcode", node.path("senprovcode").asText());// 寄件地址省份
						} else {
							map.put("senprovcode", "");
						}
						if (node.path("sencountycode") != null) {
							map.put("sencountycode", node.path("sencountycode").asText());// 寄件地址区县
						} else {
							map.put("sencountycode", "");
						}
						if (node.path("senaddress") != null) {
							map.put("senaddress", node.path("senaddress").asText());// 寄件详细地址
						} else {
							map.put("senaddress", "");
						}

						if (node.path("recname") != null) {
							map.put("recname", node.path("recname").asText());// 收件人姓名
						} else {
							map.put("recname", "");
						}

						if (node.path("recmobile") != null) {
							map.put("recmobile", node.path("recmobile").asText());// 收件人手机
						} else {
							map.put("recmobile", "");
						}
						if (node.path("recphone") != null) {
							map.put("recphone", node.path("recphone").asText());// 收件人固定电话
						} else {
							map.put("recphone", "");
						}
						if (node.path("recdatetime") != null) {
							map.put("recdatetime", node.path("recdatetime").asText());// 收件人签收时间
						} else {
							map.put("recdatetime", "");
						}

						if (node.path("recprovcode") != null) {
							map.put("recprovcode", node.path("recprovcode").asText());// 收件地址省份
						} else {
							map.put("recprovcode", "");
						}
						if (node.path("reccitycode") != null) {
							map.put("reccitycode", node.path("reccitycode").asText());// 收件城市代码
						} else {
							map.put("reccitycode", "");
						}
						if (node.path("reccountycode") != null) {
							map.put("reccountycode", node.path("reccountycode").asText());// 收件地址区县
						} else {
							map.put("reccountycode", "");
						}

						if (node.path("recaddress") != null) {
							map.put("recaddress", node.path("recaddress").asText());// 收货详细地址
						} else {
							map.put("recaddress", "");
						}
						return map;
					}
				});
				result = ImmutablePair.of(total, datas);
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadRentalsInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		String name = csd.getName();
		String idCardNo = csd.getIdCardNo();
		byte[] requestParam = new RequestParam().and("NAME", "=", name).and("CARDNO", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30041");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				List<Map<String, Object>> datas = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("checkindate") != null) {
							map.put("checkindate", node.path("checkindate").asText());// 入住时间
						} else {
							map.put("checkindate", "");
						}
						if (node.path("leavedate") != null) {
							map.put("leavedate", node.path("leavedate").asText());// 离开日期
						} else {
							map.put("leavedate", "");
						}
						if (node.path("homeaddress") != null) {
							map.put("homeaddress", node.path("homeaddress").asText());// 居住房屋地址
						} else {
							map.put("homeaddress", "");
						}

						// -------------------------------缺少的数据---------------------------------//
						if (node.path("togetherPsnName") != null) {
							map.put("togetherPsnName", node.path("togetherPsnName").asText());// 同住人姓名
						} else {
							map.put("togetherPsnName", "");
						}
						if (node.path("togetherPsnIdCardNo") != null) {
							map.put("togetherPsnIdCardNo", node.path("togetherPsnIdCardNo").asText());// 同住人身份证
						} else {
							map.put("togetherPsnIdCardNo", "");
						}
						if (node.path("togetherPsnPhone") != null) {
							map.put("togetherPsnPhone", node.path("togetherPsnPhone").asText());// 同住人手机号码
						} else {
							map.put("togetherPsnPhone", "");
						}
						return map;
					}
				});
				result = ImmutablePair.of(total, datas);
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>>  loadHotelInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		String name = csd.getName();
		String idCardNo = csd.getIdCardNo();
		byte[] requestParam = new RequestParam().and("NAME", "=", name).and("IDCODE", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30595");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				List<Map<String, Object>> datas = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("ccode") != null) {
							map.put("ccode", node.path("ccode").asText());// 旅客编号
						} else {
							map.put("ccode", "");
						}
						if (node.path("ltime") != null) {// 入住时间
							Date date = DateUtil.parse2Date(node.path("ltime").asText(), SUPPORTED_DATE_TIMEP_ATTERNS);
							if (date != null) {
								map.put("ltime", DateUtil.formatDate(date, DEFALUT_DATE_PATTERN));
							} else {
								map.put("ltime", "");
							}
						} else {
							map.put("ltime", "");
						}
						if (node.path("etime") != null) {
							Date date = DateUtil.parse2Date(node.path("etime").asText(), SUPPORTED_DATE_TIMEP_ATTERNS);
							if (date != null) {
								map.put("etime", DateUtil.formatDate(date, DEFALUT_DATE_PATTERN));// 退房时间
							} else {
								map.put("etime", "");
							}
						} else {
							map.put("etime", "");
						}
						if (node.path("nohotel") != null) {
							map.put("nohotel", node.path("nohotel").asText());// 入住旅馆
						} else {
							map.put("nohotel", "");
						}
						if (node.path("nohotel-idofy") != null) {
							map.put("nohotel-idofy", node.path("nohotel-idofy").asText());// 旅馆名称
						} else {
							map.put("nohotel-idofy", "");
						}
						if (node.path("noroom") != null) {
							map.put("noroom", node.path("noroom").asText());// 入住房号
						} else {
							map.put("noroom", "");
						}

						// -------------------------------缺少的数据---------------------------------//
						if (node.path("togetherPsnName") != null) {
							map.put("togetherPsnName", node.path("togetherPsnName").asText());// 同住人姓名
						} else {
							map.put("togetherPsnName", "");
						}
						if (node.path("togetherPsnIdCardNo") != null) {
							map.put("togetherPsnIdCardNo", node.path("togetherPsnIdCardNo").asText());// 同住人身份证
						} else {
							map.put("togetherPsnIdCardNo", "");
						}
						if (node.path("togetherPsnPhone") != null) {
							map.put("togetherPsnPhone", node.path("togetherPsnPhone").asText());// 同住人手机号码
						} else {
							map.put("togetherPsnPhone", "");
						}
						return map;
					}
				});
				result = ImmutablePair.of(total, datas);
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadEntryAndExistInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		String name = csd.getName();
		String idCardNo = csd.getIdCardNo();
		byte[] requestParam = new RequestParam().and("ZWXM", "=", name).and("SFZH", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30743");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				List<Map<String, Object>> datas = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("sldw") != null) {
							map.put("sldw", node.path("sldw").asText());// 受理单位
						} else {
							map.put("sldw", "");
						}
						if (node.path("slrq") != null) {// 受理日期
							Date date = DateUtil.parse2Date(node.path("slrq").asText(), SUPPORTED_DATE_TIMEP_ATTERNS);
							if (date != null) {
								map.put("slrq", DateUtil.formatDate(date, DEFALUT_DATE_PATTERN));
							} else {
								map.put("slrq", "");
							}
						} else {
							map.put("slrq", "");
						}
						if (node.path("qwd") != null) {
							map.put("qwd", node.path("qwd").asText());// 去往地
						} else {
							map.put("qwd", "");
						}

						// -------------------------------缺少的数据---------------------------------//
						if (node.path("togetherPsnName") != null) {
							map.put("togetherPsnName", node.path("togetherPsnName").asText());// 同行人姓名
						} else {
							map.put("togetherPsnName", "");
						}
						if (node.path("togetherPsnIdCardNo") != null) {
							map.put("togetherPsnIdCardNo", node.path("togetherPsnIdCardNo").asText());// 同行人身份证
						} else {
							map.put("togetherPsnIdCardNo", "");
						}
						if (node.path("togetherPsnPhone") != null) {
							map.put("togetherPsnPhone", node.path("togetherPsnPhone").asText());// 同行人手机号码
						} else {
							map.put("togetherPsnPhone", "");
						}
						return map;
					}
				});
				result = ImmutablePair.of(total, datas);
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	private ImmutablePair<Long, List<Map<String, Object>>> doLoadGuangdongCivilAirLineInfo(String name, String idCardNo,
			String transType) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		byte[] requestParam = new RequestParam().and("CHINESE_NAME", "=", name).and("FOID", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		if (!StringUtils.equalsAny(transType, "30814", "30811")) {// 进港,出港
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, transType);
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				List<Map<String, Object>> datas = jacksonUtils.deserailize(rootNode.path("data"),
						new JacksonUtils.JsonNode2MapConvertor() {
							@Override
							public Map<String, Object> convert(JsonNode node) {
								Map<String, Object> map = new HashMap<String, Object>();
								if (node.path("flight") != null) {
									map.put("flight", node.path("flight").asText());// 航班号
								} else {
									map.put("flight", "");
								}
								if (node.path("offday") != null) {
									map.put("offday", node.path("offday").asText());// 航班日期
								} else {
									map.put("offday", "");
								}
								if (node.path("nation") != null) {
									map.put("nation", node.path("nation").asText());// 国籍
								} else {
									map.put("nation", "");
								}
								if (node.path("strt") != null) {
									map.put("strt", node.path("strt").asText());// 始发港
								} else {
									map.put("strt", "");
								}
								if (node.path("dest") != null) {
									map.put("dest", node.path("dest").asText());// 到达港
								} else {
									map.put("dest", "");
								}
								if (node.path("depttm") != null) {
									map.put("depttm", node.path("depttm").asText());// 始发时间
								} else {
									map.put("depttm", "");
								}
								if (node.path("arvetm") != null) {
									map.put("arvetm", node.path("arvetm").asText());// 到达时间
								} else {
									map.put("arvetm", "");
								}
								if (node.path("class") != null) {
									map.put("class", node.path("class").asText());// 舱位
								} else {
									map.put("class", "");
								}
								if (node.path("pnr") != null) {
									map.put("pnr", node.path("pnr").asText());// 订座编号
								} else {
									map.put("pnr", "");
								}
								return map;
							}
						});
				result = ImmutablePair.of(total, datas);
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	/**
	 * 广东省民航【进港/出港】旅客信息
	 * 
	 * @param name
	 * @param idCardNo
	 * @return
	 * @throws Exception
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadGuangdongCivilAirLineInfo(String name, String idCardNo) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, new ArrayList<Map<String,Object>>());
		long total = 0;
		List<Map<String, Object>> datas = Lists.<Map<String, Object>>newArrayList();
		if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(idCardNo)) {
			ImmutablePair<Long, List<Map<String, Object>>> entryAirLineInfo = this.doLoadGuangdongCivilAirLineInfo(name,
					idCardNo, "30814");

			if (entryAirLineInfo != null && entryAirLineInfo.getRight() != null
					&& !entryAirLineInfo.getRight().isEmpty()) {
				total += entryAirLineInfo.getLeft();
				datas.addAll(entryAirLineInfo.getRight());
			}
			ImmutablePair<Long, List<Map<String, Object>>> existAirLineInfo = this.doLoadGuangdongCivilAirLineInfo(name,
					idCardNo, "30811");
			if (existAirLineInfo != null && existAirLineInfo.getRight() != null
					&& !existAirLineInfo.getRight().isEmpty()) {
				total += existAirLineInfo.getLeft();
				datas.addAll(existAirLineInfo.getRight());
			}
		}
		result = ImmutablePair.of(total, datas);
		return result;
	}

	/**
	 * 全国航空订座信息表
	 * 
	 * @param name
	 * @param idCardNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> loadNationwideAirLineInfo(String name, String idCardNo) throws Exception {
		List<Map<String, Object>> result = null;
		byte[] requestParam = new RequestParam().and("pas_chn_nm", "=", name).and("pas_id_nbr", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30866");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			logger.debug("response data: " + responseBody);
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				result = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("air_carr_cd") != null) {
							map.put("air_carr_cd", node.path("air_carr_cd").asText());// 航空公司代码
						} else {
							map.put("air_carr_cd", "");
						}
						if (node.path("rsp_airln_cd") != null) {
							map.put("rsp_airln_cd", node.path("rsp_airln_cd").asText());// 责任航空公司
						} else {
							map.put("rsp_airln_cd", "");
						}

						if (node.path("air_seg_flt_nbr") != null) {
							map.put("air_seg_flt_nbr", node.path("air_seg_flt_nbr").asText());// 航班号
						} else {
							map.put("air_seg_flt_nbr", "");
						}

						if (node.path("air_seg_dpt_dt_lcl") != null) {
							map.put("air_seg_dpt_dt_lcl", node.path("air_seg_dpt_dt_lcl").asText());// 当地出发日期
						} else {
							map.put("air_seg_dpt_dt_lcl", "");
						}
						if (node.path("air_seg_dpt_airpt_cd") != null) {
							map.put("air_seg_dpt_airpt_cd", node.path("air_seg_dpt_airpt_cd").asText());// 登机机场代码
						} else {
							map.put("air_seg_dpt_airpt_cd", "");
						}

						if (node.path("air_seg_arrv_airpt_cd") != null) {
							map.put("air_seg_arrv_airpt_cd", node.path("air_seg_arrv_airpt_cd").asText());// 到达机场代码
						} else {
							map.put("air_seg_arrv_airpt_cd", "");
						}
						if (node.path("sub_cls_cd") != null) {
							map.put("sub_cls_cd", node.path("sub_cls_cd").asText());// 子舱位
						} else {
							map.put("sub_cls_cd", "");
						}
						if (node.path("ffp_id_nbr") != null) {
							map.put("ffp_id_nbr", node.path("ffp_id_nbr").asText());// 常旅客编号
						} else {
							map.put("ffp_id_nbr", "");
						}
						return map;
					}
				});
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	/**
	 * 广东省广铁集团售票信息
	 * 
	 * @param name
	 * @param idCardNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> loadGuangDongRailwayInfo(String name, String idCardNo) throws Exception {
		List<Map<String, Object>> result = null;
		byte[] requestParam = new RequestParam().and("ID_NAME", "=", name).and("ID_NO", "=", idCardNo).toByte();
		if (requestParam.length == 0) {
			return result;
		}
		Map<String, String> httpHeader = this.buildHeader(RequestSummary.TABLE, null, "30793");
		com.squareup.okhttp.Response response = new HttpUtil().postByte(this.buildUrl("/idoop-cpkj/IDoopDataPortal"),
				requestParam, null, null, httpHeader);
		ResponseResult responseResult = new ResponseResult(response);
		if (responseResult.isSuccess()) {
			String responseBody = responseResult.getBody();
			logger.debug("response data: " + responseBody);
			JsonNode rootNode = jsonParser.readTree(responseBody).path("data");
			if (rootNode != null) {
				long total = rootNode.path("total").asLong();
				result = jacksonUtils.deserailize(rootNode.path("data"), new JacksonUtils.JsonNode2MapConvertor() {
					@Override
					public Map<String, Object> convert(JsonNode node) {
						Map<String, Object> map = new HashMap<String, Object>();
						if (node.path("board_train_code") != null) {
							map.put("board_train_code", node.path("board_train_code").asText());// 车次
						} else {
							map.put("board_train_code", "");
						}
						if (node.path("train_date") != null) {
							map.put("train_date", node.path("train_date").asText());// 乘车日期
						} else {
							map.put("train_date", "");
						}

						if (node.path("from_station_name") != null) {
							map.put("from_station_name", node.path("from_station_name").asText());// 出发车站
						} else {
							map.put("from_station_name", "");
						}

						if (node.path("to_station_name") != null) {
							map.put("to_station_name", node.path("to_station_name").asText());// 到达车站
						} else {
							map.put("to_station_name", "");
						}

						if (node.path("coach_no") != null) {
							map.put("coach_no", node.path("coach_no").asText());// 车厢
						} else {
							map.put("coach_no", "");
						}
						if (node.path("seat_no") != null) {
							map.put("seat_no", node.path("seat_no").asText());// 席位
						} else {
							map.put("seat_no", "");
						}

						if (node.path("sale_time") != null) {
							map.put("sale_time", node.path("sale_time").asText());// 售票时间
						} else {
							map.put("sale_time", "");
						}
						if (node.path("office_no") != null) {
							map.put("office_no", node.path("office_no").asText());// 售出车站代码
						} else {
							map.put("office_no", "");
						}

						if (node.path("window_no") != null) {
							map.put("window_no", node.path("window_no").asText());// 售票窗口号
						} else {
							map.put("window_no", "");
						}

						if (node.path("ticket_no") != null) {
							map.put("ticket_no", node.path("ticket_no").asText());// 票号
						} else {
							map.put("ticket_no", "");
						}

						return map;
					}
				});
			}
		} else {
			logger.error("{}", responseResult.getMessage());
		}
		return result;
	}

	@Override
	public ImmutablePair<Long, List<Map<String, Object>>> loadTravelPathInfo(CriminalSuspectDocument csd) throws Exception {
		ImmutablePair<Long, List<Map<String, Object>>> result = ImmutablePair.of(0L, null);
		if (csd == null)
			return result;
		String name = csd.getName();
		String idCardNo = csd.getIdCardNo();
		return result;
	}

	public static void main(String[] args) {
		CriminalSuspectDocument csd = new CriminalSuspectDocument();
		csd.setName("何敏");
		csd.setIdCardNo("522123198611035027");
		csd.setPhone("");
		// System.out.println(new RequestParam().or("SENNAME", "=",
		// "孙克").or("SENMOBILE", "=", "17751032094")
		// .or("SENPHONE", "=", "17751032094").or("RECNAME", "=",
		// "孙克").or("RECMOBILE", "=", "17751032094")
		// .or("RECPHONE", "=", "17751032094").dump());

		CriminalSuspectDocumentInfoService service = new CriminalSuspectDocumentInfoServiceImpl();
		try {
			Map<String, Object> inso = service.loadCriminalSuspectBaseInfo(csd);
			System.out.println("嫌疑人基本信息：" + jsonParser.writeValueAsString(inso));

			 ImmutablePair<Long,List<Map<String,Object>>> relatedPsn = service.loadRelatedPerson(csd);
			System.out.println("关系人：" + jsonParser.writeValueAsString(relatedPsn.getRight()));

			 ImmutablePair<Long,List<Map<String,Object>>> criminalHistory = service.loadCriminalHistoryInfo(csd);
			System.out.println("犯罪历史：" + jsonParser.writeValueAsString(criminalHistory.getRight()));

			ImmutablePair<Long,List<Map<String,Object>>> physical = service.loadPhysicalDistributionInfo(csd);
			System.out.println("快递（物流）信息：" + jsonParser.writeValueAsString(physical.getRight()));

			ImmutablePair<Long,List<Map<String,Object>>> rental = service.loadRentalsInfo(csd);
			System.out.println("出租屋信息：" + jsonParser.writeValueAsString(rental.getRight()));

			ImmutablePair<Long,List<Map<String,Object>>> hotel = service.loadHotelInfo(csd);
			System.out.println("旅馆信息：" + jsonParser.writeValueAsString(hotel.getRight()));

			ImmutablePair<Long,List<Map<String,Object>>> entryAndExist = service.loadEntryAndExistInfo(csd);
			System.out.println("出入境信息：" + jsonParser.writeValueAsString(entryAndExist.getRight()));

			ImmutablePair<Long,List<Map<String,Object>>> travelPath = service.loadTravelPathInfo(csd);
			System.out.println("出行轨迹：" + jsonParser.writeValueAsString(travelPath.getRight()));

			// Map<String, Object> inso3 =
			// service.loadCriminalSuspectBaseInfo("徐健 ", "441721199103293031");
			// System.out.println(inso3);
			// List<Map<String, Object>> list = service.loadHotelInfo("余炳燊",
			// "");
			// System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}