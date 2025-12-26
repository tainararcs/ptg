package br.trcs.ptg.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil { // Hibernate (uma implementação da JPA), pois ele faz oq ta abaixo
	// Entity Manger é JPA (não acessa banco de dados sozinha, nem executa com SQL)
	
	// - A JPA define o contrato, o Hibernate executa
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptg");
	
	/**
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		EntityManager  connection = emf.createEntityManager();
		System.out.print(connection);
		return connection;
	}
}
