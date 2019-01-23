package com.hyzs.cidyth.module.base.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.vo.CasesVO;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesCidService {

	/**
	 * 查询警综的数据列表
	 * @param casesVO 案件视图对象
	 * @return
	 */
	PageInfo<Cases> listPackCases(CasesVO casesVO, Page page);

}
