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

import com.spring.mvc.project.domain.Category;
import com.spring.mvc.project.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Category find(String id) {
		return (Category) this.getSession().get(Category.class, id);
	}

	@Override
	public boolean exist(String fieldName, String fieldValue) {
		String sql = "SELECT COUNT(0) FROM Category WHERE " + fieldName + "=:fieldValue";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("fieldValue", fieldValue);
		return Integer.parseInt(query.uniqueResult().toString()) != 0;
	}

	@Override
	public boolean add(Category category) {
		return this.getSession().save(category) != null;
	}

	@Override
	public boolean update(String fieldName, Object fieldValue, String id) {
		String hql = "UPDATE Category SET" + fieldName + "=:fieldValue WHERE id=:id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("fieldValue", fieldValue);
		query.setParameter("id", id);
		return query.executeUpdate() != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findList(String categoryCode, int hot, int enable, String keyword, int pageIndex, int pageSize) {
		Criteria crit = this.getSession().createCriteria(Category.class);
		if (StringUtils.hasText(categoryCode)) {
			crit.add(Restrictions.like("code", categoryCode, MatchMode.START));
		}
		if (hot != 10) {
			crit.add(Restrictions.eq("hot", hot));
		}
		if (enable != 10) {
			crit.add(Restrictions.eq("enable", enable));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
		}
		crit.addOrder(Order.asc("code"));
		if (pageSize > 0) {
			crit.setMaxResults(pageSize);
			crit.setFirstResult((pageIndex - 1) * pageSize);
		}
		return crit.list();
	}

	@Override
	public int findCount(String categoryCode, int hot, int enable, String keyword) {
		Criteria crit = this.getSession().createCriteria(Category.class);
		if (StringUtils.hasText(categoryCode)) {
			crit.add(Restrictions.like("code", categoryCode, MatchMode.START));
		}
		if (hot != 10) {
			crit.add(Restrictions.eq("hot", hot));
		}
		if (enable != 10) {
			crit.add(Restrictions.eq("enable", enable));
		}
		if (StringUtils.hasText(keyword)) {
			crit.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

}
