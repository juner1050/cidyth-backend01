package com.hyzs.cidyth.communicate.websocket.config;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:websocket.properties")
public abstract class AbstractWebSocketProperties {
	@Value("${websocket.basePath}")
	private String websocketPath = "/ws";

	public abstract String getHost();

	public abstract void setHost(String host);

	public abstract String getPort();

	public abstract void setPort(String port);

	public String getWebsocketPath() {
		return websocketPath;
	}

	public void setWebsocketPath(String websocketPath) {
		this.websocketPath = websocketPath;
	}
	
	public InetSocketAddress getServerAddress() {
		int serverPort = Integer.parseInt(getPort());
		final InetSocketAddress address = new InetSocketAddress(getHost(), serverPort);
		return address;
	}
}
