package com.hyzs.cidyth.module.cases.vo;

import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.vo.CasesVO;

import java.util.List;

public class CasesBO {

	/**
	 * 案件基本信息
	 */
	private CasesVO casesVO;
	/**
	 * 案件-涉案物品
	 */
	private List<CasesGoodsVO> lsCasesGoodsVO;
	/**
	 * 案件-法律文书
	 */
	private List<CasesLawBookVO> lsCasesLawBookVO;
	/**
	 * 案件-笔录
	 */
	private List<CasesRecordVO> lsCasesRecordVO;
	/**
	 * 案件-案件嫌疑人员
	 */
	private List<CasesSuspectVO> lsCasesSuspectVO;
	/**
	 * 案件-案件报案人受害人
	 */
	private List<CasesInformantVO> lsCasesInformantVO;
	/**
	 * 案件-比中信息
	 */
	private List<CasesComparison> lsCasesComparison;
	/**
	 * 案件-现场基本信息
	 */
	private List<SceneBO> lsSceneBO;

	public CasesVO getCasesVO() {
		return casesVO;
	}
	public void setCasesVO(CasesVO casesVO) {
		this.casesVO = casesVO;
	}
	public List<CasesGoodsVO> getLsCasesGoodsVO() {
		return lsCasesGoodsVO;
	}
	public void setLsCasesGoodsVO(List<CasesGoodsVO> lsCasesGoodsVO) {
		this.lsCasesGoodsVO = lsCasesGoodsVO;
	}
	public List<CasesRecordVO> getLsCasesRecordVO() {
		return lsCasesRecordVO;
	}
	public void setLsCasesRecordVO(List<CasesRecordVO> lsCasesRecordVO) {
		this.lsCasesRecordVO = lsCasesRecordVO;
	}
	public List<CasesSuspectVO> getLsCasesSuspectVO() {
		return lsCasesSuspectVO;
	}
	public void setLsCasesSuspectVO(List<CasesSuspectVO> lsCasesSuspectVO) {
		this.lsCasesSuspectVO = lsCasesSuspectVO;
	}
	public List<CasesInformantVO> getLsCasesInformantVO() {
		return lsCasesInformantVO;
	}
	public void setLsCasesInformantVO(List<CasesInformantVO> lsCasesInformantVO) {
		this.lsCasesInformantVO = lsCasesInformantVO;
	}

	public List<CasesComparison> getLsCasesComparison() {
		return lsCasesComparison;
	}

	public void setLsCasesComparison(List<CasesComparison> lsCasesComparison) {
		this.lsCasesComparison = lsCasesComparison;
	}

	public List<CasesLawBookVO> getLsCasesLawBookVO() {
		return lsCasesLawBookVO;
	}
	public void setLsCasesLawBookVO(List<CasesLawBookVO> lsCasesLawBookVO) {
		this.lsCasesLawBookVO = lsCasesLawBookVO;
	}

	public List<SceneBO> getLsSceneBO() {
		return lsSceneBO;
	}

	public void setLsSceneBO(List<SceneBO> lsSceneBO) {
		this.lsSceneBO = lsSceneBO;
	}
}
