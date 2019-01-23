package com.hyzs.cidyth.module.reply.service.impl.validator;

import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.reply.vo.Replies;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.MessageContentValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 针对需求的回复验证
 * 
 * @author jidw
 *
 */
@Service("replyContentOnDemandValidator")
public class ReplyContentOnDemandValidator implements MessageContentValidator<Replies> {
	private static final Logger logger = LoggerFactory.getLogger(ReplyContentOnDemandValidator.class);

	@Override
	public String name() {
		return ReplyTypeEnum.DEMAND.name();
	}

	@Override
	public void validate(Replies content) throws Exception {
		logger.debug("针对需求的回复进行验证...");
		if (content == null) {
			throw new ServiceException("参数不能为空!");
		}
		if (StringUtils.isBlank(content.getXxlx()) || !name().equals(content.getXxlx())) {
			throw new ServiceException("回复内容的类型不合法或者不是针对需求的回复!");
		}
		if (StringUtils.isBlank(content.getReferenceId())) {
			throw new ServiceException("缺少需求id.");
		}
		User user = UserUtil.getUser();
		if (StringUtils.isBlank(content.getFsry()) && (user == null)) {
			throw new ServiceException("缺少回复发起人用户编号");
		}
		if (StringUtils.isBlank(content.getFsry())) {
			content.setFsry(user.getAccount());
			content.setFsryxm(user.getName());
		}
		if (StringUtils.isBlank(content.getFsdw())) {
			Dept dept = user.getDepartment();
			if (dept != null) {
				content.setFsdw(dept.getCode());
				content.setFsdwmc(dept.getFullname());
			}
		}
		if (StringUtils.isBlank(content.getFsnr())) {
			throw new ServiceException("没有反馈内容!");
		}
		logger.debug("reply from user[" + content.getFsdw() + "," + content.getFsry() + "] on DEMAND["
				+ content.getReferenceId() + "]");
	}

}
