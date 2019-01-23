package com.hyzs.cidyth.module.integral.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.TableTypeEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.integral.dao.IntegralTitleMapper;
import com.hyzs.cidyth.module.integral.entity.IntegralTitle;
import com.hyzs.cidyth.module.integral.service.IntegralTitleService;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleAvatarDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleSaveDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleVO;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("IntegralTitleService")
public class IntegralTitleServiceImpl implements IntegralTitleService {
	
	private static final Logger logger = LoggerFactory.getLogger(IntegralTitleServiceImpl.class);

	@Autowired
	private IntegralTitleMapper integralTitleMapper;
	@Autowired
	private AttachmentService attachmentService;

	@Override
	public PageInfo<IntegralTitleVO> list(IntegralTitleVO integralTitleVO, Page page) {
		IntegralTitle integralTitleParam = new IntegralTitle();
		BeanUtils.copyProperties(integralTitleVO, integralTitleParam);

		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<IntegralTitle> pageInfo = new PageInfo<IntegralTitle>(integralTitleMapper.select(integralTitleParam));
		List<IntegralTitleVO> lsIntegralTitleVO = Lists.newArrayList();
		for(IntegralTitle item : pageInfo.getList()){
			IntegralTitleVO vo = new IntegralTitleVO();
			BeanUtils.copyProperties(item, vo);
			Attachment attachment = attachmentService.getAttachmentByMaxId(vo.getId());
			if(attachment != null){
				// 把上传的头像图片转base64返回给前端
				String suffixName = attachment.getFjmc().substring(attachment.getFjmc().indexOf(".") + 1);
				// 返回完整的base64图片的数据
				try {
					vo.setAvatar(getBase64ImagePreffix(suffixName) + FileUtil.toBase64(attachment.getFileId()));
				} catch (Exception e) {
					throw new ServiceException("头衔图片转换失败");
				}
			}
			lsIntegralTitleVO.add(vo);
		}
		PageInfo<IntegralTitleVO> pageResult = new PageInfo<IntegralTitleVO>();
		BeanUtils.copyProperties(pageInfo, pageResult);
		pageResult.setList(lsIntegralTitleVO);

		return pageResult;
	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public int insert(IntegralTitleSaveDTO integralTitleSaveDTO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		IntegralTitle integralTitle = new IntegralTitle();
		BeanUtils.copyProperties(integralTitleSaveDTO, integralTitle);
		/*if(integralTitle.getUpperLimit() <= integralTitle.getLowerLimit()){
			throw new ServiceException("上限分数不能小于等于下限分数!");
		}*/
		integralTitleMapper.insertSelective(integralTitle);
		if(integralTitleSaveDTO.getAvatar() != null){
			Map<String,Object> attachments = attachmentService.insert(integralTitleSaveDTO.getAvatar(), "", String.valueOf(integralTitle.getId()), TableTypeEnum.INTEGRAL_TITLE_AVATAR.name());
		}
		return integralTitle.getId();
	}

	@Override
	public int update(IntegralTitleSaveDTO integralTitleSaveDTO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		if(integralTitleSaveDTO.getId() == null){
			throw new ServiceException("无法获取头衔ID");
		}
		IntegralTitle integralTitle = new IntegralTitle();
		BeanUtils.copyProperties(integralTitleSaveDTO, integralTitle);
		/*if(integralTitle.getUpperLimit() <= integralTitle.getLowerLimit()){
			throw new ServiceException("上限分数不能小于等于下限分数!");
		}*/
		integralTitleMapper.updateByPrimaryKeySelective(integralTitle);
		if(integralTitleSaveDTO != null){
			// 保存附件
			Map<String,Object> attachments = attachmentService.insert(integralTitleSaveDTO.getAvatar(), "", String.valueOf(integralTitleSaveDTO.getId()), TableTypeEnum.INTEGRAL_TITLE_AVATAR.name());
		}
		return integralTitle.getId();
	}

	@Override
	public int delete(Integer id) {
		if(id == null){
			throw new ServiceException("id为空!");
		}
		return integralTitleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String uploadAvatar(IntegralTitleAvatarDTO integralTitleAvatarDTO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		if(integralTitleAvatarDTO.getId() == null){
			throw new ServiceException("id为空!");
		}
		if(integralTitleAvatarDTO.getAvatar() == null){
			throw new ServiceException("无法获取上传文件!");
		}
		// 保存附件
		Map<String,Object> attachments = attachmentService.insert(integralTitleAvatarDTO.getAvatar(), "", String.valueOf(integralTitleAvatarDTO.getId()), TableTypeEnum.INTEGRAL_TITLE_AVATAR.name());
		String suffixName = attachments.get("url").toString().substring(attachments.get("url").toString().indexOf(".") + 1);
		// 返回完整的base64图片的数据
		try {
			return getBase64ImagePreffix(suffixName) + FileUtil.toBase64(attachments.get("url").toString());
		} catch (Exception e) {
			throw new ServiceException("头衔图片转换失败");
		}
	}

	public String getBase64ImagePreffix(String suffixName){
		if(suffixName.toLowerCase().equals("png")){
			return "data:image/png;base64,";
		}else if(suffixName.toLowerCase().equals("gif")){
			return "data:image/gif;base64,";
		}else{
			return "data:image/jpeg;base64,";
		}
	}

}
