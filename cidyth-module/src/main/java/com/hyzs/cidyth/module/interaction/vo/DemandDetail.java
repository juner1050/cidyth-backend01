package com.hyzs.cidyth.module.interaction.vo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.demand.entity.Demand;

/**
 * 需求详情列表模型
 * 
 * @author jidw
 *
 */
public class DemandDetail extends AbstractDemandDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4375903969224238930L;

	private Demand demand;// 需求;

	private boolean showClueBtn;// 该需求是否显示反馈线索按钮

	private List<CasesPartner> demandReceiver;// 需求接受人列表

	public DemandDetail setDemand(Demand demand) {
		this.demand = demand;
		return this;
	}

	public boolean isShowClueBtn() {
		return showClueBtn;
	}

	public void setShowClueBtn(boolean showClueBtn) {
		this.showClueBtn = showClueBtn;
	}

	public DemandDetail setDemandReceiver(List<CasesPartner> demandReceiver) {
		this.demandReceiver = demandReceiver;
		return this;
	}

	@Override
	public String getAjbh() {
		return StringUtils.isNotBlank(super.getAjbh()) ? super.getAjbh() : (demand == null ? null : demand.getAjbh());
	}

	/**
	 * 需求id
	 * 
	 * @return
	 */
	@Override
	public Integer getId() {
		return demand == null ? null : demand.getId();
	}

	/**
	 * 需求名称
	 * 
	 * @return
	 */
	@Override
	public String getXqmc() {
		return demand == null ? null : demand.getXqmc();
	}

	/**
	 * 需求内容
	 * 
	 * @return
	 */
	@Override
	public String getXqnr() {
		return demand == null ? null : demand.getXqnr();
	}

	/**
	 * 主办人警号
	 * 
	 * @return
	 */
	@Override
	public String getQqry() {
		return demand == null ? null : demand.getQqry();
	}

	/**
	 * 需求发起方人员姓名
	 * 
	 * @return
	 */
	@Override
	public String getLrrymc() {
		return demand == null ? null : demand.getLrrymc();
	}

	/**
	 * 需求的录入时间
	 * 
	 * @return
	 */
	@Override
	public String getLrsj() {
		return demand == null ? null : DateUtil.formatDate(demand.getLrsj(), DateUtil.Y_M_D);
	}

	@Override
	public String getContentType() {
		return ReplyTypeEnum.DEMAND.name();
	}

	@JsonIgnore
	@Override
	public Set<String> toUsers() {
		Set<String> to = new HashSet<String>();
		if (StringUtils.isNotBlank(getQqry())) {
			to.add(getQqry());
		}
		if (demand != null && StringUtils.isNotBlank(demand.getZpld())) {
			to.addAll(Sets.newHashSet(Splitter.on(",").split(demand.getZpld())));
		}
		if (CollectionUtils.isNotEmpty(demandReceiver)) {
			for (CasesPartner rec : demandReceiver) {
				if (StringUtils.isNotBlank(rec.getJybh())) {
					to.add(rec.getJybh());
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
}
