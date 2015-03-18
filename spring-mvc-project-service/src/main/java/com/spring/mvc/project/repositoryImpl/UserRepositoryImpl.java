package com.spring.mvc.project.repositoryImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.domain.UserInfo.Role;
import com.spring.mvc.project.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		String hql = "SELECT COUNT(id) FROM UserInfo WHERE " + fieldName + "=:fieldValue";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("fieldValue", fieldValue);
		return Integer.parseInt(query.uniqueResult().toString()) != 0;
	}

	@Override
	public UserInfo findByUsername(String username) {
		Criteria crit = this.getSession().createCriteria(UserInfo.class);
		//用户名，手机，邮箱均可登陆
		crit.add(Restrictions.or(Restrictions.eq("username", username),
				Restrictions.or(Restrictions.eq("mobilPhone", username), Restrictions.eq("email", username))));
		return (UserInfo) crit.uniqueResult();
	}

	@Override
	public void updateLastLogin(String id, Date date, String ip) {
		String hql = "UPDATE UserInfo SET lastLoginDate=:lastLoginDate,ip=:ip WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("lastLoginDate", date);
		query.setParameter("ip", ip);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public boolean add(UserInfo userInfo) {
		return this.getSession().save(userInfo) != null;
	}

	@Override
	public boolean update(String paramter, String id) {
		String hql = "UPDATE UserInfo SET " + paramter + " WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}

	@Override
	public UserInfo find(String id) {
		return (UserInfo) this.getSession().get(UserInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findList(Role role, String areaCode, String schoolCode, String keyword, int pageIndex,
			int pageSize) {
		Criteria crit = this.getSession().createCriteria(UserInfo.class);
		if (role != null) {
			crit.add(Restrictions.eq("role", role));
		}
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("areaCode", areaCode, MatchMode.START));
		}
		if (StringUtils.hasText(schoolCode)) {
			crit.add(Restrictions.eq("schoolCode", schoolCode));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.like("username", keyword, MatchMode.ANYWHERE),
					Restrictions.or(Restrictions.like("companyName", keyword, MatchMode.ANYWHERE),
							Restrictions.like("realName", keyword, MatchMode.ANYWHERE))));
		}
		if (pageSize > 0) {
			crit.setFirstResult((pageIndex - 1) * pageSize);
			crit.setMaxResults(pageSize);
		}
		crit.addOrder(Order.asc("registerDate"));
		return crit.list();
	}

	@Override
	public int findCount(Role role, String areaCode, String schoolCode, String keyword) {
		Criteria crit = this.getSession().createCriteria(UserInfo.class);
		if (role != null) {
			crit.add(Restrictions.eq("role", role));
		}
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("areaCode", areaCode, MatchMode.START));
		}
		if (StringUtils.hasText(schoolCode)) {
			crit.add(Restrictions.eq("schoolCode", schoolCode));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.like("username", keyword, MatchMode.ANYWHERE),
					Restrictions.or(Restrictions.like("companyName", keyword, MatchMode.ANYWHERE),
							Restrictions.like("realName", keyword, MatchMode.ANYWHERE))));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

}
