package com.hyzs.cidyth.module.reply.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.module.reply.entity.Reply;
import com.hyzs.cidyth.module.websocket.data.WebSocketMessage;

/**
 * 回复
 * 
 * @author jidw
 *
 */
public class Replies implements java.io.Serializable ,WebSocketMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1254838350540941441L;
	private Reply reply;

	private List<Map<String, Object>> attachments;// 附件信息

	public Replies() {
		this.reply = new Reply();
	}
	public Replies setReply(Reply reply){
		this.reply = reply;
		return this;
	}

	@JsonIgnore
	public Reply getReply() {
		return reply;
	}

	public Integer getId() {
		return reply == null ? null : reply.getId();
	}

	public void setId(Integer id) {
		if (reply != null) {
			reply.setId(id);
		}
	}

	public String getReferenceId() {
		return reply == null ? null : reply.getReferenceId();
	}

	public void setReferenceId(String referenceId) {
		if (reply != null) {
			reply.setReferenceId(referenceId);
		}
	}

	/**
	 * 回复的消息类型
	 * 
	 * @return
	 */
	public String getXxlx() {
		return reply == null ? null : (ReplyTypeEnum.ofName(reply.getXxlx()) == null ? null : reply.getXxlx());
	}

	/**
	 * 回复的消息类型
	 * 
	 * @param xxlx
	 *            回复类型,com.hyzs.cidyth.common.enums.ReplyTypeEnum
	 */
	public void setXxlx(String xxlx) {
		if (reply != null && StringUtils.isNotBlank(xxlx)
				&& null != (ReplyTypeEnum.ofName(xxlx) == null ? null : ReplyTypeEnum.ofName(xxlx))) {
			reply.setXxlx(xxlx);
		}
	}

	public String getLxbh() {
		return reply == null ? null : reply.getLxbh();
	}

	/**
	 * 发送单位编号
	 * 
	 * @return
	 */
	public String getFsdw() {
		return reply == null ? null : reply.getFsdw();
	}

	public void setFsdw(String fsdw) {
		if (reply != null) {
			reply.setFsdw(fsdw);
		}
	}

	/**
	 * 发送单位名称
	 * 
	 * @return
	 */
	public String getFsdwmc() {
		return reply == null ? null : reply.getFsdwmc();
	}

	public void setFsdwmc(String fsdwmc) {
		if (reply != null) {
			reply.setFsdwmc(fsdwmc);
		}
	}

	/**
	 * 发送人编号
	 * 
	 * @return
	 */
	public String getFsry() {
		return reply == null ? null : reply.getFsry();
	}

	public void setFsry(String fsry) {
		if (reply != null) {
			reply.setFsry(fsry);
		}
	}

	/**
	 * 发送人员姓名
	 * 
	 * @return
	 */
	public String getFsryxm() {
		return reply == null ? null : reply.getFsryxm();
	}

	public void setFsryxm(String fsryxm) {
		if (reply != null) {
			reply.setFsryxm(fsryxm);
		}
	}

	/**
	 * 发送日期
	 * 
	 * @return
	 */
	public Date getFsrq() {
		return reply == null ? null : reply.getFsrq();
	}

	/**
	 * 发送内容
	 * 
	 * @return
	 */
	public String getFsnr() {
		return reply == null ? null : reply.getFsnr();
	}

	public void setFsnr(String fsnr) {
		if (reply != null) {
			reply.setFsnr(fsnr);
		}
	}

	/**
	 * 接受人所属机构代码
	 * 
	 * @return
	 */
	public String getHfdw() {
		return reply == null ? null : reply.getHfdw();
	}

	public void setHfdw(String hfdw) {
		if (reply != null) {
			reply.setHfdw(hfdw);
		}
	}

	/**
	 * 接受人所属机构名称
	 * 
	 * @return
	 */
	public String getHfdwmc() {
		return reply == null ? null : reply.getHfdwmc();
	}

	public void setHfdwmc(String hfdwmc) {
		if (reply != null) {
			reply.setHfdwmc(hfdwmc);
		}
	}

	/**
	 * 接受人员编号
	 * 
	 * @return
	 */
	public String getHfry() {
		return reply == null ? null : reply.getHfry();
	}

	public void setHfry(String hfry) {
		if (reply != null) {
			reply.setHfry(hfry);
		}
	}

	/**
	 * 接受人姓名
	 * 
	 * @return
	 */
	public String getHfryxm() {
		return reply == null ? null : reply.getHfryxm();
	}

	public void setHfryxm(String hfryxm) {
		if (reply != null) {
			reply.setHfryxm(hfryxm);
		}
	}

	/**
	 * 修改人代码
	 * 
	 * @return
	 */
	public String getXgry() {
		return reply == null ? null : reply.getXgry();
	}

	public void setXgry(String xgry) {
		if (reply != null) {
			reply.setXgry(xgry);
		}
	}

	/**
	 * 修改人姓名
	 * 
	 * @return
	 */
	public String getXgryxm() {
		return reply == null ? null : reply.getXgryxm();
	}

	public void setXgryxm(String xgryxm) {
		if (reply != null) {
			reply.setXgryxm(xgryxm);
		}
	}

	/**
	 * 修改时间
	 * 
	 * @return
	 */
	public Date getXgsj() {
		return reply == null ? null : reply.getXgsj();
	}

	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}

	public Replies setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
		return this;
	}

	/**
	 * 上传附件
	 */
	private List<AttachmentUpload> files;

	public List<AttachmentUpload> getFiles() {
		return files;
	}

	public void setFiles(List<AttachmentUpload> files) {
		this.files = files;
	}
	
	
	@JsonIgnore
	@Override
	public String fromUser() {
		return getFsry();
	}
	@JsonIgnore
	@Override
	public Set<String> toUsers() {
		Set<String> to = Sets.newHashSet();
		if(StringUtils.isNotBlank(fromUser())){
			to.add(fromUser());
		}
		if(StringUtils.isNotBlank(getHfry())){
			to.add(getHfry());
		}
		return to;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String topMessageIdentifer;// 顶层消息标识符

	public String getTopMessageIdentifer() {
		return topMessageIdentifer;
	}
	public void setTopMessageIdentifer(String topMessageIdentifer) {
		this.topMessageIdentifer = topMessageIdentifer;
	}
}
