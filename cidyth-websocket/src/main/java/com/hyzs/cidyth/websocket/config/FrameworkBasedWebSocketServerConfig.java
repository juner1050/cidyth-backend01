package com.hyzs.cidyth.websocket.config;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.hyzs.cidyth.communicate.websocket.config.AbstractWebSocketProperties;
import com.hyzs.cidyth.communicate.websocket.service.impl.GlobalWebSocketFrameResolver;
import com.hyzs.cidyth.communicate.websocket.service.impl.JacksonBasedWebSocketMessageCodec;
import com.hyzs.cidyth.communicate.websocket.service.impl.LocalWebSocketFrameResolver;
import com.hyzs.cidyth.communicate.websocket.service.impl.WebsocketBootService;
import com.hyzs.psd.gafa.websocket.netty.WebSocketMessageReceiver;
import com.hyzs.psd.gafa.websocket.netty.boot.WebSocketServerInitializer;
import com.hyzs.psd.gafa.websocket.netty.impl.WebSocketFrameResolver;
import com.hyzs.psd.gafa.websocket.netty.impl.WebSocketMessageDeliver;

@Component("frameworkBasedWebSocketServerConfig")
public class FrameworkBasedWebSocketServerConfig implements InitializingBean, DisposableBean {
	private static final Logger logger = LoggerFactory.getLogger(FrameworkBasedWebSocketServerConfig.class);

	@Autowired
	@Qualifier("globalWebSocketProperties")
	private AbstractWebSocketProperties globalWebSocketProperties;

	@Autowired
	@Qualifier("localWebSocketProperties")
	private AbstractWebSocketProperties localWebSocketProperties;

	private Collection<WebSocketServerInitializer> websoketServers;

	public FrameworkBasedWebSocketServerConfig() {
		websoketServers = Sets.<WebSocketServerInitializer>newConcurrentHashSet();
	}

	@Override
	public void destroy() throws Exception {
		if (CollectionUtils.isNotEmpty(websoketServers)) {
			for (WebSocketServerInitializer server : websoketServers) {
				if (server != null) {
					WebsocketBootService s = (WebsocketBootService) server;
					server.destroy(s.getServerAddress());
				}
			}
			websoketServers.clear();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (websoketServers == null) {
			websoketServers = Sets.<WebSocketServerInitializer>newConcurrentHashSet();
		}
		WebSocketMessageDeliver globalMessageDeliver = new WebSocketMessageDeliver();
		globalMessageDeliver.setWebSocketMessageCodec(new JacksonBasedWebSocketMessageCodec());
		WebSocketMessageReceiver globalWebSocketFrameResolver = new GlobalWebSocketFrameResolver();
		globalMessageDeliver.putWebSocketMessageReceiver(globalWebSocketFrameResolver);
		WebsocketBootService globalServer = new WebsocketBootService();
		globalServer.init(globalWebSocketProperties.getServerAddress(), globalWebSocketProperties.getWebsocketPath(),
				Sets.newHashSet(globalMessageDeliver, (WebSocketFrameResolver) globalWebSocketFrameResolver));

		
		WebSocketMessageDeliver localMessageDeliver = new WebSocketMessageDeliver();
		localMessageDeliver.setWebSocketMessageCodec(new JacksonBasedWebSocketMessageCodec());
		WebSocketMessageReceiver localWebSocketFrameResolver = new LocalWebSocketFrameResolver();
		localMessageDeliver.putWebSocketMessageReceiver(localWebSocketFrameResolver);
		WebsocketBootService localServer = new WebsocketBootService();
		localServer.init(localWebSocketProperties.getServerAddress(), localWebSocketProperties.getWebsocketPath(),
				Sets.newHashSet(localMessageDeliver, (WebSocketFrameResolver) localWebSocketFrameResolver));

		websoketServers.add(globalServer);
		websoketServers.add(localServer);
	}
}
