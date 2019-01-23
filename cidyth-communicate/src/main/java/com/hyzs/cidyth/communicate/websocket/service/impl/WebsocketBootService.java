package com.hyzs.cidyth.communicate.websocket.service.impl;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import com.hyzs.psd.gafa.websocket.netty.boot.WebSocketServerInitializer;
import com.hyzs.psd.gafa.websocket.netty.impl.WebSocketFrameResolver;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebsocketBootService extends WebSocketServerInitializer {

	private String host;// ip
	private String port;// 端口
	private String websocketPath;

	private final Set<WebSocketFrameResolver<? extends WebSocketFrame>> dataProcessors = new HashSet<WebSocketFrameResolver<? extends WebSocketFrame>>();

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getWebsocketPath() {
		return websocketPath;
	}

	public void setWebsocketPath(String websocketPath) {
		this.websocketPath = websocketPath;
	}

	public InetSocketAddress getServerAddress() {
		int serverPort = Integer.parseInt(port);
		final InetSocketAddress address = new InetSocketAddress(host, serverPort);
		return address;
	}

	public Set<WebSocketFrameResolver<? extends WebSocketFrame>> getDataProcessors() {
		return dataProcessors;
	}

	public void putDataProcessor(WebSocketFrameResolver<? extends WebSocketFrame> dataProcessor) {
		if (dataProcessor != null) {
			dataProcessors.add(dataProcessor);
		}
	}
}
