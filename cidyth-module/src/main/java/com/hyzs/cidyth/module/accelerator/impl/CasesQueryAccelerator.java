package com.hyzs.cidyth.module.accelerator.impl;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.Term;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.utils.GenericBeanUtils;
import com.hyzs.cidyth.common.utils.GenericBeanUtils.ConvertedBeanPropertyValueResolver;
import com.hyzs.cidyth.module.accelerator.QueryAcceleratorPrepare;
import com.hyzs.cidyth.module.base.dao.CasesCidMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.psd.gafa.lucene.index.AbstractIndexBuilder.IndexWriteActionParam;
import com.hyzs.psd.gafa.lucene.search.impl.AbstractQueryTemplateService;

/**
 * 针对案件查询的加速服务
 * 
 * @author jidw
 *
 */
@Service("casesQueryAccelerator")
public class CasesQueryAccelerator extends AbstractQueryTemplateService<Cases> implements QueryAcceleratorPrepare{
	private static final Logger logger = LoggerFactory.getLogger(CasesQueryAccelerator.class);

	protected static final String DEFALUT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	protected final String[] SUPPORTED_DATE_TIMEP_ATTERNS = new String[] { "yyyyMMddHHmmssSSS", "yyyyMMddHHmmss",
			DEFALUT_DATE_PATTERN, "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "HH:mm:ss.SSS", "HH:mm:ss", "HH:mm",
			"yyyyMMdd", "HHmmssSSS", "HHmmss", "HHmm" };

	private static final int DATA_SYNCHRONOUS_DAYS = 30*6;// 180天之内

	@Autowired
	private CasesCidMapper casesCidMapper;

	@Override
	public String getIndexClassifyDir() {
		return "case";
	}
	@Override
	public String getBaseIndexDir() {
		String baseIndexDir=null;
		String webRoot = ContextUserHelper.getApplicationRootRealPath();
		if (Paths.get(webRoot).getParent().getParent() == null) {
			if (Paths.get(webRoot).getParent() == null) {
				baseIndexDir = Paths.get(webRoot).toString();
			} else {
				baseIndexDir = Paths.get(webRoot).getParent().toString();
			}
		} else {
			baseIndexDir = Paths.get(webRoot).getParent().getParent().toString();
		}
		return baseIndexDir;
	}
	/**
	 * 每周日凌晨1:30执行
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 30 1 0/3 * *")
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void prepare() throws Exception {
		super.createIndex();
	}
	
	@Override
	protected DataLoadParameter getDataLoadParameter(byte[] checkPoint, Charset currentCharset) throws Exception {
		SimpleDateFormat formmater = new SimpleDateFormat(DEFALUT_DATE_PATTERN);
		DateTime time = new DateTime();
		Date to = time.toDate();
		Date from = null;
		if (checkPoint != null && checkPoint.length > 0) {
			from = new Date(Long.parseLong(new String(checkPoint, currentCharset)));
		} else {
			long period=DATA_SYNCHRONOUS_DAYS;
			if(period>0){
				period=period*(-1);
			}
			from = time.plusDays(DATA_SYNCHRONOUS_DAYS).toDate();
		}
		DataLoadParameter param = new DataLoadParameter();
		param.put("fasjcz", formmater.format(from));
		param.put("fasjzz", formmater.format(to));
		param.setOrderBy(" FASJCZ ASC");
		return param;
	}

	@Override
	protected ImmutablePair<Long,List<Cases>> doLoadPagedData(DataLoadParameter parameter) throws Exception {
		PageHelper.startPage(parameter.getPageNumber(), parameter.getPageSzie()).setOrderBy(parameter.getOrderBy());;
		PageInfo<Cases> pageInfo = new PageInfo<Cases>(casesCidMapper.selectListPackCases(parameter.getAll()));
		ImmutablePair<Long,List<Cases>> datas = ImmutablePair.of(pageInfo.getTotal(), pageInfo.getList());
		return datas;
	}

	@Override
	protected void fillIndexData(Cases data, IndexWriteActionParam indexDataParam) throws Exception {
		Document doc = indexDataParam.getDoc();
		String ajbh = data.getAjbh();
		GenericBeanUtils.convertSimpleBean2Map(data, false, new ConvertedBeanPropertyValueResolver() {
			private long resolveDatePropertyValue(Object propertyValue) {
				if (propertyValue != null) {
					if (Date.class.isAssignableFrom(propertyValue.getClass())) {
						return ((Date) propertyValue).getTime();
					}
					if (String.class.isAssignableFrom(propertyValue.getClass())) {
						Date da = DateTimeTranslator.parse2Date((String) propertyValue, SUPPORTED_DATE_TIMEP_ATTERNS);
						if (da != null) {
							return da.getTime();
						}
					}
				}
				return -1;
			}

			@Override
			public Field resolvePropertyValue(String propertyName, Object propertyValue) {
				Field field = null;
				if ("fasjcz".equals(propertyName)) {// 案发开始时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("fasjzz".equals(propertyName)) {// 案发结束时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("sljjsj".equals(propertyName)) {// 受理接警时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("sl_slsj".equals(propertyName)) {// 受理时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("slfxrq".equals(propertyName)) {// 受理发现日期
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("lasj".equals(propertyName)) {//立案时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				} else if ("pasj".equals(propertyName)) {//破案时间
					field = new StoredField(propertyName, this.resolveDatePropertyValue(propertyValue));
				}else {
					field = new StringField(propertyName, propertyValue == null ? "" : propertyValue.toString(),
							Field.Store.YES);
				}
				if (field != null) {
					doc.add(field);
				}
				return field;
			}
		}, new String[] { "id", "shzt", "sfcba", "cbabh", "ajFrom", "mindTop", "mindLeft", "checkPerson", "checkStatus",
				"checkResult" });
		indexDataParam.setModifyRef(new Term("ajbh", ajbh));
	}
	/**
	 * 异常发生时，用发案开始时间作为断点
	 */
	@Override
	protected byte[] onExceptionCheckPoint(Cases dataOnException, Charset currentCharset, Exception exp) {
		logger.error("{}", exp);
		Date date = dataOnException.getFasjcz();
		if(date!=null){
			return currentCharset.encode(""+date.getTime()).array();
		}
		return null;
	}
}
