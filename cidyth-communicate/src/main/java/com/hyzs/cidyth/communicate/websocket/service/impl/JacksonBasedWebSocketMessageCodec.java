package com.hyzs.cidyth.communicate.websocket.service.impl;

import java.nio.charset.Charset;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyzs.psd.gafa.websocket.data.WebSocketMessage;
import com.hyzs.psd.gafa.websocket.netty.WebSocketMessageCodec;

public class JacksonBasedWebSocketMessageCodec implements WebSocketMessageCodec {
	private final ObjectMapper jsonParser = new ObjectMapper();

	public JacksonBasedWebSocketMessageCodec() {
		jsonParser.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public byte[] convert(WebSocketMessage msg) throws Exception {
		byte[] result = null;
		if (msg != null && msg.getBody() != null) {
			String json;
			try {
				json = jsonParser.writeValueAsString(msg);
				result = json.getBytes(Charset.forName("UTF-8"));
			} catch (Exception e) {
				throw e;
			}
		}
		return result == null ? new byte[] {} : result;
	}

	@Override
	public WebSocketMessage convert(byte[] msg) throws Exception {
		WebSocketMessage wsms = null;
		if (msg != null && msg.length > 0) {
			try {
				wsms = jsonParser.readValue(msg, WebSocketMessage.class);
			} catch (Exception e) {
				throw e;
			}
		}
		return wsms;
	}

}
