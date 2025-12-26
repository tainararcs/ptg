package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

public class DAO<T> { // "Implementação do hibernate"?
	
	/**
	 * 
	 */
	private Class<T> currentClass;
	
	/**
	 * 
	 * @param currentClass
	 */
	public DAO(Class<T> currentClass) {
		this.currentClass = currentClass;
	}
	
	/**
	 * 
	 * @param t
	 */
	public void insert(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Read
	 * @param id
	 * @return
	 */
	public T findById(Integer id) {
	    EntityManager em = new JPAUtil().getEntityManager();
	    try {
	    	return em.find(currentClass, id);
	    } finally {
	        em.close();
	    }
	}
	
	/**
	 * 
	 * @param jpql
	 * @param params
	 * @return
	 */
	public List<T> findByQuery(String jpql, Map<String, Object> params) {
	    EntityManager em = new JPAUtil().getEntityManager();

	    try {
	    	var query = em.createQuery(jpql, currentClass);
	    	params.forEach(query::setParameter);
	    	return query.getResultList();
	    } finally {
	        em.close();
	    }
	}

	/**
	 * 
	 * @param jpql
	 * @param params
	 * @return
	 */
	public T findSingleByQuery(String jpql, Map<String, Object> params) {
	    EntityManager em = new JPAUtil().getEntityManager();
	    
	    try {
	        var query = em.createQuery(jpql, currentClass);
	        params.forEach(query::setParameter);
	        return query.getResultStream().findFirst().orElse(null);
	    } finally {
	        em.close();
	    }
	}

	/**
	 * 
	 * @param t
	 */
	public void update(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param t
	 */
	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<T> list() {
		EntityManager em = new JPAUtil().getEntityManager();
		
		try {
			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(currentClass);
			query.select(query.from(currentClass));
			return em.createQuery(query).getResultList();
		} finally {
	        em.close();
	    }
	}
}