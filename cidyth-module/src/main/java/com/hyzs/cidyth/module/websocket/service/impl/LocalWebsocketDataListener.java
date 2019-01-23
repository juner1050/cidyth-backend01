package com.hyzs.cidyth.module.websocket.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.communicate.websocket.config.AbstractWebSocketProperties;
import com.hyzs.cidyth.communicate.websocket.service.impl.CommunicationChannels;
import com.hyzs.cidyth.communicate.websocket.service.impl.JacksonBasedWebSocketMessageCodec;
import com.hyzs.cidyth.module.clue.service.impl.ClueServiceImpl;
import com.hyzs.cidyth.module.clue.vo.Clues;
import com.hyzs.cidyth.module.demand.service.impl.DemandFlowServiceImpl;
import com.hyzs.cidyth.module.demand.service.impl.DemandServiceImpl;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;
import com.hyzs.cidyth.module.interaction.vo.InfoDetail;
import com.hyzs.cidyth.module.msg.service.impl.InfoServiceImpl;
import com.hyzs.cidyth.module.reply.service.impl.ReplyServiceImpl;
import com.hyzs.cidyth.module.reply.vo.DemandInteractionContent;
import com.hyzs.cidyth.module.reply.vo.Replies;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BusinessDataCreateEvent;
import com.hyzs.cidyth.module.websocket.service.BusinessBasedDataListener;
import com.hyzs.psd.gafa.websocket.data.WebSocketMessage;
import com.hyzs.psd.gafa.websocket.netty.WebSocketMessageSender;
import com.hyzs.psd.gafa.websocket.netty.boot.DefaultWebSocketMessageSender;

/**
 * 局部websocket服务
 * 
 * @author derrick
 *
 */
@Component("localWebsocketDataListener")
public class LocalWebsocketDataListener implements BusinessBasedDataListener {
	private static final Logger logger = LoggerFactory.getLogger(LocalWebsocketDataListener.class);
	@Autowired
	@Qualifier("localWebSocketProperties")
	private AbstractWebSocketProperties localWebSocketProperties;

	private WebSocketMessageSender localWebsocketConnector;

	private Set<Class<?>> accaptableEventSourceTypes = new HashSet<Class<?>>() {
		{
			add(DemandServiceImpl.class);
			add(DemandFlowServiceImpl.class);
			add(InfoServiceImpl.class);
			add(ReplyServiceImpl.class);
			add(ClueServiceImpl.class);
		}
	};

	private Set<Class<? extends BussinessDataEvent>> accaptableEventTypes = new HashSet<Class<? extends BussinessDataEvent>>() {
		{
			add(BusinessDataCreateEvent.class);
		}
	};

	public LocalWebsocketDataListener() {
		localWebsocketConnector = new DefaultWebSocketMessageSender();
		((DefaultWebSocketMessageSender) localWebsocketConnector)
				.setWebSocketMessageCodec(new JacksonBasedWebSocketMessageCodec());
	}

	@Override
	public void onApplicationEvent(BussinessDataEvent event) {
		if (AccaptableEventValidator.isAcceptableEvent(event, accaptableEventTypes)) {
			if (AccaptableEventValidator.isAcceptableEventSource(event.getEventSource(), accaptableEventSourceTypes)) {
				Object data = event.getBusinessData();
				if (localWebsocketConnector != null && data != null) {
					if (data instanceof DemandDetail) {
						logger.info("DEMAND is created....");
						Set<String> toUserIdSet = Sets.newHashSet();
						toUserIdSet.addAll(((DemandDetail) data).toUsers());
						this.push(toUserIdSet, data);
					}
					if (data instanceof InfoDetail) {
						logger.info("INFO is created....");
						Set<String> toUserIdSet = Sets.newHashSet();
						toUserIdSet.addAll(((InfoDetail) data).toUsers());
						this.push(toUserIdSet, data);
					}
					if (data instanceof Replies) {
						Replies rpy = (Replies) data;
						logger.info("REPLY is created....");
						Set<String> toUserIdSet = Sets.newHashSet();
						toUserIdSet.addAll(((Replies) data).toUsers());
						String topMessageIdentifer = rpy.getTopMessageIdentifer();
						this.push(toUserIdSet, DemandInteractionContent.convertReply(topMessageIdentifer,
								rpy.getReply(), rpy.getAttachments()));
					}
					if (data instanceof Clues) {
						logger.info("CLUE is created....");
						Clues cls = (Clues) data;
						Set<String> toUserIdSet = Sets.newHashSet();
						toUserIdSet.addAll(((Clues) data).toUsers());
						String topMessageIdentifer = ReplyTypeEnum.DEMAND.name() + "_" + cls.getDemandId();
						this.push(toUserIdSet, DemandInteractionContent.convertClue(topMessageIdentifer, cls.getClue(),
								cls.getAttachments()));
					}
				}
			}
		}
	}

	private void push(Set<String> toUserIdSet, Object data) {
		if (data != null && CollectionUtils.isNotEmpty(toUserIdSet)) {
			try {
				WebSocketMessage localMessage = new WebSocketMessage();
				localMessage.setFrom(CommunicationChannels.LOCAL);
				localMessage.setTo(toUserIdSet);
				localMessage.setBody(data);
				localWebsocketConnector.publish(localMessage);
			} catch (Exception e) {
				logger.error("Failed to send data to WebSocket Server[" + localWebSocketProperties.getHost() + ":"
						+ localWebSocketProperties.getPort() + "],{}", e);
				e.printStackTrace();
			}

		}

	}

	@PostConstruct
	private void init() {
		DefaultWebSocketMessageSender wsdp = ((DefaultWebSocketMessageSender) localWebsocketConnector);
		wsdp.setHost(localWebSocketProperties.getHost());
		wsdp.setPort(Integer.parseInt(localWebSocketProperties.getPort()));
		wsdp.setWebsocketPath(localWebSocketProperties.getWebsocketPath());
		try {
			wsdp.connect();
		} catch (Exception e) {
			logger.error("Failed to connect WebSocket Server[" + localWebSocketProperties.getHost() + ":"
					+ localWebSocketProperties.getPort() + "],{}", e);
		}
	}

	@PreDestroy
	private void destroy() {
		if (localWebsocketConnector != null) {
			DefaultWebSocketMessageSender wsdp = ((DefaultWebSocketMessageSender) localWebsocketConnector);
			wsdp.destroy();
		}
	}

}
