package com.hyzs.cidyth.module.websocket.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface WebSocketMessage{
	@JsonIgnore
	public String fromUser();
	@JsonIgnore
	public Set<String> toUsers();
}
