package com.hyzs.cidyth.module.demand.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import com.hyzs.cidyth.module.clue.vo.ClueVO;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
/**
 * 需求
 * @author derrick
 *
 */
public class DemandVO implements Serializable {
	
	private static final long serialVersionUID = 5002373855788205575L;

	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 案件编号
     */
    @ApiModelProperty(value="案件编号", required=true)
    private String ajbh;

    /**
     * 需求名称
     */
    @ApiModelProperty(value="需求名称", required=true)
    private String xqmc;

    /**
     * 需求人员
     */
    @ApiModelProperty(value="需求人员")
    private String qqry;
    
    /**
     * 需求人员
     */
    @ApiModelProperty(value="请求时间")
    private Date qqsj;

    /**
     * 需求单位
     */
    @ApiModelProperty(value="需求单位")
    private String qqdw;

    /**
     * 需求单位编号
     */
    @ApiModelProperty(value="请求单位编号")
    private String qqdwbh;
    
    /**
     * 接收单位
     */
    @ApiModelProperty(value="接收单位名称")
    private String jsdw;

    /**
     * 接收单位编号
     */
    @ApiModelProperty(value="接收单位编号", required=true)
    private String jsdwbh;

    /**
     * 需求类型
     */
    @ApiModelProperty(value="需求类型", required=true)
    private String xqlx;

    /**
     * 需求类型名称
     */
	@ApiModelProperty(value="需求类型")
    private String xqlxCn;

    /**
     * 需求内容
     */
    @ApiModelProperty(value="需求内容", required=true)
    private String xqnr;
    
    /**
     * 签收状态
     */
    @ApiModelProperty(value="签收状态")
	private String qszt;

	/**
	 * 签收状态名称
	 */
	@ApiModelProperty(value="签收状态")
	private String qsztCn;

	/**
     * 需求说明
     */
    @ApiModelProperty(value="需求说明")
    private String smbz;
    
    /**
     * 反馈数量
     */
	@ApiModelProperty(value="反馈数量")
    private Integer fksl;
    /**
     * 上传文件
     */
    @ApiModelProperty(value="上传文件")
    private List<AttachmentUpload> files;
    
    /**
     * 上传文件说明
     */
    @ApiModelProperty(value="上传文件说明")
    private String fileComment;


    /**
	 * 需求开始时间
	 */
    @ApiModelProperty(value="需求开始时间")
	private String beginCreateTime;

	/**
	 * 需求结束时间
	 */
    @ApiModelProperty(value="需求结束时间")
	private String endCreateTime;

	/**
	 * 时间段：三天内、三十天内、上个月、本年、去年
	 * 根据该字段的值确定如何获取时间段来查询
	 */
    @ApiModelProperty(value="时间段标识")
	private String timeSection;
	
    /**
     * 线索数量
     */
    @ApiModelProperty(value="需求的线索数量")
    private Integer clueCount;
	/**
	 * 超出天数
	 */
	@ApiModelProperty(value="超出天数")
	private Integer ccts;

	/**
	 * 指派领导编号
	 */
	private String zpld;

	/**
	 * 指派领导名称
	 */
	@ApiModelProperty(value="指派领导名称")
	private String zpldCn;

    /**
     * 录入人员
     */
    private String lrry;
    
    /**
     * 录入人员名称
     */
    private String lrrymc;
    
    /**
     * 操作状态：枚举，用于给前端判断显示【指派】【签收】【反馈】哪一个按钮
     */
	@ApiModelProperty(value="操作状态")
    private String operationStatus;

	/**
	 * 操作状态提示
	 */
	@ApiModelProperty(value="操作状态提示")
	private String operationStatusTip;

	/**
	 * 思维导图上坐标
	 */
    @ApiModelProperty(value="需求的思维导图top坐标")
	private String mindTop;

	/**
	 * 思维导图左坐标
	 */
    @ApiModelProperty(value="需求的思维导图left坐标")
	private String mindLeft;

    @ApiModelProperty(value="指派人员名称")
    private String zprymc;

	/**
	 * 是否发送短信通知（true/false）
	 */
	@ApiModelProperty(value="是否发送短信通知（true/false）")
	private boolean sendMessage;

	/**
	 * 反馈天数
	 */
	private Integer fkts;

	/**
	 * 需求节点
	 */
	@ApiModelProperty(value="需求节点")
	private List<DemandFlowHisVO> lsDemandFlowHisVO;

	/**
	 * 线索
	 */
	@ApiModelProperty(value="线索")
	private List<ClueVO> lsClueVO;

