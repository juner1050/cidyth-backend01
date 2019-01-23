package com.hyzs.cidyth.module.dashboard.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 警种维度案件侦破情况
 * 
 * @author jidw
 *
 */
public class CaseSolvedOfOrgType  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8571142298307778082L;
	private int totalCase;// 案件总数
	private Map<Integer, CaseSolveOfOrgTypeRecord> records;

	public CaseSolvedOfOrgType() {
		records = new HashMap<Integer, CaseSolveOfOrgTypeRecord>();
	}

	public void setTotalCase(int totalCase) {
		this.totalCase = totalCase;
	}

	/**
	 * 总案件数
	 * 
	 * @return
	 */
	public int getTotalCase() {
		return totalCase;
	}

	/**
	 * 每个警种的案件侦破情况
	 * 
	 * @return
	 */
	public Collection<CaseSolveOfOrgTypeRecord> getRecords() {
		return records.values();
	}

	/**
	 * 累加需求数
	 * 
	 * @param orgType
	 *            警种类别代码
	 * @param orgTypeName
	 *            警种类别名称
	 * @param demand
	 *            需求数
	 */
	public void addDemand(int orgType, String orgTypeName, int demand) {
		CaseSolveOfOrgTypeRecord rec = records.get(orgType);
		if (rec == null) {
			rec = new CaseSolveOfOrgTypeRecord();
			rec.orgType = orgType;
			if (StringUtils.isNotBlank(orgTypeName)) {
				rec.orgTypeName = orgTypeName;
			}
			records.put(orgType, rec);
		}
		rec.demand = rec.demand + demand;
	}

	/**
	 * 累加签收需求数
	 * 
	 * @param orgType
	 *            警种类别代码
	 * @param orgTypeName
	 *            警种类别名称
	 * @param signedDemand
	 *            签收需求数
	 */
	public void addSignedDemand(int orgType, String orgTypeName, int signedDemand) {
		CaseSolveOfOrgTypeRecord rec = records.get(orgType);
		if (rec == null) {
			rec = new CaseSolveOfOrgTypeRecord();
			rec.orgType = orgType;
			if (StringUtils.isNotBlank(orgTypeName)) {
				rec.orgTypeName = orgTypeName;
			}
			records.put(orgType, rec);
		}
		rec.signedDemand = rec.signedDemand + signedDemand;
	}

	/**
	 * 累加回复数
	 * 
	 * @param orgType
	 *            警种类别代码
	 * @param orgTypeName
	 *            警种类别名称
	 * @param replied
	 *            回复数
	 */
	public void addReply(int orgType, String orgTypeName, int replied) {
		CaseSolveOfOrgTypeRecord rec = records.get(orgType);
		if (rec == null) {
			rec = new CaseSolveOfOrgTypeRecord();
			rec.orgType = orgType;
			if (StringUtils.isNotBlank(orgTypeName)) {
				rec.orgTypeName = orgTypeName;
			}
			records.put(orgType, rec);
		}
		rec.replied = rec.replied + replied;
	}

	class CaseSolveOfOrgTypeRecord {
		int orgType;// 机构种类代码
		String orgTypeName;// 机构种类名称

		int demand;// 需求数
		int signedDemand;// 签收需求数
		int replied;// 回复数

		public int getOrgType() {
			return orgType;
		}

		public String getOrgTypeName() {
			return orgTypeName;
		}

		public int getDemand() {
			return demand;
		}

		public int getSignedDemand() {
			return signedDemand;
		}

		public int getReplied() {
			return replied;
		}

	}
}
