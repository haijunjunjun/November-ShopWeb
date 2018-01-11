package com.november.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.november.common.utils.domain.Response;
import com.november.system.domain.User;
import com.november.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "AuthApi", description = "认证授权接口")
@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/login")
	public String login() {
		return "/login";
	}
	
	@ApiOperation(value = "用户登录", notes = "用户登录", response = Response.class)
	@RequestMapping(value = "/loginUser", produces = { "application/json;charset=utf-8" })
	public Response<String> login(HttpServletRequest request,@ApiParam("用户名密码不能为空") @RequestBody User u) throws JsonProcessingException{
//		if (u == null || StringUtils.isEmpty(u.getLoginName()) || StringUtils.isEmpty(u.getPassword()))
//			return new Response<String>(Response.ERROR, "账号信息不完整");
		User user = userService.findUserByName(u.getLoginName());
		Response<String> resp = new Response<String>(Response.SUCCESS, "用户验证成功");
//		String token = tokenHelper.createToken("123", info);
		resp.setData(user.getLoginName());
		return resp;
	}
}
