package com.hyzs.cidyth.module.personal.vo;

/**
 * 工作台排名
 * @author Administrator
 *
 */
public class PersonalRankVO {

	/**
	 * 个人工作台：主办案件
	 */
	private Integer mainHandleCount;
	
	/**
	 * 个人工作台：创建需求
	 */
	private Integer createDemandCount;
	
	/**
	 * 单位排名
	 */
	private Integer deptBureauRank;

	/**
	 * 分局排名
	 */
	private Integer branchBureauRank;

	/**
	 * 市局排名
	 */
	private Integer cityBureauRank;

	public Integer getMainHandleCount() {
		return mainHandleCount;
	}

	public void setMainHandleCount(Integer mainHandleCount) {
		this.mainHandleCount = mainHandleCount;
	}

	public Integer getCreateDemandCount() {
		return createDemandCount;
	}

	public void setCreateDemandCount(Integer createDemandCount) {
		this.createDemandCount = createDemandCount;
	}

	public Integer getDeptBureauRank() {
		return deptBureauRank;
	}

	public void setDeptBureauRank(Integer deptBureauRank) {
		this.deptBureauRank = deptBureauRank;
	}

	public Integer getBranchBureauRank() {
		return branchBureauRank;
	}

	public void setBranchBureauRank(Integer branchBureauRank) {
		this.branchBureauRank = branchBureauRank;
	}

	public Integer getCityBureauRank() {
		return cityBureauRank;
	}

	public void setCityBureauRank(Integer cityBureauRank) {
		this.cityBureauRank = cityBureauRank;
	}
	
	
}
