package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesCidMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.CasesCidService;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesCidService")
public class CasesCidServiceImpl implements CasesCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesCidServiceImpl.class);

	@Autowired
	private CasesCidMapper casesCidMapper;

	@Override
	public PageInfo<Cases> listPackCases(CasesVO casesParam, Page page) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		Map<String,Object> parameter = new HashMap<String,Object>();
		if(casesParam != null){
			// 限制按照部分机构编号查询
			/*String sectionDeptCode = DepartmentUtil.getSectionDeptCode(loginUser.getDepartment().getPolityLevel(),
					loginUser.getDepartment().getCode());*/
			//如果查询的受理接收单位为空，则默认查询本单位
			/*if(StringUtils.isEmpty(casesParam.getSljsdw())){
				casesParam.setSljsdw(loginUser.getDeptid());
			}*/
			/*if(StringUtils.isNotEmpty(sectionDeptCode)){
				// 长度为6代表分局机构
				if(sectionDeptCode.length() == 6){
					casesParam.setSljsdw(sectionDeptCode);
					// 如果分局的人的单位不为空，并且不包含分局前缀6位编号，则代表查询条件的主办单位不属于分局下的
					if(StringUtils.isNotEmpty(casesParam.getZbdw()) && casesParam.getZbdw().indexOf(sectionDeptCode) < 0){
						casesParam.setSljsdw(null);
					}
				}else{
					casesParam.setSljsdw(sectionDeptCode);
				}
			}*/
			parameter.put("beginLasj", casesParam.getBeginLasj());
			parameter.put("endLasj", casesParam.getEndLasj());
			parameter.put("fasjcz", casesParam.getFasjcz());
			parameter.put("fasjzz", casesParam.getFasjzz());
			parameter.put("ajbh", casesParam.getAjbh());
			parameter.put("ajmc", casesParam.getAjmc());
			parameter.put("ajzbry", casesParam.getAjzbry());
			parameter.put("ajstate", casesParam.getAjstate());
			parameter.put("sljsdw", casesParam.getSljsdw());

			if(AuthorityUtil.check(loginUser.getAccount())){
				casesParam.setSljsdw(null);
			}
		}
		//parameter.put("userDeptCode", value);
		
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Cases> result = casesCidMapper.selectListPackCases(parameter);
		PageInfo<Cases> pageInfo = new PageInfo<>(result);
		//BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this, result));
		return pageInfo;
	}

}
