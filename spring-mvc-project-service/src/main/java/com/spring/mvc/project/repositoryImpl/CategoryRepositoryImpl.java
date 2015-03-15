package com.spring.mvc.project.repositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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
		crit.add(Restrictions.like("code", categoryCode, MatchMode.START));
		crit.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
		if (hot != 10) {
			crit.add(Restrictions.eq("hot", hot));
		}
		if (enable != 10) {
			crit.add(Restrictions.eq("enable", enable));
		}
		if (pageSize > 0) {
			crit.setMaxResults(pageSize);
			crit.setFirstResult(pageIndex);
		}
		return crit.list();
	}

	@Override
	public int findCount(String categoryCode, int hot, int enable, String keyword) {
		Criteria crit = this.getSession().createCriteria(Category.class);
		crit.add(Restrictions.like("code", categoryCode, MatchMode.START));
		crit.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
		if (hot != 10) {
			crit.add(Restrictions.eq("hot", hot));
		}
		if (enable != 10) {
			crit.add(Restrictions.eq("enable", enable));
		}
		crit.setProjection(Projections.rowCount());
		return Integer.parseInt(crit.uniqueResult().toString());
	}

}
