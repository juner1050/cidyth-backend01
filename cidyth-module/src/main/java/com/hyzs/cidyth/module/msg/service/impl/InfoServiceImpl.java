package com.hyzs.cidyth.module.msg.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.RuleTypeEnum;
import com.hyzs.cidyth.common.enums.TableTypeEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.dao.CasesPartnerMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.interaction.vo.InfoDetail;
import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.msg.dao.InfoMapper;
import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.msg.entity.InfoAcceptance;
import com.hyzs.cidyth.module.msg.service.InfoService;
import com.hyzs.cidyth.module.msg.vo.InfoVO;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BussinessDataEventPublisher;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("InfoService")
public class InfoServiceImpl implements InfoService {

	private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private CasesPartnerMapper casesPartnerMapper;//合成作战小组
	@Autowired
	private MindService mindService;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;
	@Autowired
	private CasesService casesService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private TimeNodeService timeNodeService;
	@Autowired
	private IntegralHisService integralHisService;
	@Autowired
	private IntegralConfigService integralConfigService;


	@Override
	public int getCountByAjbh(String ajbh) {
		Info info = new Info();
		info.setAjbh(ajbh);
		return infoMapper.selectCount(info);
	}

	@Override
	public List<InfoVO> list(InfoVO infoVO) {
		Info infoParam = new Info();
		BeanUtils.copyProperties(infoVO, infoParam);
		List<Info> lsInfo = infoMapper.select(infoParam);
		List<InfoVO> lsInfoVO = new ArrayList<>();
		for (Info info : lsInfo) {
			InfoVO infoObj = new InfoVO();
			BeanUtils.copyProperties(info, infoObj);
			// 获取需求的上传附件
			List<Map<String, Object>> lsAttachment = attachmentService.loadAttachment(String.valueOf(info.getId()), TimeNodeEnum.INFO);
			infoObj.setLsAttachment(lsAttachment);
			lsInfoVO.add(infoObj);
		}
		return lsInfoVO;
	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, Object> insert(InfoVO infoVO) {
		if(casesService.isFinish(infoVO.getAjbh())){
			throw new ServiceException("该案件已侦结!");
		}
		int scoreNum = 0;
		Map<String, Object> mapResult = Maps.newHashMap();
		Info info = new Info();
		BeanUtils.copyProperties(infoVO, info);
		Cases cases = null;
		if (StringUtils.isEmpty(infoVO.getAjbh())) {
			throw new ServiceException("案件编号为空!");
		}
		cases = casesService.getCaseByAjbh(infoVO.getAjbh());
		if (cases == null) {
			throw new ServiceException("案件为空!");
		}
		// 获取用户
		User user = UserUtil.getUser();
		if (user != null) {
			info.setFbry(user.getAccount());
			info.setLrry(user.getAccount());
			info.setLrrymc(user.getName());
			Dept dept = user.getDepartment();
			if (dept != null) {
				info.setFbdw(dept.getFullname());
				info.setFbdwbh(dept.getCode());
			}
		}
		// 接收单位
		List<String> lsReceiveOrgCode = new ArrayList<String>();
		// 保存信息
		infoMapper.insertSelective(info);
		/**
		 * 合成作战组员
		 */
		List<CasesPartner> partners = Lists.<CasesPartner>newArrayList();
		if(StringUtils.isNotBlank(info.getAjbh())&&StringUtils.isNotBlank(user.getAccount())){
			if(casesPartnerMapper.isExist(info.getAjbh(), user.getAccount())==0){
				CasesPartner partner = new CasesPartner();
				partner.setAjbh(info.getAjbh());
				partner.setJgdm(user.getDepartment()==null?null:user.getDepartment().getCode());
				partner.setJgmc(user.getDepartment()==null?null:user.getDepartment().getFullname());
				partner.setJybh(user.getAccount());
				partner.setJyxm(user.getName());
				partner.setSjhm(user.getPhone());
				Dept dept = user.getDepartment();
				if (dept != null) {
					partner.setLrryjgdm(dept.getCode());
					partner.setLrryjgmc(dept.getFullname());
				}
				partner.setLrrybh(user.getAccount());
				partner.setLrrymc(user.getName());
				casesPartnerMapper.insertSelective(partner);
				//partners.add(partner);
			}
		}
		if (CollectionUtils.isNotEmpty(infoVO.getAcceptList())) {
			/**
			 * 信息接收对象
			 */
			List<InfoAcceptance> lsInfoAcceptance = Lists.newArrayList();
			for (InfoAcceptance accpt : infoVO.getAcceptList()) {
				if(StringUtils.isNotEmpty(accpt.getJsdwbh())){
					try {
						List<User> lsUser = userCenterService.getUsersByDepartmentCode(accpt.getJsdwbh());
						if(CollectionUtils.isNotEmpty(lsUser)){
							for(User userInfo : lsUser){
								InfoAcceptance itemAccept = new InfoAcceptance();
								itemAccept.setAjbh(info.getAjbh());
								itemAccept.setInfoId(info.getId().longValue());
								itemAccept.setJsrybh(userInfo.getAccount());
								itemAccept.setJsrxm(userInfo.getName());
								itemAccept.setJsdwbh(userInfo.getDepartment()==null?null:userInfo.getDepartment().getCode());
								itemAccept.setJsdwmc(userInfo.getDepartment()==null?null:userInfo.getDepartment().getFullname());
								lsInfoAcceptance.add(itemAccept);
								if(StringUtils.isNotBlank(info.getAjbh())&&StringUtils.isNotBlank(userInfo.getAccount())){
									if(casesPartnerMapper.isExist(info.getAjbh(), userInfo.getAccount())==0){
										CasesPartner partner = new CasesPartner();
										partner.setAjbh(info.getAjbh());
										partner.setJgdm(userInfo.getDepartment()==null?null:userInfo.getDepartment().getCode());
										partner.setJgmc(userInfo.getDepartment()==null?null:userInfo.getDepartment().getFullname());
										partner.setJybh(userInfo.getAccount());
										partner.setJyxm(userInfo.getName());
										partner.setSjhm(userInfo.getPhone());
										Dept dept = user.getDepartment();
										if (dept != null) {
											partner.setLrryjgdm(dept.getCode());
											partner.setLrryjgmc(dept.getFullname());
										}
										partner.setLrrybh(user.getAccount());
										partner.setLrrymc(user.getName());
										partners.add(partner);
									}
								}
							}
						}
					} catch (Exception e) {
						throw new ServiceException("获取单位下的所有用户失败!");
					}
				}
				lsReceiveOrgCode.add(accpt.getJsdwbh());
			}
			if(CollectionUtils.isNotEmpty(lsInfoAcceptance)){
				int count = infoMapper.insertInfoAccpt(lsInfoAcceptance);
				logger.info("saved {} acceptance(s) for info {} ",count,info.getId());
			}
			if(CollectionUtils.isNotEmpty(partners)){
				int count = casesPartnerMapper.batchInsertCasePartners(partners);
				logger.info("saved {} partner(s) for case {} ",count,info.getAjbh());
			}
		}
		List<Map<String,Object>> attachments = null;
		if(CollectionUtils.isNotEmpty(infoVO.getFiles())){
			// 插入附件表（信息对象的附件、附件说明）
			attachments = attachmentService.insert(infoVO.getFiles(), infoVO.getFileComment(), String.valueOf(info.getId()),
					TableTypeEnum.INFO.name());
		}
		// 插入思维导图（维护思维导图关联关系）
		mindService.insert(info.getAjbh(), info.getId(), cases.getId(), TimeNodeEnum.INFO.name(),
				TimeNodeEnum.CASE.name());
		// 保存积分历史
		int integralHisScore = integralHisService.insert(info.getAjbh(), user, RuleTypeEnum.INFO.name());
		if(integralHisScore > 0){
			scoreNum++;
		}
		// 时间轴新增
		timeNodeService.insert(info.getAjbh(), info.getId(), TimeNodeEnum.INFO.name(), info.getXxzt(), info.getFbnr(),
				String.join(",", lsReceiveOrgCode));
		BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this,
				((InfoDetail) new InfoDetail().setInfo(info).setInfoAcceptances(infoVO.getAcceptList()).setAttacments(attachments))));

		Float score = integralConfigService.getScoreByRuleType(RuleTypeEnum.INFO.name());
		mapResult.put("score", scoreNum * score);
		return mapResult;
	}

	@Override
	public InfoVO detail(Integer id) {
		Info info = infoMapper.selectByPrimaryKey(id);
		InfoVO infoVO = new InfoVO();
		BeanUtils.copyProperties(info, infoVO);
		infoVO.setAcceptList(infoMapper.selectInfoAcceptByInfoId(id.longValue()));
		// String[] fbfw = infoVO.getFbfw().split(",");
		// String fbfwCn = "";
		// for (int i = 0; i < fbfw.length; i++) {
		// try {
		// Dept dept = userCenterService.getDeptByCode(fbfw[i]);
		// if (dept != null && StringUtils.isNotEmpty(dept.getFullname())) {
		// fbfwCn += dept.getFullname() + ",";
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// infoVO.setFbfwCn(fbfwCn.substring(0, fbfwCn.length() - 1));
		return null;
	}
}
