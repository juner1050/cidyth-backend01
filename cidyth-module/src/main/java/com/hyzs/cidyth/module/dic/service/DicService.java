package com.hyzs.cidyth.module.dic.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.vo.DemandVO;
import com.hyzs.cidyth.module.dic.entity.Dictionary;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface DicService {

    /**
     * 根据类型编号获取字典列表
     * @author 陈铭
     * @date 2018-04-10 17:21:53
     * @param lxbh 字典类型编号
     * @return
     */
	List<Dictionary> listByLxbh(String lxbh);
	/**
     * 根据类型编号获取字典列表
     * @author 陈铭
     * @date 2018-04-10 17:21:53
     * @param lxbh 字典类型编号
     * @return
     */
	List<Dictionary> listByLxbh(String lxbh, String fjzdj);
    /**
     * 根据类型编号、字典键获取字典值
     * @author 陈铭
     * @date 2018-04-10 17:21:53
     * @param lxbh 字典类型编号
     * @param zdj 字典键
     * @return
     */
	String getValueByKey(String lxbh, String zdj);

}
