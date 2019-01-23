package com.hyzs.cidyth.module.cases.vo;


import com.hyzs.cidyth.module.base.entity.SceneFootPrint;

import java.util.Map;

public class SceneFootPrintVO extends SceneFootPrint {
  
    /**
     * 足迹类型
     */
    private String zjlxCn;

    /**
     * 提取方法
     */
    private String tqffCn;

	/**
	 * base64图片
	 */
	private Map<String, Object> image;

	public String getZjlxCn() {
		return zjlxCn;
	}

	public void setZjlxCn(String zjlxCn) {
		this.zjlxCn = zjlxCn;
	}

	public String getTqffCn() {
		return tqffCn;
	}

	public void setTqffCn(String tqffCn) {
		this.tqffCn = tqffCn;
	}

	public Map<String, Object> getImage() {
		return image;
	}

	public void setImage(Map<String, Object> image) {
		this.image = image;
	}
}