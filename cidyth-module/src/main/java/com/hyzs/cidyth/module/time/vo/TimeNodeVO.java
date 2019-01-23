package com.hyzs.cidyth.module.time.vo;

import com.hyzs.cidyth.module.time.entity.TimeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@ApiModel("TimeNodeVO（时间轴）")
public class TimeNodeVO extends TimeNode {

	/**
	 * 业务类型名称
	 */
	@ApiModelProperty("业务类型")
	public String referenceTypeCn;

	/**
	 * 案件名称
	 */
	@ApiModelProperty("案件名称")
	public String ajmc;

	/**
	 * 主要案情
	 */
	@ApiModelProperty("主要案情")
	public String zyaq;

	/**
	 * 内容类型：点击时间轴的【需求】【反馈线索】【信息】时，依据切换到具体内容的格式。
	 */
	@ApiModelProperty("内容类型")
	public String contentType;

	/**
	 * 附件
	 */
	@ApiModelProperty("附件")
	private List<Map<String, Object>> lsAttachment;

	public String getReferenceTypeCn() {
		return referenceTypeCn;
	}

	public void setReferenceTypeCn(String referenceTypeCn) {
		this.referenceTypeCn = referenceTypeCn;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<Map<String, Object>> getLsAttachment() {
		return lsAttachment;
	}

	public void setLsAttachment(List<Map<String, Object>> lsAttachment) {
		this.lsAttachment = lsAttachment;
	}

	public String getAjmc() {
		return ajmc;
	}

	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}

	public String getZyaq() {
		return zyaq;
	}

	public void setZyaq(String zyaq) {
		this.zyaq = zyaq;
	}
}