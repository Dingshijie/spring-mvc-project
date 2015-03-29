package com.spring.mvc.project.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.spring.mvc.project.domain.CommodityInfo;
import com.spring.mvc.project.domain.UserInfo;
import com.spring.mvc.project.repository.CommodityRepository;

@Repository
public class CommodityRepositoryImpl implements CommodityRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean add(CommodityInfo commodity) {
		return this.getSession().save(commodity) != null;
	}

	@Override
	public void update(CommodityInfo commodity) {
		this.getSession().update(commodity);

	}

	@Override
	public boolean update(String paramter, String id) {
		String hql = "UPDATE CommodityInfo SET " + paramter + " WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}

	@Override
	public void delete(CommodityInfo commodity) {
		this.getSession().delete(commodity);

	}

	@Override
	public CommodityInfo find(String id) {
		return (CommodityInfo) this.getSession().get(CommodityInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityInfo> findList(String username, String categoryCode, String areaCode, String schoolCode,
			int status, int recommend, int used, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(CommodityInfo.class);
		if (StringUtils.hasText(username)) {
			crit.add(Restrictions.eq("username", username));
		}
		if (StringUtils.hasText(categoryCode)) {
			crit.add(Restrictions.eq("category", categoryCode));
		}
		//嵌套子查询
		DetachedCriteria subCriteria = DetachedCriteria.forClass(UserInfo.class);
		subCriteria.setProjection(Projections.property("username"));
		if (StringUtils.hasText(areaCode)) {
			subCriteria.add(Restrictions.like("areaCode", areaCode + "%"));
		}
		if (StringUtils.hasText(schoolCode)) {
			subCriteria.add(Restrictions.like("schoolCode", schoolCode));
		}
		crit.add(Property.forName("username").in(subCriteria));

		if (status != 10) {
			crit.add(Restrictions.eq("status", status));
		}
		if (recommend != 10) {
			crit.add(Restrictions.eq("recommend", recommend));
		}
		if (used != 10) {
			crit.add(Restrictions.eq("used", used));
		}

		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.eq("name", keyword),
					Restrictions.or(
							Restrictions.eq("brand", keyword),
							Restrictions.or(
									Restrictions.eq("typeCode", keyword),
									Restrictions.or(Restrictions.eq("description", keyword),
											Restrictions.eq("username", keyword))))));
		}
		if (pageSize > 0) {
			crit.setMaxResults(pageSize);
			crit.setFirstResult((pageIndex - 1) * pageSize);
		}

		return crit.list();
	}

	@Override
	public int findCount(String username, String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword) {
		Criteria crit = this.getSession().createCriteria(CommodityInfo.class);
		if (StringUtils.hasText(username)) {
			crit.add(Restrictions.eq("username", username));
		}
		if (StringUtils.hasText(categoryCode)) {
			crit.add(Restrictions.eq("category", categoryCode));
		}
		//嵌套子查询
		DetachedCriteria subCriteria = DetachedCriteria.forClass(UserInfo.class);
		subCriteria.setProjection(Projections.property("username"));
		if (StringUtils.hasText(areaCode)) {
			subCriteria.add(Restrictions.like("areaCode", areaCode + "%"));
		}
		if (StringUtils.hasText(schoolCode)) {
			subCriteria.add(Restrictions.like("schoolCode", schoolCode));
		}
		crit.add(Property.forName("username").in(subCriteria));
		if (status != 10) {
			crit.add(Restrictions.eq("status", status));
		}
		if (recommend != 10) {
			crit.add(Restrictions.eq("recommend", recommend));
		}
		if (used != 10) {
			crit.add(Restrictions.eq("used", used));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.or(
					Restrictions.eq("name", keyword),
					Restrictions.or(
							Restrictions.eq("brand", keyword),
							Restrictions.or(
									Restrictions.eq("typeCode", keyword),
									Restrictions.or(Restrictions.eq("description", keyword),
											Restrictions.eq("username", keyword))))));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

}
