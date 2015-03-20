package com.spring.mvc.project.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.mvc.project.domain.EduPubCode;
import com.spring.mvc.project.repository.EduPubCodeRepository;

@Repository
public class EduPubCodeRepositoryImpl implements EduPubCodeRepository {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(EduPubCode eduPubCode) {
		return this.getSession().save(eduPubCode) != null;
	}

	@Override
	public void update(EduPubCode eduPubCode) {
		this.getSession().update(eduPubCode);
	}

	@Override
	public EduPubCode find(String id) {
		return (EduPubCode) this.getSession().get(EduPubCode.class, id);
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		String sql = "SELECT COUNT(id) FROM EduPubCode WHERE " + fieldName + "=:fieldValue";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("fieldValue", fieldValue);
		return Integer.parseInt(query.uniqueResult().toString()) != 0;
	}

	@Override
	public void delete(EduPubCode eduPubCode) {
		this.getSession().delete(eduPubCode);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EduPubCode> findList(String eduLevel, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(EduPubCode.class);
		if (StringUtils.hasText(eduLevel)) {
			crit.add(Restrictions.eq("eduLevel", eduLevel));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.like("code", keyword, MatchMode.START),
					Restrictions.or(
							Restrictions.like("name", keyword, MatchMode.ANYWHERE),
							Restrictions.or(Restrictions.like("firstName", keyword, MatchMode.ANYWHERE),
									Restrictions.like("secondName", keyword, MatchMode.ANYWHERE)))));
		}
		crit.addOrder(Order.asc("eduLevel"));
		crit.addOrder(Order.asc("code"));
		if (pageSize > 0) {
			crit.setFirstResult((pageIndex - 1) * pageSize);
			crit.setMaxResults(pageSize);
		}
		return crit.list();
	}

	@Override
	public int findCount(String eduLevel, String keyword) {
		Criteria crit = this.getSession().createCriteria(EduPubCode.class);
		if (StringUtils.hasText(eduLevel)) {
			crit.add(Restrictions.eq("eduLevel", eduLevel));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.like("code", keyword, MatchMode.START),
					Restrictions.or(
							Restrictions.like("name", keyword, MatchMode.ANYWHERE),
							Restrictions.or(Restrictions.like("firstName", keyword, MatchMode.ANYWHERE),
									Restrictions.like("secondName", keyword, MatchMode.ANYWHERE)))));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findZYDLMap(String eduLevel, String keyword) {
		Criteria crit = this.getSession().createCriteria(EduPubCode.class);
		if (StringUtils.hasText(eduLevel)) {
			crit.add(Restrictions.eq("eduLevel", eduLevel));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.like("firstName", keyword, MatchMode.ANYWHERE));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("firstCode"));
		projectionList.add(Projections.groupProperty("firstName"));
		crit.setProjection(Projections.distinct(projectionList));
		return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findZYZLMap(String eduLevel, String firstCode, String keyword) {
		Criteria crit = this.getSession().createCriteria(EduPubCode.class);
		if (StringUtils.hasText(eduLevel)) {
			crit.add(Restrictions.eq("eduLevel", eduLevel));
		}
		if (StringUtils.hasText(firstCode)) {
			crit.add(Restrictions.like("secondCode", firstCode, MatchMode.START));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.like("secondName", keyword, MatchMode.ANYWHERE));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("secondCode"));
		projectionList.add(Projections.groupProperty("secondName"));
		crit.setProjection(Projections.distinct(projectionList));
		return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findZYXLMap(String eduLevel, String secondCode, String keyword) {
		Criteria crit = this.getSession().createCriteria(EduPubCode.class);
		if (StringUtils.hasText(eduLevel)) {
			crit.add(Restrictions.eq("eduLevel", eduLevel));
		}
		if (StringUtils.hasText(secondCode)) {
			crit.add(Restrictions.like("code", secondCode, MatchMode.START));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("code"));
		projectionList.add(Projections.groupProperty("name"));
		crit.setProjection(Projections.distinct(projectionList));
		return crit.list();
	}

}
