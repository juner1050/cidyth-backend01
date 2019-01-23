package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.module.base.dao.CasesGroupMapper;
import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.cidyth.module.base.service.CasesGroupService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesGroupService")
public class CasesGroupServiceImpl implements CasesGroupService {

	private static final Logger logger = LoggerFactory.getLogger(CasesGroupServiceImpl.class);

	@Autowired
	private CasesGroupMapper casesGroupMapper;

	@Override
	public void insert(String ajbh, User loginUser) {
		if(casesGroupMapper.isExist(ajbh, loginUser.getAccount())==0) {
			CasesGroup groupAsAllocator = new CasesGroup();
			groupAsAllocator.setAjbh(ajbh);
			groupAsAllocator.setJgdm(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getCode());
			groupAsAllocator.setJgmc(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getFullname());
			groupAsAllocator.setJybh(loginUser.getAccount());
			groupAsAllocator.setJyxm(loginUser.getName());
			groupAsAllocator.setSjhm(loginUser.getPhone());
			groupAsAllocator.setLrryjgdm(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getCode());
			groupAsAllocator.setLrryjgmc(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getFullname());
			groupAsAllocator.setLrrybh(loginUser.getAccount());
			groupAsAllocator.setLrrymc(loginUser.getName());
			casesGroupMapper.insertSelective(groupAsAllocator);
		}
	}

	@Override
	public boolean isExist(String ajbh, String account) {
		return casesGroupMapper.isExist(ajbh, account) > 0 ? true : false;
	}

	@Override
	public int countCasesJoinPerson(String ajbh) {
		CasesGroup casesGroup = new CasesGroup();
		casesGroup.setAjbh(ajbh);
		return casesGroupMapper.selectCount(casesGroup);
	}

	/**
	 * 批量插入
	 *
	 * @param lsCasesGroup
	 */
	@Override
	public void insertList(List<CasesGroup> lsCasesGroup) {
		if(CollectionUtils.isNotEmpty(lsCasesGroup)){
			casesGroupMapper.insertList(lsCasesGroup);
		}
	}

	@Override
	public List<String> listMyJoinCaseCode() {
		User loginUser = UserUtil.getUser();
		CasesGroup queryParam = new CasesGroup();
		queryParam.setJybh("aaaaaaaaaa");
		return casesGroupMapper.select(queryParam).stream().map(CasesGroup::getJybh).collect(Collectors.toList());
	}
}
