package com.hyzs.cidyth.module.websocket.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.enums.YesNoEnum;
import com.hyzs.cidyth.communicate.websocket.config.AbstractWebSocketProperties;
import com.hyzs.cidyth.communicate.websocket.service.impl.CommunicationChannels;
import com.hyzs.cidyth.communicate.websocket.service.impl.JacksonBasedWebSocketMessageCodec;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.impl.CasesCidServiceImpl;
import com.hyzs.cidyth.module.clue.service.impl.ClueServiceImpl;
import com.hyzs.cidyth.module.clue.vo.Clues;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.service.impl.DemandFlowServiceImpl;
import com.hyzs.cidyth.module.demand.service.impl.DemandServiceImpl;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;
import com.hyzs.cidyth.module.notice.dao.NoticeMapper;
import com.hyzs.cidyth.module.notice.entity.Notice;
import com.hyzs.cidyth.module.reply.service.MessageAnchroRule;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BusinessDataCreateEvent;
import com.hyzs.cidyth.module.websocket.service.BusinessBasedDataListener;
import com.hyzs.psd.gafa.websocket.data.WebSocketMessage;
import com.hyzs.psd.gafa.websocket.netty.WebSocketMessageSender;
import com.hyzs.psd.gafa.websocket.netty.boot.DefaultWebSocketMessageSender;

/**
 * 全局websocket服务
 * 
 * @author derrick
 *
 */
@Component("globalWebsocketDataListener")
public class GlobalWebsocketDataListener implements BusinessBasedDataListener {
	private static final Logger logger = LoggerFactory.getLogger(GlobalWebsocketDataListener.class);
	@Autowired
	@Qualifier("globalWebSocketProperties")
	private AbstractWebSocketProperties globalWebSocketProperties;
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private UserCenterService userCenterService;
	private WebSocketMessageSender globalWebsocketConnector;
	private Set<Class<?>> accaptableEventSourceTypes = new HashSet<Class<?>>() {
		{
			// add(InfoServiceImpl.class);
			// add(ReplyServiceImpl.class);
			add(CasesCidServiceImpl.class);
			add(DemandServiceImpl.class);
			add(DemandFlowServiceImpl.class);
			add(ClueServiceImpl.class);
		}
	};
	private Set<Class<? extends BussinessDataEvent>> accaptableEventTypes = new HashSet<Class<? extends BussinessDataEvent>>() {
		{
			add(BusinessDataCreateEvent.class);
		}
	};

	public GlobalWebsocketDataListener() {
		globalWebsocketConnector = new DefaultWebSocketMessageSender();
		((DefaultWebSocketMessageSender) globalWebsocketConnector)
				.setWebSocketMessageCodec(new JacksonBasedWebSocketMessageCodec());
	}

