package com.hyzs.cidyth.module.cases.vo;

import com.hyzs.cidyth.module.base.vo.CasesVO;

import java.util.List;
import java.util.Map;

public class SceneBO {
	
	/**
	 * 案件对象
	 */
	private CasesVO casesVO;

	/**
	 * 现场基本信息对象
	 */
	private SceneVO sceneVO;

	/**
	 * 现场指纹对象列表
	 */
	private List<SceneFingerPrintVO> lsSceneFingerPrintVO;

	/**
	 * 现场生物(DNA)痕迹对象列表
	 */
	private List<SceneBiologyPrintVO> lsSceneBiologyPrintVO;


	/**
	 * 现场足迹对象列表
	 */
	private List<SceneFootPrintVO> lsSceneFootPrintVO;

	/**
	 * 现场所有图片base64
	 */
	private List<Map<String, Object>> lsImage;

	public CasesVO getCasesVO() {
		return casesVO;
	}

	public void setCasesVO(CasesVO casesVO) {
		this.casesVO = casesVO;
	}

	public SceneVO getSceneVO() {
		return sceneVO;
	}

	public void setSceneVO(SceneVO sceneVO) {
		this.sceneVO = sceneVO;
	}

	public List<SceneFingerPrintVO> getLsSceneFingerPrintVO() {
		return lsSceneFingerPrintVO;
	}

	public void setLsSceneFingerPrintVO(List<SceneFingerPrintVO> lsSceneFingerPrintVO) {
		this.lsSceneFingerPrintVO = lsSceneFingerPrintVO;
	}

	public List<SceneBiologyPrintVO> getLsSceneBiologyPrintVO() {
		return lsSceneBiologyPrintVO;
	}

	public void setLsSceneBiologyPrintVO(List<SceneBiologyPrintVO> lsSceneBiologyPrintVO) {
		this.lsSceneBiologyPrintVO = lsSceneBiologyPrintVO;
	}

	public List<SceneFootPrintVO> getLsSceneFootPrintVO() {
		return lsSceneFootPrintVO;
	}

	public void setLsSceneFootPrintVO(List<SceneFootPrintVO> lsSceneFootPrintVO) {
		this.lsSceneFootPrintVO = lsSceneFootPrintVO;
	}

	public List<Map<String, Object>> getLsImage() {
		return lsImage;
	}

	public void setLsImage(List<Map<String, Object>> lsImage) {
		this.lsImage = lsImage;
	}
}
