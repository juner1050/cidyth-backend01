package com.hyzs.cidyth.common.page;

public class Page {

	/**
	 * 第几页
	 */
	private Integer pageNum;

	/**
	 * 每页多少条
	 */
	private Integer pageSize;
	
	/**
	 * 排序条件 <br/>
	 * 例如："user_name desc"
	 */
	private String orderBy;

	public Integer getPageNum() {
		return (pageNum == null || pageNum < 1) ? 1 : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return (pageSize == null || pageSize < 1) ? 10 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
}
