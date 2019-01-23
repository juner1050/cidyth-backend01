package com.hyzs.cidyth.module.interaction.vo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.msg.entity.InfoAcceptance;

/**
 * 信息
 * 
 * @author derrick
 *
 */
public class InfoDetail extends AbstractDemandDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7911005543038306735L;

	private Info info;
		
	private List<InfoAcceptance> infoAcceptances;//消息接受列表
	
	public InfoDetail setInfo(Info info) {
		this.info = info;
		return this;
	}
	public InfoDetail setInfoAcceptances(List<InfoAcceptance> infoAcceptances){
		this.infoAcceptances = infoAcceptances;
		return this;
	}

	private Integer approveCount;

	/**
	 * 消息id
	 * 
	 * @return
	 */
	@Override
	public Integer getId() {
		return info == null ? null : info.getId();
	}

	@Override
	public String getXqmc() {
		return info == null ? null : info.getXxzt();
	}

	@Override
	public String getXqnr() {
		return info == null ? null : info.getFbnr();
	}

	@Override
	public String getQqry() {
		return info == null ? null : info.getFbry();
	}

	@Override
	public String getLrrymc() {
		return info == null ? null : info.getLrrymc();
	}

	@Override
	public String getLrsj() {
		return info == null ? null : DateUtil.formatDate(info.getFbrq(), DateUtil.Y_M_D);
	}

	@Override
	public String getContentType() {
		return ReplyTypeEnum.INFO.name();
	}
	
	@JsonIgnore
	@Override
	public Set<String> toUsers() {
		Set<String> to = new HashSet<String>();
		if (StringUtils.isNotBlank(getQqry())) {
			to.add(getQqry());
		}
		if (infoAcceptances != null && CollectionUtils.isNotEmpty(infoAcceptances)) {
			for(InfoAcceptance accpt:infoAcceptances){
				if(StringUtils.isNotBlank(accpt.getJsrybh())){
					to.add(accpt.getJsrybh());
				}
			}
		}
		List<Map<String, Object>> interactions = getContentList();
		if (CollectionUtils.isNotEmpty(getContentList())) {
			for (Map<String, Object> iner : interactions) {
				Object f = iner.get("fromUserId");
				Object t = iner.get("toUserId");
				if (f != null && StringUtils.isNotBlank(f.toString())) {
					to.add(f.toString());
				}
				if (t != null && StringUtils.isNotBlank(t.toString())) {
					to.add(t.toString());
				}
			}
		}
		return to;
	}

	public Integer getApproveCount() {
		return approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}

}
