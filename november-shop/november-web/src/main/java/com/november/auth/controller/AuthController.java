package com.november.auth.controller;

import com.november.auth.domain.User;
import com.november.auth.service.UserService;
import com.november.common.ctrl.response.WebResponseV1;
import com.november.common.token.TokenHelper;
import com.november.common.utils.constant.GlobalConstant;
import com.november.common.utils.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "AuthApi", description = "认证授权接口")
@Controller
public class AuthController {
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	TokenHelper tokenHelper;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "/index";
	}
	
	@ApiOperation(value = "用户登录", notes = "用户登录", response = Response.class)
	@ResponseBody
	@PostMapping(value = "/loginUser",produces = { "application/json;charset=utf-8" })
	public Response<String> loginUser(HttpServletRequest request,User u){
		try {
			if (u == null || StringUtils.isEmpty(u.getLoginName()) || StringUtils.isEmpty(u.getPassword())){
				return new Response<String>(Response.ERROR, "账号信息不完整");
			}
			User user = userService.findUserByName(u.getLoginName());
			if (user == null) {
				return new Response<String>(Response.ERROR, "用户不存在");
			}

			if (!StringUtils.equals(u.getPassword(), user.getPassword())) {
				return new Response<String>(Response.ERROR, "账号或密码不正确");
			}

			Map<String, Object> info = new HashMap<String, Object>();
//			info.put(GlobalConstant.TENANT_ID, "yizhi");
			info.put(GlobalConstant.USER_ID, user.getId());
			info.put(GlobalConstant.USER_ACCOUNT, user.getLoginName());
			//eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpYXQiOjE1MTU2NjEyNTgsInN1YiI6ImxvZ2luIiwiaXNzIjoibm92ZW1iZXIiLCJ1c2VyQWNjb3VudCI6Inlhbmd6ZXh1IiwidXNlcklkIjoxLCJleHAiOjE1MTU2NjQ4NTgsIm5iZiI6MTUxNTY2MTI1OH0.tbJrQUgazplliBzpau9p8s_GyO0ziwciMFxidBqxhgI
			String token = tokenHelper.createToken("login", info);
			Response<String> resp = new Response<String>(Response.SUCCESS, "用户验证成功");
			resp.setData(token);
			return resp;

		} catch (Exception e) {
			logger.error("msg", e);
			return new Response<String>(Response.ERROR, e.getLocalizedMessage());
		}
	}

}
