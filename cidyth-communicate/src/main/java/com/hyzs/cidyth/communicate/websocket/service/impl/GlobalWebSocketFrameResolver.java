package com.hyzs.cidyth.communicate.websocket.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.hyzs.psd.gafa.websocket.data.JsonResponseData;
import com.hyzs.psd.gafa.websocket.data.WebSocketMessage;
import com.hyzs.psd.gafa.websocket.netty.WebSocketMessageReceiver;
import com.hyzs.psd.gafa.websocket.netty.impl.WebSocketFrameResolver;

public class GlobalWebSocketFrameResolver extends WebSocketFrameResolver implements WebSocketMessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalWebSocketFrameResolver.class);

	public GlobalWebSocketFrameResolver() {
		jsonParser.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public Set<String> getSubscribeUrl() {
		return Sets.newHashSet("/global");
	}

	@Override
	protected void onRequest(WebSocketContext context) {
		StringBuilder error = new StringBuilder();
		Map<String, List<String>> requestParameters = context.getQueryparameters();
		if (requestParameters == null) {
			error.append("request parameter['account'] were missed.");
		} else {
			List<String> account = requestParameters.get("account");// 用户登录账号
			if (CollectionUtils.isEmpty(account)) {
				error.append("request parameter['account'] was missed or it's value was illegal.");
			} else {
				if (StringUtils.isBlank(account.get(0))) {
					error.append("request parameter['account'] was missed or it's value was illegal.");
				}
			}
		}
		if (error.length() > 0) {
			JsonResponseData jd = JsonResponseData.error();
			jd.setMessage(error.toString());
			try {
				String json = jsonParser.writeValueAsString(jd);
				logger.error(json);
				context.httpResponse(json, "application/json;charset=UTF-8");
			} catch (JsonProcessingException e) {
				logger.error("{}", e);
				e.printStackTrace();
			}
		} else {
			String account = requestParameters.get("account").get(0);
			logger.info("login account====>" + account);
			context.setChannelAttribute(WebSocketResolverConstants.CONTEXT_USER, account);
			contextHolder.set(context);
		}
	}

	@Override
	public void onWebSocketMessageReceived(WebSocketMessage instance) {
		WebSocketContext context = contextHolder.get();
		if (instance != null && context != null) {
			if (CommunicationChannels.GLOBAL.equals(instance.getFrom())) {
				String id = context.getId();
				logger.debug(CommunicationChannels.GLOBAL+" Context Id " + id);
				Set<String> toUserIdSet = instance.getTo();
				if (instance.getBody() != null && CollectionUtils.isNotEmpty(toUserIdSet)) {
					final StringBuilder toUserId = new StringBuilder();
					Joiner.on(",").appendTo(toUserId, toUserIdSet);
					@SuppressWarnings("unchecked")
					Set<String> targets = context.matchedOnlines(new HashMap<String, String>() {
						{
							put(WebSocketResolverConstants.CONTEXT_USER, toUserId.toString());
						}
					});

					JsonResponseData jd = JsonResponseData.success();
					jd.setData(instance.getBody());
					try {
						String json = jsonParser.writeValueAsString(jd);
						context.send(json, targets);
					} catch (JsonProcessingException e) {
						logger.error("{}", e);
						e.printStackTrace();
					}
				}
			}
		} else {
			logger.error("Message is NULL or context is NULL.");
		}
	}

//	@Override
//	protected void offline(WebSocketContext context) {
//		contextHolder.set(null);
//	}

	private final AtomicReference<WebSocketContext> contextHolder = new AtomicReference<WebSocketContext>();
	private final ObjectMapper jsonParser = new ObjectMapper();
}
