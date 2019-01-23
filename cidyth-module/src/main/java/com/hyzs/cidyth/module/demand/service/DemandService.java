package com.hyzs.cidyth.module.demand.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.vo.DemandBatchVO;
import com.hyzs.cidyth.module.demand.vo.DemandIndexVO;
import com.hyzs.cidyth.module.demand.vo.DemandVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface DemandService {

    /**
     * 我发起的需求
     * @param demandVO 需求对象
     * @param page 分页对象
     * @return
     */
	PageInfo<DemandVO> list(DemandVO demandVO, Page page);

	/**
	 * 我待办的需求
	 * @param demandVO
	 * @param page
	 * @return
	 */
	PageInfo<DemandVO> waitHandle(DemandVO demandVO, Page page);

	/**
	 * 需求新增（同时处理需求、上传线索、附件对象）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param demandBatchVO 需求视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.demand.entity.DemandVO>
	 */
	Map<String, Object> insert(DemandBatchVO demandBatchVO);

	/**
	 * 需求新增（同时处理需求、上传线索、附件对象）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param demandVO 需求视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.demand.entity.DemandVO>
	 */

	Map<String, Object> insert(DemandVO demandVO);

	/**
	 * 需求新增（同时处理需求、上传线索、附件对象）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param id 需求id
	 * @return java.util.List<com.hyzs.cidyth.module.demand.entity.DemandVO>
	 */
	DemandVO detail(Integer id);


	/**
     * 根据案件编号查询总数
     * @param ajbh 案件编号
     * @return
     */
	int getCountByAjbh(String ajbh);
	/**
     * 修改需求处理状态
     * @param id 需求id
     * @param qszt 签收状态（枚举名）
     * @return
     */
	void updateQszt(Integer id, String qszt, String ajbh);

	/**
     * 获取一条需求
     * @param demand 需求对象
     * @return
     */
	Demand selectOne(Demand demand);

	/**
	 * 指派时：修改紧急程度状态
	 * @param id 需求id
	 * @param jjcd 紧急程度
	 * @param ajbh 案件编号
	 */
	void updateJjcd(Integer id, Integer jjcd, String ajbh);

	/**
	 * 获取案件下的所有该单位接收的需求状态不等于【未指派】和【已指派】的所有数据
	 * @param ajbh 案件编号
	 * @param jsdwbh 接收单位编号
	 * @return
	 */
	List<Demand> listExecuteDemand(String ajbh, String jsdwbh);

	/**
	 * 判断需求的类型、内容是否存在
	 * @param demand
	 * @return
	 */
	List<Demand> checkDemandInfoExists(List<Demand> lsDemand);

	/**
	 * 首页：待办事项
	 * @return
	 */
	Map<String, Object> waitHandler();

	/**
	 * 首页：待办事项详情
	 * @return
	 */
	PageInfo<DemandIndexVO> waitAllocate(Page page);

	/**
	 * 首页：需求-线索
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	Map<String, Object> demandClue(String kssj, String jssj);

	/**
	 *
	 * @param kssj
	 * @param jssj
	 * @param policeType
	 * @param page
	 * @return
	 */
	PageInfo<DemandVO> demandClueDetail(String kssj, String jssj, String policeType, Page page);

}
