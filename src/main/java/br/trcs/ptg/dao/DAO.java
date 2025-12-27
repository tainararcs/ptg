package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

/**
 * DAO (Data Access Object) genérico.
 * Esta classe fornece operações básicas de persistência (CRUD) para qualquer entidade JPA, utilizando generics.
 *
 * @param <T> tipo da entidade JPA gerenciada pelo DAO.
 */
public class DAO<T> {
	
	/**
     * Classe da entidade que o DAO manipula.
     * Necessária para operações genéricas do JPA.
     */
	private Class<T> currentClass;
	
	/**
     * Construtor do DAO.
     *
     * @param currentClass classe da entidade associada o DAO.
     */
	public DAO(Class<T> currentClass) {
		this.currentClass = currentClass;
	}
	
	/**
    * Insere uma nova entidade no banco de dados.
    * Abre uma transação, persiste a entidade e realiza o commit.
    *
    * @param t entidade a ser persistida.
    */
	public void insert(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	 /**
     * Busca uma entidade pelo seu identificador.
     * Operação de leitura simples utilizando o método {@link EntityManager#find(Class, Object)}.
     *
     * @param id identificador da entidade.
     * @return entidade encontrada ou null caso não exista.
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
     * Busca uma entidade pelo atributo "name".
     * Utiliza JPQL dinâmica baseada no nome simples da classe.
     * Retorna apenas o primeiro resultado encontrado.
     *
     * @param name valor do atributo name.
     * @return entidade encontrada ou null.
     */
	public T findByName(String name) {
		EntityManager em = new JPAUtil().getEntityManager();
	    
		try {
	        String jpql = "SELECT e FROM " + currentClass.getSimpleName() + " e WHERE e.name = :name";
	        return em.createQuery(jpql, currentClass).setParameter("name", name).getResultStream().findFirst().orElse(null);
	    } finally {
	        em.close();
	    }
	}
	
	/**
     * Executa uma consulta JPQL que retorna múltiplos resultados.
     *
     * @param jpql string JPQL da consulta.
     * @param params mapa de parâmetros nome/valor.
     * @return lista de entidades encontradas.
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
    * Executa uma consulta JPQL que retorna apenas um resultado.
    *
    * @param jpql string JPQL da consulta.
    * @param params mapa de parâmetros nome/valor.
    * @return entidade encontrada ou null.
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
     * Atualiza uma entidade existente no banco de dados.
     * Utiliza {@link EntityManager#merge(Object)} para sincronizar o estado da entidade com o banco.
     *
     * @param t entidade a ser atualizada.
     */
	public void update(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	/**
     * Remove uma entidade do banco de dados.
     *
     * @param t entidade a ser removida.
     */
    public void remove(T t) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Retorna todas as entidades do tipo T.
     * Utiliza a Criteria API para criar uma consulta genérica e segura em tempo de compilação.
     *
     * @return lista com todas as entidades persistidas.
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