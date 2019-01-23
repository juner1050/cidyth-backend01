package com.hyzs.cidyth.module.appraise.vo;

import java.util.Date;

import com.hyzs.cidyth.common.module.CommonParameter;
import com.hyzs.cidyth.module.appraise.entity.ClueAppraise;

/**
 * 评价
 * 
 * @author derrick
 *
 */
public class Appraise implements java.io.Serializable {

	private static final long serialVersionUID = 166360754208464860L;

	public ClueAppraise clueEntity;

	public Appraise() {
		this.clueEntity = new ClueAppraise();
	}

	public Appraise(ClueAppraise clueEntity) {
		this.clueEntity = clueEntity;
	}

	public Integer getId() {
		return clueEntity == null ? null : clueEntity.getId();
	}

	/**
	 * 目标id
	 * 
	 * @return
	 */
	public String getTargetId() {
		return clueEntity == null ? null
				: clueEntity.getClueId() == null ? null : clueEntity.getClueId().toString();
	}
	/**
	 * 目标主键id
	 * 
	 * @param targetId
	 */
	public void setTargetId(Integer targetId) {
		
		clueEntity.setClueId(targetId);
	}
	/**
	 * 评价人单位编号
	 * 
	 * @return
	 */
	public String getPfrdwbh() {
		return clueEntity == null ? null : clueEntity.getPfrdwbh();
	}

	/**
	 * 评价人单位名称
	 * 
	 * @return
	 */
	public String getPfrdwmc() {
		return clueEntity == null ? null : clueEntity.getPfrdwmc();
	}

	/**
	 * 评价人警号
	 * 
	 * @return
	 */
	public String getPfrbh() {
		return clueEntity == null ? null : clueEntity.getPfrbh();
	}

	/**
	 * 评价人心姓名
	 * 
	 * @return
	 */
	public String getPfrxm() {
		return clueEntity == null ? null : clueEntity.getPfrxm();
	}

	/**
	 * 评价时间
	 * 
	 * @return
	 */
	public Date getLrsj() {
		return clueEntity == null ? null : clueEntity.getLrsj();
	}

	/**
	 * 分值
	 * 
	 * @return
	 */
	public Float getPffz() {
		return clueEntity == null ? null : clueEntity.getPffz();
	}
	/**
	 * 评分分值
	 * 
	 * @param pffz
	 */
	public void setPffz(Float pffz) {
		clueEntity.setPffz(pffz);
	}
	/**
	 * 评价内容
	 * 
	 * @return
	 */
	public String getPfnr() {
		return clueEntity == null ? null : clueEntity.getPfnr();
	}
	/**
	 * 评分内容
	 * 
	 * @param pfnr
	 */
	public void setPfnr(String pfnr) {
		clueEntity.setPfnr(pfnr);
	}

	public class ClueAppraiseHolder extends CommonParameter {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7496788955099694465L;

		public void setTaregeType(String taregeType) {
			put("taregeType", taregeType);
		}
	}
}
