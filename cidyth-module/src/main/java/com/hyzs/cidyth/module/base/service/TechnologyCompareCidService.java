package com.hyzs.cidyth.module.base.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.vo.CompareInfoLoadParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface TechnologyCompareCidService {

	/**
	 * 加载比中信息
	 * @param ajbh
	 * @return
	 */
	PageInfo<Map<String, Object>> loadCompareInfo(CompareInfoLoadParam param, Page page);
	/**
	 * 提取指定案件的比中信息
	 * @param ajbh 案件编号
	 * @param page 分页信息
	 * @return
	 */
	PageInfo<Map<String, Object>> loadCompareInfoByAjbh(String ajbh, Page page);
	/**
	 * 导出比中信息
	 * @param response
	 * @param param
	 */
	void export(HttpServletResponse response, Map<String, Object> param);
}
