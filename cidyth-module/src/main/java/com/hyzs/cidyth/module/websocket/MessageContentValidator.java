package com.hyzs.cidyth.module.websocket;
/**
 * 消息内容验证器
 * @author derrick
 *
 * @param <C>
 */
public interface MessageContentValidator<C> {
	public String name();//名称
	public void validate(C content) throws Exception;//验证方法
}
