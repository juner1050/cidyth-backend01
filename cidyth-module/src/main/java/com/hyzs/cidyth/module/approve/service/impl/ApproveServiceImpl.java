package com.hyzs.cidyth.module.approve.service.impl;

import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.approve.dao.ApproveMapper;
import com.hyzs.cidyth.module.approve.entity.Approve;
import com.hyzs.cidyth.module.approve.service.ApproveService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.axis.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞
 * 
 * @author derrick
 *
 */
@Service("approveService")
public class ApproveServiceImpl implements ApproveService {

	private static final Logger logger = LoggerFactory.getLogger(ApproveServiceImpl.class);
	@Autowired
	private ApproveMapper approveMapper;

	@Override
	public int insert(Approve approve) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("获取需求用户失败!");
		}
		if(approve.getReferenceId() == null){
			throw new ServiceException("点赞目标参数无效!");
		}
		if(StringUtils.isEmpty(approve.getReferenceType())){
			throw new ServiceException("点赞类型参数无效!");
		}
		if(approveMapper.selectCount(approve) > 0){
			return 0;
		}
		approve.setLrry(loginUser.getAccount());
		return approveMapper.insertSelective(approve);
	}

	@Override
	public int count(Integer referenceId, String referenceType) {
		Approve approve = new Approve();
		approve.setReferenceId(referenceId);
		approve.setReferenceType(referenceType);
		return approveMapper.selectCount(approve);
	}
}
