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
	public void update(Area area) {
		this.getSession().update(area);
	}

	@Override
	public void delete(Area area) {
		this.getSession().delete(area);
	}

	@Override
	public Area find(String id) {
		return (Area) this.getSession().get(Area.class, id);
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue) {
		String sql = "SELECT COUNT(id) FROM Area WHERE " + fieldName + "=:fieldValue";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("fieldValue", fieldValue);
		return Integer.parseInt(query.uniqueResult().toString()) != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findList(String areaCode, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(Area.class);
		if (StringUtils.hasText(areaCode)) {
			crit.add(Restrictions.like("code", areaCode, MatchMode.START));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.like("code", keyword, MatchMode.ANYWHERE),
					Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
							Restrictions.like("display", keyword, MatchMode.ANYWHERE))));
		}
		crit.addOrder(Order.asc("code"));
		if (pageSize > 0) {
			crit.setFirstResult((pageIndex - 1) * pageSize);
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
			crit.add(Restrictions.or(
					Restrictions.like("code", keyword, MatchMode.ANYWHERE),
					Restrictions.or(Restrictions.like("name", keyword, MatchMode.ANYWHERE),
							Restrictions.like("display", keyword, MatchMode.ANYWHERE))));
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
