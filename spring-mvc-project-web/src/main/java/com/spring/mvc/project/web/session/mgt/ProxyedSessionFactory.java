package com.spring.mvc.project.web.session.mgt;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 由于代理会屏蔽客户端真实IP，所以需要重构以获取客户端真实IP
 * @author $Author$
 */
public class ProxyedSessionFactory implements SessionFactory {
	private static final Logger log = LoggerFactory.getLogger(ProxyedSessionFactory.class);

	/**
	 * 创建session
	 */
	@Override
	public Session createSession(SessionContext initData) {
		SimpleSession session = new SimpleSession();
		if (initData != null && initData instanceof WebSessionContext) {
			WebSessionContext sessionContext = (WebSessionContext) initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
			if (request != null) {
				session.setHost(getIpAddr(request));
				log.debug(String.format("Create shiro user session, Host: %s, User-Agent: %s", session.getHost(),
						request.getHeader("User-Agent")));
				log.info(request.getHeader("User-Agent"));
			}
		}
		return session;
	}

	private String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		log.info(String.format("ip from x-forwarded-for: %s", ip));
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			log.info(String.format("ip from X-Real-IP: %s", ip));
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.info(String.format("ip from Proxy-Client-IP: %s", ip));
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
			log.info(String.format("ip from X-Forwarded-For: %s", ip));
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.info(String.format("ip from WL-Proxy-Client-IP: %s", ip));
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.info(String.format("ip from RemoteAddr: %s", ip));
		}
		return ip;
	}

}
