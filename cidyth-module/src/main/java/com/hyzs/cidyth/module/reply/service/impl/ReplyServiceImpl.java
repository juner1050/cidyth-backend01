package com.hyzs.cidyth.module.reply.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyzs.cidyth.common.enums.TableTypeEnum;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.reply.dao.ReplyMapper;
import com.hyzs.cidyth.module.reply.service.ReplyService;
import com.hyzs.cidyth.module.reply.vo.Replies;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.FrameworkBasedCommunicationContextManager;
import com.hyzs.cidyth.module.websocket.MessageContentValidator;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BussinessDataEventPublisher;

/**
 * 回复服务
 * 
 * @author jidw
 *
 */
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	@Autowired
	@Qualifier("DemandService")
	private DemandService demandService;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;

	public ReplyServiceImpl() {

	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public String saveReply(Replies reply) throws Exception {
		if (reply == null) {
			throw new ServiceException("参数不能为空!");
		} else {
			MessageContentValidator validator = FrameworkBasedCommunicationContextManager
					.getMessageContentValidator(reply.getXxlx());
			if (validator == null) {
				throw new ServiceException("回复内容的类型不合法或者未知!");
			} else {
				validator.validate(reply);
				String responseUserId = reply.getHfry();
				if (StringUtils.isNotBlank(responseUserId)) {
					try {
						User usr = userCenterService.getUserByUserName(responseUserId);
						if (usr != null) {
							reply.setHfryxm(usr.getName());
							Dept dept = usr.getDepartment();
							if (dept != null) {
								reply.setHfdw(dept.getCode());
								reply.setHfdwmc(dept.getFullname());
							}
						}
					} catch (Exception e) {
						logger.error("调用用户中心服务失败.{}", e);
						e.printStackTrace();
					}
				}
				replyMapper.insertSelective(reply.getReply());
			}
		}
		List<Map<String, Object>> attachments = null;
		Integer replyId = reply.getReply().getId();
		if (replyId == null) {
			throw new ServiceException("保存失败!");
		} else {
			if (reply.getFiles() != null) {
				// 插入附件表
				attachments = attachmentService.insert(reply.getFiles(), "", String.valueOf(reply.getReply().getId()),
						TableTypeEnum.REPLY.name());
			}
		}
		BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this,
				reply.setReply(replyMapper.selectByPrimaryKey(replyId)).setAttachments(attachments)));
		return replyId.toString();
	}

	@Override
	public Replies getReplyById(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new ServiceException("参数不能为空!");
		}
		Replies result = new Replies().setReply(replyMapper.selectByPrimaryKey(Integer.parseInt(id)));
		return result;
	}
}
