package com.hyzs.cidyth.module.base.vo;

import com.hyzs.cidyth.common.page.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CriminalSuspectDocument implements java.io.Serializable {

	private static final long serialVersionUID = -7544582021688759298L;

	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("嫌疑人身份标识(身份证)")
	private String idCardNo;
	@ApiModelProperty("手机/电话号码")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// --------------------------//
	private Page page = new Page();

	public Page page() {
		return this.page;
	}

	public void setPageNum(Integer pageNum) {
		this.page.setPageNum(pageNum);
	}

	public void setPageSize(Integer pageSize) {
		this.page.setPageSize(pageSize);
	}
}
