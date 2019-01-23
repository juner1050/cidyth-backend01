package com.hyzs.cidyth.module.cases.vo;


import com.hyzs.cidyth.module.base.entity.Scene;

import java.util.List;

public class SceneVO extends Scene {
  
    /**
     * 现场条件
     */
    private String xctjCn;

    /**
     * 天气情况（选择）
     */
    private String tqqkCn;

    /**
     * 现场处置意见（选择）
     */
    private String xcczyjxzCn;

    /**
     * 保存标志
     */
    private String bcbzCn;

    /**
     * 完成标志
     */
    private String wcbzCn;

    /**
     * 合格标志
     */
    private String hgbzCn;

	public String getXctjCn() {
		return xctjCn;
	}

	public void setXctjCn(String xctjCn) {
		this.xctjCn = xctjCn;
	}

	public String getTqqkCn() {
		return tqqkCn;
	}

	public void setTqqkCn(String tqqkCn) {
		this.tqqkCn = tqqkCn;
	}

	public String getXcczyjxzCn() {
		return xcczyjxzCn;
	}

	public void setXcczyjxzCn(String xcczyjxzCn) {
		this.xcczyjxzCn = xcczyjxzCn;
	}

	public String getBcbzCn() {
		return bcbzCn;
	}

	public void setBcbzCn(String bcbzCn) {
		this.bcbzCn = bcbzCn;
	}

	public String getWcbzCn() {
		return wcbzCn;
	}

	public void setWcbzCn(String wcbzCn) {
		this.wcbzCn = wcbzCn;
	}

	public String getHgbzCn() {
		return hgbzCn;
	}

	public void setHgbzCn(String hgbzCn) {
		this.hgbzCn = hgbzCn;
	}

}