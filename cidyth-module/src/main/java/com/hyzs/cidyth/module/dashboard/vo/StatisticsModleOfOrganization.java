package com.hyzs.cidyth.module.dashboard.vo;
/**
 * 统计模型
 * @author jidw
 *
 */
public class StatisticsModleOfOrganization  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 672015989904729477L;
	private String organizationCode;//机构代码
	private String organizationName;//机构名称
	
	private int amount;//数量

	public StatisticsModleOfOrganization(){
		
	}
	public StatisticsModleOfOrganization(String code){
		this.organizationCode=code;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organizationCode == null) ? 0 : organizationCode.hashCode());
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
		StatisticsModleOfOrganization other = (StatisticsModleOfOrganization) obj;
		if (organizationCode == null) {
			if (other.organizationCode != null)
				return false;
		} else if (!organizationCode.equals(other.organizationCode))
			return false;
		return true;
	}
}
