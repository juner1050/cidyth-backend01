package com.hyzs.cidyth.module.dashboard.vo;

import org.apache.commons.lang3.StringUtils;

import com.hyzs.cidyth.common.enums.DemandFlowStepStatus;
import com.hyzs.cidyth.common.enums.DemandStatus;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.dashboard.entity.Backlog;
import com.hyzs.cidyth.module.uc.vo.User;

/**
 * 代办事项
 * 
 * @author jidw
 *
 */
public class ThingsWillBeDone implements java.io.Serializable {

	private static final long serialVersionUID = -341958631560233072L;

	private Backlog demand;

	private User candidateExecuteUser;// 候选操作人

	public ThingsWillBeDone() {

	}

	public ThingsWillBeDone(Backlog demand) {
		this.demand = demand;
	}

	public ThingsWillBeDone(Backlog demand, User candidateExecuteUser) {
		this.demand = demand;
		this.candidateExecuteUser = candidateExecuteUser;
	}

	// 代办事项编号
	public String getThingsId() {
		return demand == null ? null : demand.getId().toString();
	}

	// 代办事主题
	public String getThingsDesc() {
		return demand == null ? null : demand.getXqmc();
	}

	/**
	 * 需求发布人警号
	 * 
	 * @return
	 */
	public String getPublishUserId() {
		return demand == null ? null : demand.getLrry();
	}

	/**
	 * 需求发布人姓名
	 * 
	 * @return
	 */
	public String getPublishUserName() {
		return demand == null ? null : demand.getLrrymc();
	}

	public void setCandidateExecuteUser(User candidateExecuteUser) {
		this.candidateExecuteUser = candidateExecuteUser;
	}

	/**
	 * 接受人警号
	 * 
	 * @return
	 */
	public String getAcceptUserId() {
		String acctUserId = null;
		String status = demand.getQszt();
		DemandFlowStepStatus dfss = DemandFlowStepStatus.ofCode(status) == null ? DemandFlowStepStatus.ofName(status)
				: null;
		if (DemandFlowStepStatus.WAIT_FOR_SIGN.equals(dfss) || DemandFlowStepStatus.SIGNED.equals(dfss)) {
			acctUserId = demand != null ? demand.getJsrybh() : null;
		} else {
			DemandStatus ds = DemandStatus.ofCode(status) == null ? DemandStatus.ofName(status) : null;
			if (DemandStatus.INIT.equals(ds) && (this.candidateExecuteUser != null
					&& StringUtils.isNoneBlank(candidateExecuteUser.getAccount()))) {
				acctUserId = candidateExecuteUser.getAccount();
			}
		}
		return acctUserId;
	}

	/**
	 * 接收人姓名
	 * 
	 * @return
	 */
	public String getAcceptUserName() {
		String acctUserName = null;
		String status = demand.getQszt();
		DemandFlowStepStatus dfss = DemandFlowStepStatus.ofCode(status) == null ? DemandFlowStepStatus.ofName(status)
				: null;
		if (DemandFlowStepStatus.WAIT_FOR_SIGN.equals(dfss) || DemandFlowStepStatus.SIGNED.equals(dfss)) {
			acctUserName = demand != null ? demand.getJsrymc() : null;
		} else {
			DemandStatus ds = DemandStatus.ofCode(status) == null ? DemandStatus.ofName(status) : null;
			if (DemandStatus.INIT.equals(ds) && (this.candidateExecuteUser != null
					&& StringUtils.isNoneBlank(candidateExecuteUser.getAccount()))) {
				acctUserName = candidateExecuteUser.getName();
			}
		}
		return acctUserName;
	}

	/**
	 * 说明
	 * 
	 * @return
	 */
	public String getExplain() {
		String explain = null;
		long d = demand.getMilliseconedOfTimeout() == 0 ? 0 : DateUtil.toDays(demand.getMilliseconedOfTimeout());
		String status = demand.getQszt();
		DemandFlowStepStatus dfss = DemandFlowStepStatus.ofCode(status) == null ? DemandFlowStepStatus.ofName(status)
				: null;
		if (dfss != null) {
			if (DemandFlowStepStatus.WAIT_FOR_SIGN.equals(dfss)) {
				explain = "超过" + d + "天未签收";
			} else if (DemandFlowStepStatus.SIGNED.equals(dfss)) {
				explain = "超过" + d + "天未反馈";
			}
		} else {
			DemandStatus ds = DemandStatus.ofCode(status) == null ? DemandStatus.ofName(status) : null;
			if (DemandStatus.INIT.equals(ds)) {
				explain = "超过" + d + "天未指派";
			}
		}
		return explain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getThingsId() == null) ? 0 : getThingsId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThingsWillBeDone other = (ThingsWillBeDone) obj;
		if (getThingsId() == null) {
			if (other.getThingsId() != null)
				return false;
		} else if (!getThingsId().equals(other.getThingsId()))
			return false;
		return true;
	}

}
