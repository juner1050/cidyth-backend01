package com.hyzs.cidyth.communicate.websocket.config.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hyzs.cidyth.communicate.websocket.config.AbstractWebSocketProperties;

@Component("globalWebSocketProperties")
public class GlobalWebSocketProperties extends AbstractWebSocketProperties {
	@Value("${websocket.host}")
	private String host = "localhost";// ip
	@Value("${websocket.global.port}")
	private String port = "9527";// 端口

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
