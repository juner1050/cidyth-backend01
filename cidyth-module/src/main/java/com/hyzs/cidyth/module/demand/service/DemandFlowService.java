package com.hyzs.cidyth.module.demand.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.enums.DemandFlowStepStatus;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.cidyth.module.demand.vo.DemandFlowHisVO;
import com.hyzs.cidyth.module.demand.vo.DemandIndexVO;

import java.util.List;
import java.util.Map;

public interface DemandFlowService {
	/**
	 * 指定的角色是否有有指派权限
	 * @param sysInfo 系统信息
	 * @param roleIds
	 * @return
	 */
	public boolean isAllocateRole(Constant.SystemInfo sysInfo);

	/**
     * 指派需求
     * @author 陈铭
     * @date 2018-04-25 17:06:05
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
	public void insert(DemandFlowHisVO demandFlowHisVO);

	/**
	 * 修改节点为签收状态
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param xqid 需求id
	 * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
	 */
	public void sign(DemandFlowHisVO demandFlowHisVO);
	/**
	 * 修改节点为退回状态
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param demandFlowHisVO 需求节点对象
	 * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
	 */
	public void retreat(DemandFlowHisVO demandFlowHisVO);
	/**
	 * 需求延期申请
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param demandFlowHisVO 需求节点对象
	 * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
	 */
	public void delayApply(DemandFlowHisVO demandFlowHisVO);
	/**
     * 修改节点为反馈状态
     * @author 陈铭
     * @date 2018-04-25 17:06:05
     * @param xqid 需求id
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
	public void feedback(Integer xqid, String ajbh);
	/**
	 * 根据需求id、接收人员编号统计数量
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param xqid 需求id
	 * @return Integer
	 */
	public Integer countByAllocateSelf(Integer xqid, String jsrybh);
	/**
	 * 根据需求id、接收人员编号获取需求节点
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param xqid 需求id
	 * @return Integer
	 */
	public DemandFlowHis getDemandFlowHis(Integer xqid, String jsrybh);
	/**
	 * 根据需求id、
	 * @param xqid 需求id
	 * @param status 需求流转节点状态
	 * @return
	 */
	public List<DemandFlowHis> getDemandFlowHis(Integer xqid,DemandFlowStepStatus ... status);
	/**
	 * 根据需求id获取所有需求节点
	 * @author 陈铭
	 * @date 2018-04-25 17:06:05
	 * @param xqid 需求id
	 * @return Integer
	 */
	public List<DemandFlowHisVO> listMyAllocatedFlow(Integer xqid);

	/**
	 * 首页：超期反馈
	 * @return
	 */
	List<Map<String, Object>> overdueFeedback();

	/**
	 * 首页：超期反馈详情
	 * @param page
	 * @return
	 */
	PageInfo<Map<String, Object>> overdueFeedbackDetail(String prefix_code, Page page);

	/**
	 * 首页：待办事项
	 * @param qszt
	 * @return
	 */
	//PageInfo<DemandIndexVO> waitHandlerDetail(String qszt, Page page);

	/**
	 * 根据需求id，接收人员警号判断是否超期
	 * @param xqid
	 * @param jsrybh
	 * @return 返回true：已超期，false：未超期
	 */
	boolean isDelayProcess(Integer xqid, String jsrybh);

	/**
	 * 根据需求id，接收人员警号判断超期多少天
	 * @param xqid
	 * @param jsrybh
	 * @return
	 */
	int delayCount(Integer xqid, String jsrybh);

	/**
	 * 首页：待签收
	 * @param page
	 * @return
	 */
	PageInfo<DemandIndexVO> waitSign(Page page);
	/**
	 * 首页：待反馈
	 * @param page
	 * @return
	 */
	PageInfo<DemandIndexVO> waitFeedBack(Page page);
}
