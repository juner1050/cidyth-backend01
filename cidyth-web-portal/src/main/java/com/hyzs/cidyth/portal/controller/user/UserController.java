package com.hyzs.cidyth.portal.controller.user;

import javax.servlet.http.HttpServletRequest;

import com.hyzs.cidyth.common.enums.SystemExceptionEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"用户接口"})
@RestController
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier("userCenterService")
	private UserCenterService userCenterService;

	@PostMapping(value = "login")
	@ApiOperation(value = "用户登录", httpMethod = "POST", response = User.class, notes = "用户登录")
	public User login(@ApiParam(required = true, value = "登录用户信息") @RequestBody LoginInfo param, HttpServletRequest request)
			throws Exception {
		User usr = userCenterService.getUserByUserNameAndPassword(param.getAccount(), param.getPassword());
		// usr = new User();
		// usr.setAccount("051412");
		// usr.setName("谢伟群");
		// usr.setPassword("123456");
		// usr.setDepartment(new Dept("440300191300","深圳市刑事侦查局"));
		if (usr != null) {
			ContextUserHelper.setLoginUser(usr);
		}else{
			throw new ServiceException(SystemExceptionEnum.LOGIN_ERROR.code(), SystemExceptionEnum.LOGIN_ERROR.descp());
		}
		return usr;
	}

	@GetMapping(value = "logout")
	@ApiOperation(value = "用户登出", httpMethod = "GET", notes = "用户登出")
	public void logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
	}

	@GetMapping(value = "google")
	@ApiOperation(value = "浏览器下载", httpMethod = "GET", notes = "浏览器下载")
	public ResponseEntity<byte[]> google() {
		try {
			return FileUtil.download("49_chrome.rar", "谷歌浏览器");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
