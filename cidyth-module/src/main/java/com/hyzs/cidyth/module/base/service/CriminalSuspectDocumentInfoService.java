package com.hyzs.cidyth.module.base.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.hyzs.cidyth.module.base.vo.CriminalSuspectDocument;

public interface CriminalSuspectDocumentInfoService {
	/**
	 * 嫌疑人基本信息
	 * @param name 嫌疑人姓名
	 * @param idCardNo 身份证号码
	 * @return
	 */
	public Map<String,Object> loadCriminalSuspectBaseInfo(CriminalSuspectDocument csd)throws Exception;
	/**
	 * 查询嫌疑人的关系人
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadRelatedPerson(CriminalSuspectDocument csd)throws Exception;
	/**
	 * 犯罪前科
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadCriminalHistoryInfo(CriminalSuspectDocument csd)throws Exception;
	
	/**
	 * 快递（物流）信息
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadPhysicalDistributionInfo(CriminalSuspectDocument csd)throws Exception;
	
	/**
	 * 出租屋信息
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadRentalsInfo(CriminalSuspectDocument csd)throws Exception;
	/**
	 * 旅馆信息
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadHotelInfo(CriminalSuspectDocument csd)throws Exception;
	
	/**
	 * 出入境信息
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadEntryAndExistInfo(CriminalSuspectDocument csd)throws Exception;
	/**
	 * 出行轨迹
	 * @param name
	 * @param idCardNo
	 * @return
	 */
	public ImmutablePair<Long, List<Map<String, Object>>> loadTravelPathInfo(CriminalSuspectDocument csd)throws Exception;
	
}
