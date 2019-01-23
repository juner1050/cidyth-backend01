package com.hyzs.cidyth.module.integral.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleAvatarDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleSaveDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleVO;

public interface IntegralTitleService {

	/**
	 * 查询
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralTitleVO 
	 * @param page 
	 * @return
	 */
	PageInfo<IntegralTitleVO> list(IntegralTitleVO integralTitleVO, Page page);

	/**
	 * 新增
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralTitleVO 实体
	 * @return
	 */
	int insert(IntegralTitleSaveDTO integralTitleSaveDTO);

	/**
	 * 编辑
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralTitleVO 实体
	 * @return
	 */
	int update(IntegralTitleSaveDTO integralTitleSaveDTO);

	/**
	 * 删除
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param id id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 上传头衔图片
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralTitleAvatarDTO 对象
	 * @return 返回上传成功后的base64字符串
	 */
	String uploadAvatar(IntegralTitleAvatarDTO integralTitleAvatarDTO);

}
