package com.hyzs.cidyth.module.base.service.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.utils.CasesRecordUtil;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.DepartmentUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesRecordMapper;
import com.hyzs.cidyth.module.base.entity.CasesRecord;
import com.hyzs.cidyth.module.base.entity.CasesRecordCid;
import com.hyzs.cidyth.module.base.entity.CasesRecordKL;
import com.hyzs.cidyth.module.base.service.CasesInformantCidService;
import com.hyzs.cidyth.module.base.service.CasesRecordCidService;
import com.hyzs.cidyth.module.base.service.CasesRecordService;
import com.hyzs.cidyth.module.base.service.CasesSuspectCidService;
import com.hyzs.cidyth.module.base.vo.CasesRecordKLParam;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesRecordService")
public class CasesRecordServiceImpl implements CasesRecordService {

	private static final Logger logger = LoggerFactory.getLogger(CasesRecordServiceImpl.class);

	@Autowired
	private CasesRecordMapper casesRecordMapper;
	@Autowired
	private CasesRecordCidService casesRecordCidService;
	@Autowired
	private CasesSuspectCidService casesSuspectCidService;
	@Autowired
	private CasesInformantCidService casesInformantCidService;

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		CasesRecordCid casesRecordCid = new CasesRecordCid();
		casesRecordCid.setAjbh(ajbh);
		List<CasesRecordCid> casesRecordResult = casesRecordCidService.listCasesRecord(casesRecordCid);
		// 从警综库查询到，则插入
		if(casesRecordResult != null && casesRecordResult.size() > 0){
			for(CasesRecordCid item : casesRecordResult) {
				if(!isExist(item.getSystemid())){
					CasesRecord casesRecord = new CasesRecord();
					BeanUtils.copyProperties(item, casesRecord);
					// 根据人员编号查找【嫌疑人表】和【报案受害人表】判断人员类型
					if(StringUtils.isNotBlank(casesRecord.getTarget()) && StringUtils.isNotBlank(casesRecord.getAjbh())){
						// 如果嫌疑人表有记录，则是嫌疑人
						if(casesSuspectCidService.isExist(casesRecord.getTarget(), casesRecord.getAjbh())){
							casesRecord.setRylx("嫌疑人");
						}else{
							// 如果报案受害人表有记录，则是报案或受害人
							casesRecord.setRylx(casesInformantCidService.getCasesInformantType(casesRecord.getTarget(), casesRecord.getAjbh()));
						}
					}
					casesRecord.setBlly("警综系统");
					casesRecordMapper.insert(casesRecord);
				}
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, Dept sendDept, Dept receiveDept) {
		CasesRecordCid casesRecordCid = new CasesRecordCid();
		casesRecordCid.setAjbh(ajbh);
		List<CasesRecordCid> casesRecordResult = casesRecordCidService.listCasesRecord(casesRecordCid);
		// 从警综库查询到，则插入
		if(casesRecordResult != null && casesRecordResult.size() > 0){
			for(CasesRecordCid item : casesRecordResult) {
				if(!isExist(item.getSystemid())){
					CasesRecord casesRecord = new CasesRecord();
					BeanUtils.copyProperties(item, casesRecord);
					// 根据人员编号查找【嫌疑人表】和【报案受害人表】判断人员类型
					if(StringUtils.isNotBlank(casesRecord.getTarget()) && StringUtils.isNotBlank(casesRecord.getAjbh())){
						// 如果嫌疑人表有记录，则是嫌疑人
						if(casesSuspectCidService.isExist(casesRecord.getTarget(), casesRecord.getAjbh())){
							casesRecord.setRylx("嫌疑人");
						}else{
							// 如果报案受害人表有记录，则是报案或受害人
							casesRecord.setRylx(casesInformantCidService.getCasesInformantType(casesRecord.getTarget(), casesRecord.getAjbh()));
						}
					}
					casesRecord.setBlly("警综系统");
					casesRecordMapper.insert(casesRecord);
				}
			}
		}
	}

	@Override
	public List<CasesRecord> list(String ajbh) {
		CasesRecord casesRecordParam = new CasesRecord();
		casesRecordParam.setAjbh(ajbh);
		List<CasesRecord> lsCasesRecord = casesRecordMapper.select(casesRecordParam);
		return lsCasesRecord;
	}

	@Override
	public List<CasesRecord> listRecordKL(List<CasesRecordKLParam> casesRecordKLParam) {
		User loginUser = UserUtil.getUser();
		Dept loginUserDept = loginUser.getDepartment();
		if(loginUser == null){
			throw new ServiceException("无法获取当前登录用户!");
		}
		if(loginUserDept == null){
			throw new ServiceException("无法获取当前登录用户机构!");
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String userLevel = DepartmentUtil.getUserLevel(loginUserDept.getPolityLevel(), loginUserDept.getCode());
		List<CasesRecord> lsCasesRecord = Lists.newArrayList();
		try {
			for(CasesRecordKLParam param : casesRecordKLParam){
				//电子笔录JSON字符串，不带正文
				String resultNoBody = CasesRecordUtil.getRecordByCXTJ(param.getAjbh(), param.getRybh(), param.getSfzh(), loginUser.getAccount(), userLevel);
				// 电子笔录（不带正文）
				List<CasesRecordKL> lsCasesRecordList = mapper.readValue(resultNoBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
				if(CollectionUtils.isNotEmpty(lsCasesRecordList)){
					for(CasesRecordKL objCasesRecordKL : lsCasesRecordList){
						if(StringUtils.isNotEmpty(objCasesRecordKL.getID())){
							//带正文的电子笔录JSON字符串，
							String resultHasBody = CasesRecordUtil.getRecordByBLBH(objCasesRecordKL.getID(), loginUser.getAccount(), userLevel);
							List<CasesRecordKL> lsCasesRecordKL = mapper.readValue(resultHasBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
							// 根据笔录ID获取的笔录应该只会有一条
							CasesRecordKL casesRecordKL = lsCasesRecordKL.get(0);
							CasesRecord casesRecord = commonInsert(casesRecordKL, objCasesRecordKL.getID());
							lsCasesRecord.add(casesRecord);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			return lsCasesRecord;
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insertRecordKL(List<CasesRecordKLParam> casesRecordKLParam) {
		String account = "100001";
		User loginUser = UserUtil.getUser();
		Dept loginUserDept = loginUser.getDepartment();
		if(loginUser != null){
			account = loginUser.getAccount();
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String userLevel = "1";
		try {
			for(CasesRecordKLParam param : casesRecordKLParam){
				//电子笔录JSON字符串，不带正文
				String resultNoBody = CasesRecordUtil.getRecordByCXTJ(param.getAjbh(), param.getRybh(), param.getSfzh(), loginUser.getAccount(), userLevel);
				// 电子笔录（不带正文）
				List<CasesRecordKL> lsCasesRecordList = mapper.readValue(resultNoBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
				if(CollectionUtils.isNotEmpty(lsCasesRecordList)){
					for(CasesRecordKL objCasesRecordKL : lsCasesRecordList){
						if(StringUtils.isNotEmpty(objCasesRecordKL.getID())){
							//带正文的电子笔录JSON字符串，
							String resultHasBody = CasesRecordUtil.getRecordByBLBH(objCasesRecordKL.getID(), account, userLevel);
							List<CasesRecordKL> lsCasesRecordKL = mapper.readValue(resultHasBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
							// 根据笔录ID获取的笔录应该只会有一条
							CasesRecordKL casesRecordKL = lsCasesRecordKL.get(0);
							commonInsert(casesRecordKL, objCasesRecordKL.getID());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insertRecordKL(List<CasesRecordKLParam> casesRecordKLParam, Dept sendDept, Dept receiveDept) {
		String account = "051412";
		String ajbh = casesRecordKLParam.get(0).getAjbh();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String userLevel = "1";
		for(CasesRecordKLParam param : casesRecordKLParam){
			String resultNoBody = "";
			// 电子笔录（不带正文）
			try {
				System.out.println("param.getAjbh() + \"  \" + param.getRybh() + \"  \" + param.getSfzh() = " + param.getAjbh() + "  " + param.getRybh() + "  " + param.getSfzh());
				//电子笔录JSON字符串，不带正文
				resultNoBody = CasesRecordUtil.getRecordByCXTJ(param.getAjbh(), param.getRybh(), param.getSfzh(), account, userLevel);
				System.out.println("resultNoBody = " + resultNoBody);

				List<CasesRecordKL> lsCasesRecordList = mapper.readValue(resultNoBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
				if(CollectionUtils.isNotEmpty(lsCasesRecordList)){
					for(CasesRecordKL objCasesRecordKL : lsCasesRecordList){
						if(StringUtils.isNotEmpty(objCasesRecordKL.getID())){
							try {
								//带正文的电子笔录JSON字符串，
								String resultHasBody = CasesRecordUtil.getRecordByBLBH(objCasesRecordKL.getID(), account, userLevel);
								System.out.println("objCasesRecordKL.getID() + \"  \" + account + \"  \" + userLevel = " + objCasesRecordKL.getID() + "  " + account + "  " + userLevel);

								System.out.println("resultHasBody = " + resultHasBody);
								List<CasesRecordKL> lsCasesRecordKL = mapper.readValue(resultHasBody, mapper.getTypeFactory().constructParametricType(List.class, CasesRecordKL.class));
								// 根据笔录ID获取的笔录应该只会有一条
								CasesRecordKL casesRecordKL = lsCasesRecordKL.get(0);
								commonInsert(casesRecordKL, objCasesRecordKL.getID());
							} catch (Exception e) {
								System.out.println("明细笔录返回json转换失败：" + resultNoBody);
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("案件笔录返回json转换失败：" + resultNoBody);
			}
		}
	}

	/**
	 * 复用插入逻辑
	 * @param casesRecordKL
	 * @param rylx
	 * @return 返回插入后的对象
	 */
	private CasesRecord commonInsert(CasesRecordKL casesRecordKL, String rylx) {
		// 不存在则插入
		if(!isExist(casesRecordKL.getID())){
			CasesRecord casesRecord = new CasesRecord();
			//本地库的笔录表的systemid为第三方表的主键，所以
			//警综的笔录库是sytemid对应本地库systemid字段
			//考拉的笔录库是id对应本地库systemid字段
			casesRecord.setSystemid(casesRecordKL.getID());
			casesRecord.setBlly("手动提取（笔录系统）");
			casesRecord.setRylx(rylx);
			casesRecord.setAjbh(casesRecordKL.getCASECODE());
			casesRecord.setTarget(casesRecordKL.getJZRYBH());
			casesRecord.setTargetXm(casesRecordKL.getQUESTIONNAME());
			casesRecord.setTargetXb("女".equals(casesRecordKL.getSEX()) ? "2" : "1");
			casesRecord.setTargetXzz(casesRecordKL.getPRESENTADDRESS());
			casesRecord.setTargetZjhm(casesRecordKL.getCREDENTIALNO());
			casesRecord.setBody(casesRecordKL.getNOTECONTENT());
			casesRecord.setRecorder(casesRecordKL.getQUESTIONER1());
			casesRecord.setJldd(casesRecordKL.getQUESTIONER1WORKUNIT());
			casesRecord.setTargetLxdh(casesRecordKL.getPHONENUMBER());
			try {
				casesRecord.setStarttime(DateUtil.parseToDate(casesRecordKL.getBEGINTIME(), DateUtil.DEFAULT_SUPPORTED_PATTERN[DateUtil.DEFAULT_SUPPORTED_PATTERN.length - 1]));
				casesRecord.setEndtime(DateUtil.parseToDate(casesRecordKL.getENDTIME(), DateUtil.DEFAULT_SUPPORTED_PATTERN[DateUtil.DEFAULT_SUPPORTED_PATTERN.length - 1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			casesRecordMapper.insertSelective(casesRecord);
			return casesRecord;
		}else{
			return null;
		}
	}

	private boolean isExist(String systemid) {
		CasesRecord casesRecord = new CasesRecord();
		casesRecord.setSystemid(systemid);
		return casesRecordMapper.selectCount(casesRecord) > 0 ? true : false;
	}

}
