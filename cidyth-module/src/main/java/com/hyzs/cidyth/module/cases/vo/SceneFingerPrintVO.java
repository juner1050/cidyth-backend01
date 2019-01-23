package com.hyzs.cidyth.module.cases.vo;


import com.hyzs.cidyth.module.base.entity.SceneFingerPrint;

import java.util.Map;

public class SceneFingerPrintVO extends SceneFingerPrint {
   
    /**
     * 手印类型
     */
    private String sylxCn;

    /**
     * 提取方法
     */
    private String tqffCn;

    /**
     * 可靠程度
     */
    private String kkcdCn;

	/**
	 *
	 */
	private Map<String, Object> image;

	public String getSylxCn() {
		return sylxCn;
	}

	public void setSylxCn(String sylxCn) {
		this.sylxCn = sylxCn;
	}

	public String getTqffCn() {
		return tqffCn;
	}

	public void setTqffCn(String tqffCn) {
		this.tqffCn = tqffCn;
	}

	public String getKkcdCn() {
		return kkcdCn;
	}

	public void setKkcdCn(String kkcdCn) {
		this.kkcdCn = kkcdCn;
	}

	public Map<String, Object> getImage() {
		return image;
	}

	public void setImage(Map<String, Object> image) {
		this.image = image;
	}
}