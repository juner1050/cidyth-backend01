package com.hyzs.cidyth.module.mind.vo;


import java.util.Date;

import org.apache.tools.ant.util.DateUtils;

public class MindVO {

	/**
	 * 思维导图id标识：TableTypeEnum名 + 横杠 + 具体实体id
	 */
	private String id;
	/**
	 * 思维导图标题
	 */
	private String title;
	/**
	 * 思维导图内容
	 */
	private String content;
	/**
	 * 思维导图分类：TableTypeEnum名
	 */
	private String type;
	/**
	 * 思维导图时间
	 */
	private String time;
	/**
	 * 思维导图top坐标
	 */
	private String top;
	/**
	 * 思维导图left坐标
	 */
	private String left;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	
	
}