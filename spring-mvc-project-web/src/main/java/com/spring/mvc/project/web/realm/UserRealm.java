package com.spring.mvc.project.web.realm;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.service.UserService;

public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	@Override
	public String getName() {
		return "UserRealm";
	}

	/**
	 * 获取当前用户的认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		if (username == null) {
			logger.info("Null Username");
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		logger.info("Begin authentication user account.");

		UserInfo user = userService.findByUsername(username);

		logger.info("User is: " + String.valueOf(user == null));
		if (user != null) {
			logger.info("Make User Principal");

			userService.updateLastLogin(user.getId(), new DateTime().toDate(), user.getIp());
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		} else {
			logger.info("UnknownAccountException");
			throw new UnknownAccountException("No account found for user [" + username + "]");
		}
	}

	/**
	 * 获取当前用户的权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		if (principals.fromRealm(getName()).isEmpty()) {
			logger.debug("Not Reaml user and not need to get permission.");
			return null;
		}
		UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(user.getRole().name());
		info.addStringPermission(user.getAuthentication().name());
		return info;
	}

	/**
	 * 在父类的isPermitted再加一层含有“ OR ”的权限验证，用来实现判断包含任意权限
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		return super.isPermitted(principals, permission);
	}
}