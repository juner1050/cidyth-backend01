package com.hyzs.cidyth.module.cases.vo;


import com.hyzs.cidyth.module.base.entity.SceneBiologyPrint;

import java.util.Map;

public class SceneBiologyPrintVO extends SceneBiologyPrint {
  
    /**
     * 类型
     */
    private String lxCn;

    /**
     * 提取方法
     */
    private String tqffCn;

	/**
	 * base64图片
	 */
	private Map<String, Object> image;

	public String getLxCn() {
		return lxCn;
	}

	public void setLxCn(String lxCn) {
		this.lxCn = lxCn;
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