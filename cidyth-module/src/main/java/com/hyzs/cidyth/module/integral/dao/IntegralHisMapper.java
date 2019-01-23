package com.hyzs.cidyth.module.integral.dao;

import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.vo.IntegralHisRank;
import com.hyzs.cidyth.module.integral.vo.IntegralHisVO;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@DataSourceName(name = "masterSqlSessionFactory")
public interface IntegralHisMapper extends Mapper<IntegralHis> {

    List<IntegralHisVO> list(IntegralHis integralHis);

    /**
     * 获取用户的积分
     * @param integralHis 积分历史对象
     * @return
     */
    Float countScore(IntegralHis integralHis);

    /**
     * 案件积分列表
     * @param ajbh 案件编号
     * @return
     */
    List<IntegralHisVO> listCasesScore(@Param("ajbh") String ajbh);

    /**
     * 获取个人中心的破案、提案、线索、需求、信息、奖励的积分
     * @param lsNodeName
     * @param lsAwardName
     * @return
     */
    List<String> nodeScore(@Param("jlry") String jlry,@Param("lsNodeName") List<String> lsNodeName);

    List<IntegralHisRank> listRankScore();
}