package com.spring.mvc.project.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.project.domain.UserInfo;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 返回登录页面
	 * @return
	 */
	@RequestMapping(value = { "/login.html" }, method = RequestMethod.GET)
	public String login() {
		logger.debug("Show login page.");
		// 到本页后强制退出当前登录
		SecurityUtils.getSubject().logout();
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, boolean rememberMe, Model model) {

		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe);
			try {
				currentUser.login(token);

				if (currentUser.hasRole(UserInfo.Role.ADMIN.name())
						|| currentUser.hasRole(UserInfo.Role.MANAGER.name())) {
					//管理员登陆
					return "redirect:/user/list.html";
				} else if (currentUser.hasRole(UserInfo.Role.BUSSINESS.name())) {
					//商家用户登陆
				}
				//学生登陆
				return "redirect:/index.html";
			} catch (UnknownAccountException e) {
				logger.info("Unknown account login");
				model.addAttribute("errorMsg", "没有该账户信息！");
			} catch (Exception e) {
				logger.info(e.getMessage());
				model.addAttribute("errorMsg", "用户名或密码错误！");
			}
		}
		return "login";
	}

	/**
	 * 用户退出登录
	 * @return 退出后跳转的视图
	 */
	@RequestMapping("/logout.html")
	public String logout() {
		logger.debug("Show index page.");
		SecurityUtils.getSubject().logout();
		return "redirect:/index.html";
	}

	/**
	 * 各种失败和找不到
	 * @return
	 */
	@RequestMapping(value = { "/404.html" }, method = RequestMethod.GET)
	public String _404() {
		//返回到找不到的页面
		return "404";
	}

}
