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

import com.spring.mvc.project.domain.Area;
import com.spring.mvc.project.repository.AreaRepository;

@Repository
public class AreaRepositoryImpl implements AreaRepository {

	@Qualifier("sessionFactory")
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(Area area) {
		return this.getSession().save(area) != null;
	}

	@Override
	public boolean update(String fieldName, String fieldValue, String id) {
		String hql = "UPDATE Area SET" + fieldName + "=:fieldValue WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("fieldValue", fieldValue);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}

	@Override
	public void delete(Area area) {
		this.getSession().delete(area);
	}

	@Override
	public Area find(String id) {
		return (Area) this.getSession().get(Area.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findList(String areaCode, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(Area.class);
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("code", areaCode, MatchMode.START));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
					Restrictions.like("display", areaCode, MatchMode.ANYWHERE)));
		}
		crit.addOrder(Order.asc("code"));
		if (pageSize > 0) {
			crit.setFirstResult(pageIndex);
			crit.setMaxResults(pageSize);
		}
		return crit.list();
	}

	@Override
	public int findCount(String areaCode, String keyword) {
		Criteria crit = this.getSession().createCriteria(Area.class);
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("code", areaCode, MatchMode.START));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
					Restrictions.like("display", areaCode, MatchMode.ANYWHERE)));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findCityList(String areaCode) {
		Criteria crit = this.getSession().createCriteria(Area.class);
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("code", areaCode, MatchMode.START));
		}
		crit.add(Restrictions.like("code", "00", MatchMode.END));
		crit.addOrder(Order.asc("code"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findAreaList(String areaCode) {
		Criteria crit = this.getSession().createCriteria(Area.class);
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("code", areaCode, MatchMode.START));
		}
		crit.add(Restrictions.not(Restrictions.like("code", "00", MatchMode.END)));
		crit.addOrder(Order.asc("code"));
		return crit.list();
	}

}
