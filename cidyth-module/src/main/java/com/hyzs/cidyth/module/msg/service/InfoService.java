package com.hyzs.cidyth.module.msg.service;

import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.msg.vo.InfoVO;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface InfoService {

    /**
     * 新增发布信息
     * @author 陈铭
     * @date 2018-04-17 09:23:11
     * @param infoVO 信息视图对象
     * @return
     */
    Map<String, Object> insert(InfoVO infoVO);
    /**
     * 新增发布信息
     * @author 陈铭
     * @date 2018-04-17 09:23:11
     * @param id 信息id
     * @return
     */
    InfoVO detail(Integer id);
    /**
     * 获取发布信息
     * @author 陈铭
     * @date 2018-04-17 09:23:11
     * @param infoVO 信息视图对象
     * @return 
     */
    List<InfoVO> list(InfoVO infoVO);
	/**
     * 根据案件编号查询总数
     * @param ajbh 案件编号
     * @return
     */
	int getCountByAjbh(String ajbh);
}