	@Override
	public void onApplicationEvent(BussinessDataEvent event) {
		if (AccaptableEventValidator.isAcceptableEvent(event, accaptableEventTypes)) {
			if (AccaptableEventValidator.isAcceptableEventSource(event.getEventSource(), accaptableEventSourceTypes)) {
				Object data = event.getBusinessData();
				if (globalWebsocketConnector != null && data != null) {
					if (Cases.class.isAssignableFrom(data.getClass()) || data instanceof List) {
						List<Cases> casesList = Lists.newArrayList();
						if (Cases.class.isAssignableFrom(data.getClass())) {
							casesList.add((Cases) data);
						}
						if (data instanceof List) {
							List list = (List) data;
							if (CollectionUtils.isNotEmpty(list)
									&& Cases.class.isAssignableFrom(list.get(0).getClass())) {
								casesList.addAll(list);
							}
						}
						logger.debug("notification on CASE create");
						for (int c = 0; c < casesList.size(); c++) {
							Cases cases = casesList.get(c);
							String sljsdw = cases.getSljsdw();
							if (StringUtils.isNotBlank(sljsdw)) {
								try {
									Set<String> toUserIdSet = Sets.newHashSet();
									List<Notice> notices = Lists.<Notice>newArrayList();
									Set<Dept> subDepts = this.userCenterService
											.getSubDepartmentsByParentDeptCodes(Sets.newHashSet(sljsdw));
									if (CollectionUtils.isNotEmpty(subDepts)) {
										for (Dept dept : subDepts) {
											List<User> users = this.userCenterService
													.getUsersByDepartmentCode(dept.getCode());
											if (CollectionUtils.isNotEmpty(users)) {
												for (User user : users) {
													toUserIdSet.add(user.getAccount());

													Notice notice = new Notice();
													notice.setAnchor(cases.getAjbh());
													notice.setTznr("有新案件[" + cases.getAjbh() + "]");
													notice.setJsrbh(user.getAccount());
													notice.setJsrmc(user.getName());
													notice.setJsrjgbh(user.getDepartment() == null ? "" : user.getDepartment().getCode());
													notice.setJsrjgmc(user.getDepartment() == null ? "" : user.getDepartment().getFullname());

													notice.setSfyd(YesNoEnum.NO.code());
													notices.add(notice);
												}
											}
										}
									} else {
										List<User> users = this.userCenterService.getUsersByDepartmentCode(sljsdw);
										if (CollectionUtils.isNotEmpty(users)) {
											for (User user : users) {
												toUserIdSet.add(user.getAccount());

												Notice notice = new Notice();
												notice.setAnchor(cases.getAjbh());
												notice.setTznr("有新案件[" + cases.getAjbh() + "]");
												notice.setJsrbh(user.getAccount());
												notice.setJsrmc(user.getName());
												notice.setSfyd(YesNoEnum.NO.code());
												notice.setAjbh(cases.getAjbh());
												notices.add(notice);
											}
										}
									}
									if (CollectionUtils.isNotEmpty(notices)) {
										noticeMapper.insertBatch(notices);
										for (Notice notice : notices) {
											this.push(toUserIdSet, notice);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					if (data instanceof Clues) {
						logger.debug("notification on CLUE create");
						Clues cls = (Clues) data;
						Integer demandId = cls.getDemandId();
						if (demandId != null) {
							StringBuilder anchro = new StringBuilder();
							Demand demand = demandMapper.selectByPrimaryKey(demandId);
							if (demand != null) {
								String ajbh = demand.getAjbh();
								anchro.append(MessageAnchroRule.buildForClue(ajbh, demandId + "", cls.getId() + ""));
								// anchro.append(ajbh + "_" + demandId + "_" +
								// ReplyTypeEnum.CLUE + "_" + cls.getId());
							}
							Set<String> toUserIdSet = Sets.newHashSet();
							if (StringUtils.isNotBlank(demand.getQqry())) {
								toUserIdSet.add(demand.getQqry());
							}
							Notice notice = new Notice();
							notice.setAnchor(anchro.toString());
							notice.setTznr(StringUtils.isBlank(cls.getLrrymc())
									? (StringUtils.isBlank(cls.getFkry()) ? "有人" : cls.getFkry())
									: cls.getLrrymc() + " 已经对您发布的需求[" + demand.getXqmc() + "]反馈了线索!");
							notice.setAjbh(demand.getAjbh());
							notice.setJsrbh(demand.getQqry());
							notice.setJsrmc(demand.getLrrymc());
							notice.setJsrjgbh(demand.getQqdwbh());
							notice.setJsrjgmc(demand.getQqdw());

							notice.setFsrbh(cls.getFkry());
							notice.setFsrmc(cls.getLrrymc());
							notice.setFsrjgbh(cls.getFkdwbh());
							notice.setFsrjgmc(cls.getFkdw());

							notice.setTzmb(TimeNodeEnum.DEMAND.name());
							notice.setTzmbid(demand.getId());
							notice.setTzly(TimeNodeEnum.CLUE.name());
							notice.setTzlyid(cls.getId());

							notice.setSfyd(YesNoEnum.NO.code());
							noticeMapper.insert(notice);
							this.push(toUserIdSet, notice);
						}
					}
					if (data instanceof DemandDetail) {
						logger.debug("notification on DEMAND create");
						DemandDetail demand = (DemandDetail) data;
						StringBuilder anchro = new StringBuilder();
						anchro.append(MessageAnchroRule.buildForDemand(demand.getAjbh(), demand.getId() + ""));
						// anchro.append(demand.getAjbh()).append("_").append(ReplyTypeEnum.DEMAND).append("_")
						// .append(demand.getId());
						Set<String> toUserIdSet = demand.toUsers() == null ? Sets.<String>newHashSet()
								: demand.toUsers();
						toUserIdSet.remove(demand.getQqry());
						if (CollectionUtils.isNotEmpty(toUserIdSet)) {
							List<Notice> notices = Lists.<Notice>newArrayList();
							for (String jsrybh : toUserIdSet) {
								User toUser = null;
								User fromUser = null;
								try {
									toUser = userCenterService.getUserLazyByUserName(jsrybh);
									fromUser = userCenterService.getUserLazyByUserName(demand.getQqry());
								} catch (Exception e) {
									e.printStackTrace();
								}
								Notice notice = new Notice();
								notice.setAnchor(anchro.toString());
								notice.setTznr("有新的需求！");
								notice.setJsrbh(jsrybh);

								notice.setAjbh(demand.getAjbh());
								notice.setJsrbh(toUser.getAccount());
								notice.setJsrmc(toUser.getName());
								notice.setJsrjgbh(toUser.getDepartment() == null ? "" : toUser.getDepartment().getCode());
								notice.setJsrjgmc(toUser.getDepartment() == null ? "" : toUser.getDepartment().getFullname());

								notice.setFsrbh(fromUser.getAccount());
								notice.setFsrmc(fromUser.getName());
								notice.setFsrjgbh(fromUser.getDepartment() == null ? "" : fromUser.getDepartment().getCode());
								notice.setFsrjgmc(fromUser.getDepartment() == null ? "" : fromUser.getDepartment().getFullname());

								notice.setTzmb(TimeNodeEnum.DEMAND.name());
								notice.setTzmbid(demand.getId());
								notice.setTzly(TimeNodeEnum.DEMAND.name());
								notice.setTzlyid(demand.getId());

								notice.setSfyd(YesNoEnum.NO.code());
								notices.add(notice);
							}
							if (CollectionUtils.isNotEmpty(notices)) {
								noticeMapper.insertBatch(notices);

								Notice jsonObject = new Notice();
								jsonObject.setAnchor(anchro.toString());
								jsonObject.setTznr("有新的需求！");
								jsonObject.setSfyd(YesNoEnum.NO.code());
								this.push(toUserIdSet, jsonObject);
							}
						}
					}
				}
			}
		}
	}

	private void push(Set<String> toUserIdSet, Object data) {
		if (data != null && CollectionUtils.isNotEmpty(toUserIdSet)) {
			try {
				WebSocketMessage globalMessage = new WebSocketMessage();
				globalMessage.setFrom(CommunicationChannels.GLOBAL);
				globalMessage.setTo(toUserIdSet);
				globalMessage.setBody(data);
				globalWebsocketConnector.publish(globalMessage);
			} catch (Exception e) {
				logger.error("Failed to send data to WebSocket Server[" + globalWebSocketProperties.getHost() + ":"
						+ globalWebSocketProperties.getPort() + "],{}", e);
				e.printStackTrace();
			}
		}
	}

	@PostConstruct
	private void init() {

		DefaultWebSocketMessageSender wsdp = ((DefaultWebSocketMessageSender) globalWebsocketConnector);
		wsdp.setHost(globalWebSocketProperties.getHost());
		wsdp.setPort(Integer.parseInt(globalWebSocketProperties.getPort()));
		wsdp.setWebsocketPath(globalWebSocketProperties.getWebsocketPath());
		try {
			wsdp.connect();
		} catch (Exception e) {
			logger.error("Failed to connect WebSocket Server[" + globalWebSocketProperties.getHost() + ":"
					+ globalWebSocketProperties.getPort() + "],{}", e);
		}
	}

	@PreDestroy
	private void destroy() {
		if (globalWebSocketProperties != null) {
			DefaultWebSocketMessageSender wsdp = ((DefaultWebSocketMessageSender) globalWebsocketConnector);
			wsdp.destroy();
		}
	}
}
