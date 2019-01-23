package com.hyzs.cidyth.module.personal.vo;

import java.util.List;

import com.hyzs.cidyth.module.uc.vo.User;

public class PersonalBO {

	/**
	 * 个人工作台：我的信息
	 */
	private User user;
	
	/**
	 * 个人工作台：排名情况
	 */
	private PersonalRankVO personalRankVO;
	
	/**
	 * 个人工作台：我的请求
	 */
	private List<PersonalDemandVO> lsPersonalMyDemand;
	
	/**
	 * 个人工作台：待办任务
	 */
	private List<PersonalDemandVO> lsPersonalHandleDemand;
	
	/**
	 * 本月合成作战案件情况
	 */
	private List<PersonalCasesVO> lsPersonalCases;
	
	/**
	 * 本月用户点评情况
	 */
	private List<PersonalDemandVO> lsPersonalMonthFeedBacked;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PersonalDemandVO> getLsPersonalMyDemand() {
		return lsPersonalMyDemand;
	}

	public void setLsPersonalMyDemand(List<PersonalDemandVO> lsPersonalMyDemand) {
		this.lsPersonalMyDemand = lsPersonalMyDemand;
	}

	public List<PersonalDemandVO> getLsPersonalHandleDemand() {
		return lsPersonalHandleDemand;
	}

	public void setLsPersonalHandleDemand(List<PersonalDemandVO> lsPersonalHandleDemand) {
		this.lsPersonalHandleDemand = lsPersonalHandleDemand;
	}

	public List<PersonalCasesVO> getLsPersonalCases() {
		return lsPersonalCases;
	}

	public void setLsPersonalCases(List<PersonalCasesVO> lsPersonalCases) {
		this.lsPersonalCases = lsPersonalCases;
	}

	public List<PersonalDemandVO> getLsPersonalMonthFeedBacked() {
		return lsPersonalMonthFeedBacked;
	}

	public void setLsPersonalMonthFeedBacked(List<PersonalDemandVO> lsPersonalMonthFeedBacked) {
		this.lsPersonalMonthFeedBacked = lsPersonalMonthFeedBacked;
	}

	public PersonalRankVO getPersonalRankVO() {
		return personalRankVO;
	}

	public void setPersonalRankVO(PersonalRankVO personalRankVO) {
		this.personalRankVO = personalRankVO;
	}

}