	/**
	 * 本地案件状态
	 */
	@ApiModelProperty(value="本地案件状态")
	private String bdajstate;
	/**
	 * 需求的上传文件
	 */
	private List<Map<String, Object>> lsAttachment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getQqry() {
		return qqry;
	}

	public void setQqry(String qqry) {
		this.qqry = qqry;
	}

	public String getQqdw() {
		return qqdw;
	}

	public void setQqdw(String qqdw) {
		this.qqdw = qqdw;
	}

	public String getQqdwbh() {
		return qqdwbh;
	}

	public void setQqdwbh(String qqdwbh) {
		this.qqdwbh = qqdwbh;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}

	public String getJsdwbh() {
		return jsdwbh;
	}

	public void setJsdwbh(String jsdwbh) {
		this.jsdwbh = jsdwbh;
	}

	public String getXqlx() {
		return xqlx;
	}

	public void setXqlx(String xqlx) {
		this.xqlx = xqlx;
	}

	public String getXqnr() {
		return xqnr;
	}

	public void setXqnr(String xqnr) {
		this.xqnr = xqnr;
	}

	public String getSmbz() {
		return smbz;
	}

	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}

	public Integer getFksl() {
		return fksl;
	}

	public void setFksl(Integer fksl) {
		this.fksl = fksl;
	}

	public List<AttachmentUpload> getFiles() {
		return files;
	}

	public void setFiles(List<AttachmentUpload> files) {
		this.files = files;
	}

	public String getFileComment() {
		return fileComment;
	}

	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
	}

	public String getQszt() {
		return qszt;
	}

	public void setQszt(String qszt) {
		this.qszt = qszt;
	}

	public Integer getClueCount() {
		return clueCount;
	}

	public void setClueCount(Integer clueCount) {
		this.clueCount = clueCount;
	}

	public String getMindTop() {
		return mindTop;
	}

	public void setMindTop(String mindTop) {
		this.mindTop = mindTop;
	}

	public String getMindLeft() {
		return mindLeft;
	}

	public void setMindLeft(String mindLeft) {
		this.mindLeft = mindLeft;
	}

	public String getQsztCn() {
		return qsztCn;
	}

	public void setQsztCn(String qsztCn) {
		this.qsztCn = qsztCn;
	}

	public Date getQqsj() {
		return qqsj;
	}

	public void setQqsj(Date qqsj) {
		this.qqsj = qqsj;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}

	public String getXqlxCn() {
		return xqlxCn;
	}

	public void setXqlxCn(String xqlxCn) {
		this.xqlxCn = xqlxCn;
	}

	public String getLrry() {
		return lrry;
	}

	public void setLrry(String lrry) {
		this.lrry = lrry;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getZpld() {
		return zpld;
	}

	public void setZpld(String zpld) {
		this.zpld = zpld;
	}

	public String getZprymc() {
		return zprymc;
	}

	public void setZprymc(String zprymc) {
		this.zprymc = zprymc;
	}

	public String getZpldCn() {
		return zpldCn;
	}

	public void setZpldCn(String zpldCn) {
		this.zpldCn = zpldCn;
	}

	public Integer getCcts() {
		return ccts;
	}

	public void setCcts(Integer ccts) {
		this.ccts = ccts;
	}

	public List<Map<String, Object>> getLsAttachment() {
		return lsAttachment;
	}

	public void setLsAttachment(List<Map<String, Object>> lsAttachment) {
		this.lsAttachment = lsAttachment;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getOperationStatusTip() {
		return operationStatusTip;
	}

	public void setOperationStatusTip(String operationStatusTip) {
		this.operationStatusTip = operationStatusTip;
	}

	public List<DemandFlowHisVO> getLsDemandFlowHisVO() {
		return lsDemandFlowHisVO;
	}

	public void setLsDemandFlowHisVO(List<DemandFlowHisVO> lsDemandFlowHisVO) {
		this.lsDemandFlowHisVO = lsDemandFlowHisVO;
	}

	public Integer getFkts() {
		return fkts;
	}

	public void setFkts(Integer fkts) {
		this.fkts = fkts;
	}

	public String getBdajstate() {
		return bdajstate;
	}

	public void setBdajstate(String bdajstate) {
		this.bdajstate = bdajstate;
	}

	public List<ClueVO> getLsClueVO() {
		return lsClueVO;
	}

	public void setLsClueVO(List<ClueVO> lsClueVO) {
		this.lsClueVO = lsClueVO;
	}

}