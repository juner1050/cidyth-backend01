package com.hyzs.cidyth.common.module;

import java.util.Map;

public abstract class CommonParameter extends java.util.HashMap<String, Object> implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5052850782749317800L;

	/**
	 * 转换成Map
	 * 
	 * @return
	 */
	public final Map<String,Object> self() {
		return this;
	}
}
