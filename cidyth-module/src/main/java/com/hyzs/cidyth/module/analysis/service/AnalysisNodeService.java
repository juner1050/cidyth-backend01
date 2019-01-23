package com.hyzs.cidyth.module.analysis.service;

import com.hyzs.cidyth.module.analysis.entity.AnalysisNode;
import com.hyzs.cidyth.module.uc.vo.User;

import java.util.List;

public interface AnalysisNodeService {

    /**
     * 获取我待办节点
     * @param account
     * @param xsbh
     * @return
     */
    AnalysisNode getMyHandleNode(String account, String xsbh);

    /**
     * 获取我已完成的最后一个节点
     * @param account
     * @param xsbh
     * @return
     */
    AnalysisNode getMyLastNode(String account, String xsbh);

    /**
     * 批量插入
     * @param lsAnalysisNode
     */
    void insertList(List<AnalysisNode> lsAnalysisNode);

    /**
     * 结束指派状态的数据（修改是否结束标记为结束状态）
     * @param xsbh
     * @return
     */
    void finishAllocate(String xsbh);

    /**
     * 是否下发研判产品线索
     * @param xsbh
     * @return
     */
    boolean isSend(String xsbh);

    /**
     * 创建领导用户数据节点
     * @param deptUsers
     */
    void saveAllocateLeader(String xsbh, User loginUser, List<User> deptUsers);

    /**
     * 创建签收人员数据节点
     * @param xsbh
     * @param loginUser
     * @param persons
     */
    void saveSignPerson(String xsbh, User loginUser, List<String> persons);

    /**
     * 签收节点数据
     * @param xsbh
     */
    void sign(String xsbh);

    /**
     * 退回节点数据
     * @param xsbh
     */
    void retreat(String xsbh);
}
