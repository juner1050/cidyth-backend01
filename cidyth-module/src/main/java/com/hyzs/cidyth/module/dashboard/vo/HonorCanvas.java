package com.hyzs.cidyth.module.dashboard.vo;
/**
 * 荣誉墙模型
 * @author jidw
 *
 */
public class HonorCanvas implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1226475901503886602L;
	private String organizationId;//组织编号
	private String organizationName;//组织名称
	private int totalAcceptedDemand;//接受到的任务总数
	private int totalSignedDemand;//签收的任务总数
	private int totalFeedBack;//总反馈数
	private int countOfFeedBackInOneDay;//一天内反馈数
	private int countOfFeedBackIn3Day;//三天内反馈数
	private int countOfFeedBackIn30DayExceeded;//超过30反馈数
	
	private FeedBackQuality feedBackQuality;//反馈质量
	
	private int compositiveRank;//综合排名

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getTotalAcceptedDemand() {
		return totalAcceptedDemand;
	}

	public void setTotalAcceptedDemand(int totalAcceptedDemand) {
		this.totalAcceptedDemand = totalAcceptedDemand;
	}

	public int getTotalSignedDemand() {
		return totalSignedDemand;
	}

	public void setTotalSignedDemand(int totalSignedDemand) {
		this.totalSignedDemand = totalSignedDemand;
	}

	public int getTotalFeedBack() {
		return totalFeedBack;
	}

	public void setTotalFeedBack(int totalFeedBack) {
		this.totalFeedBack = totalFeedBack;
	}

	public int getCountOfFeedBackInOneDay() {
		return countOfFeedBackInOneDay;
	}

	public void setCountOfFeedBackInOneDay(int countOfFeedBackInOneDay) {
		this.countOfFeedBackInOneDay = countOfFeedBackInOneDay;
	}

	public int getCountOfFeedBackIn3Day() {
		return countOfFeedBackIn3Day;
	}

	public void setCountOfFeedBackIn3Day(int countOfFeedBackIn3Day) {
		this.countOfFeedBackIn3Day = countOfFeedBackIn3Day;
	}

	public int getCountOfFeedBackIn30DayExceeded() {
		return countOfFeedBackIn30DayExceeded;
	}

	public void setCountOfFeedBackIn30DayExceeded(int countOfFeedBackIn30DayExceeded) {
		this.countOfFeedBackIn30DayExceeded = countOfFeedBackIn30DayExceeded;
	}

	public FeedBackQuality getFeedBackQuality() {
		return feedBackQuality;
	}

	public void setFeedBackQuality(FeedBackQuality feedBackQuality) {
		this.feedBackQuality = feedBackQuality;
	}

	public int getCompositiveRank() {
		return compositiveRank;
	}

	public void setCompositiveRank(int compositiveRank) {
		this.compositiveRank = compositiveRank;
	}
}
