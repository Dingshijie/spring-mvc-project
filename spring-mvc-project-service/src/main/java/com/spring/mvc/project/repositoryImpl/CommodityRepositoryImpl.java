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
	public CommodityInfo findById(String id) {
		return (CommodityInfo) this.getSession().get(CommodityInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] find(String id) {

		StringBuffer hql = new StringBuffer(
				"SELECT c.id,c.name,c.brand,c.link,c.price,c.unit,c.picture,c.used,c.new_condition,c.status,c.recommend,c.goods,c.description,c.views,c.add_time,u.username,u.real_name,u.mobile_phone,u.tel_phone,u.company_name FROM ");
		hql.append("(SELECT id,name,brand,link,price,unit,picture,used,new_condition,recommend,status,goods,description,views,add_time,username ");
		hql.append("FROM commodity_info WHERE id=:id) c ");
		hql.append("LEFT JOIN (SELECT username,real_name,mobile_phone,tel_phone,company_name FROM user_info ) u ");
		hql.append("ON u.username=c.username ");
		Query query = this.getSession().createSQLQuery(hql.toString());
		query.setParameter("id", id);
		List<Object[]> objList = query.list();
		if (objList != null && objList.size() > 0) {
			return objList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findList(String username, String categoryCode, String areaCode, String schoolCode,
			int status, int recommend, int used, String keyword, int pageIndex, int pageSize) {

		StringBuffer hql = new StringBuffer(
				"SELECT id,name,category,(SELECT name FROM Category WHERE code= a.category) AS categoryName ,price,unit,status,recommend,used,username ");
		hql.append("FROM CommodityInfo AS a WHERE 1=1 ");
		if (StringUtils.hasText(username)) {
			hql.append("AND username=:username ");
		} else {
			hql.append("AND username IN (SELECT username from UserInfo WHERE 1=1 ");
			if (StringUtils.hasText(areaCode)) {
				hql.append("AND areaCode LIKE ':areaCode||'%' ");
			}
			if (StringUtils.hasText(schoolCode)) {
				hql.append("AND schoolCode LIKE ':schoolCode||'%' ");
			}
			hql.append(") ");
		}
		if (StringUtils.hasText(categoryCode)) {
			hql.append("AND category LIKE ':categoryCode||'%' ");
		}
		if (status != 10) {
			hql.append("AND status=:status ");
		}
		if (recommend != 10) {
			hql.append("AND recommend=:recommend ");
		}
		if (used != 10) {
			hql.append("AND used=:used ");
		}

		if (StringUtils.hasText(keyword)) {
			hql.append("AND (name LIKE '%'||:keyword||'%' OR brand LIKE '%'||:keyword||'%' OR typeCode LIKE '%'||:keyword||'%' OR username LIKE '%'||:keyword||'%') ");
		}
		hql.append("ORDER BY ADD_TIME DESC");

		Query query = this.getSession().createQuery(hql.toString());

		if (StringUtils.hasText(username)) {
			query.setParameter("username", username);
		} else {
			if (StringUtils.hasText(areaCode)) {
				query.setParameter("areaCode", areaCode);
			}
			if (StringUtils.hasText(schoolCode)) {
				query.setParameter("schoolCode", schoolCode);
			}
		}
		if (StringUtils.hasText(categoryCode)) {
			query.setParameter("categoryCode", categoryCode);
		}
		if (status != 10) {
			query.setParameter("status", status);
		}
		if (recommend != 10) {
			query.setParameter("recommend", recommend);
		}
		if (used != 10) {
			query.setParameter("used", used);
		}
		if (StringUtils.hasText(keyword)) {
			query.setParameter("keyword", keyword);
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize);
			query.setFirstResult((pageIndex - 1) * pageSize);
		}

		return query.list();
	}

	@Override
	public int findCount(String username, String categoryCode, String areaCode, String schoolCode, int status,
			int recommend, int used, String keyword) {
		StringBuffer hql = new StringBuffer("SELECT count(id) FROM CommodityInfo AS a WHERE 1=1 ");
		if (StringUtils.hasText(username)) {
			hql.append("AND username=:username ");
		} else {
			hql.append("AND username IN (SELECT username from UserInfo WHERE 1=1 ");
			if (StringUtils.hasText(areaCode)) {
				hql.append("AND areaCode LIKE ':areaCode||'%' ");
			}
			if (StringUtils.hasText(schoolCode)) {
				hql.append("AND schoolCode LIKE ':schoolCode||'%' ");
			}
			hql.append(") ");
		}
		if (StringUtils.hasText(categoryCode)) {
			hql.append("AND category LIKE ':categoryCode||'%' ");
		}
		if (status != 10) {
			hql.append("AND status=:status ");
		}
		if (recommend != 10) {
			hql.append("AND recommend=:recommend ");
		}
		if (used != 10) {
			hql.append("AND used=:used ");
		}

		if (StringUtils.hasText(keyword)) {
			hql.append("AND (name LIKE '%'||:keyword||'%' OR brand LIKE '%'||:keyword||'%' OR typeCode LIKE '%'||:keyword||'%' OR username LIKE '%'||:keyword||'%') ");
		}
		hql.append("ORDER BY ADD_TIME DESC");

		Query query = this.getSession().createQuery(hql.toString());

		if (StringUtils.hasText(username)) {
			query.setParameter("username", username);
		} else {
			if (StringUtils.hasText(areaCode)) {
				query.setParameter("areaCode", areaCode);
			}
			if (StringUtils.hasText(schoolCode)) {
				query.setParameter("schoolCode", schoolCode);
			}
		}
		if (StringUtils.hasText(categoryCode)) {
			query.setParameter("categoryCode", categoryCode);
		}
		if (status != 10) {
			query.setParameter("status", status);
		}
		if (recommend != 10) {
			query.setParameter("recommend", recommend);
		}
		if (used != 10) {
			query.setParameter("used", used);
		}
		if (StringUtils.hasText(keyword)) {
			query.setParameter("keyword", keyword);
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * 导出用
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityInfo> findList(String username, String categoryCode, String areaCode, String schoolCode,
			int status, int recommend, int used, String keyword) {
		Criteria crit = this.getSession().createCriteria(CommodityInfo.class);
		if (StringUtils.hasText(username)) {
			crit.add(Restrictions.eq("username", username));
		}
		if (StringUtils.hasText(categoryCode)) {
			crit.add(Restrictions.like("category", categoryCode + "%"));
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

		return crit.list();
	}

	@Override
	public boolean addViews(String id) {
		String sql = "UPDATE CommodityInfo SET views=views+1 WHERE id=:id";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}
}
