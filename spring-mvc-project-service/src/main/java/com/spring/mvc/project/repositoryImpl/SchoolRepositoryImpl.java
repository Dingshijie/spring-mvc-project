package com.spring.mvc.project.repositoryImpl;

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

import com.spring.mvc.project.domain.SchoolInfo;
import com.spring.mvc.project.repository.SchoolRepository;

@Repository
public class SchoolRepositoryImpl implements SchoolRepository {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(SchoolInfo schoolInfo) {
		return this.getSession().save(schoolInfo) != null;
	}

	@Override
	public void update(SchoolInfo schoolInfo) {
		this.getSession().update(schoolInfo);
	}

	@Override
	public boolean update(String fieldName, String fieldValue, String id) {
		String hql = "UPDATE SchoolInfo SET " + fieldName + "=:fieldValue WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("fieldValue", fieldValue);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}

	@Override
	public SchoolInfo find(String id) {
		return (SchoolInfo) this.getSession().get(SchoolInfo.class, id);
	}

	@Override
	public void delete(SchoolInfo schoolInfo) {
		this.getSession().delete(schoolInfo);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SchoolInfo> finList(String provinceCode, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(SchoolInfo.class);
		if (StringUtils.hasText(provinceCode)) {
			crit.add(Restrictions.eq("areaCode", provinceCode));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
					Restrictions.like("code", keyword, MatchMode.START)));
		}
		crit.addOrder(Order.asc("code"));
		if (pageSize != 0) {
			crit.setFirstResult((pageIndex - 1) * pageSize);
			crit.setMaxResults(pageSize);
		}
		return crit.list();
	}

	@Override
	public int findCount(String provinceCode, String keyword) {
		Criteria crit = this.getSession().createCriteria(SchoolInfo.class);
		if (StringUtils.hasText(provinceCode)) {
			crit.add(Restrictions.eq("areaCode", provinceCode));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
					Restrictions.like("code", keyword, MatchMode.START)));
		}
		crit.setProjection(Projections.count("id"));
		return Integer.parseInt(crit.uniqueResult().toString());
	}

}
