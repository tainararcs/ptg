package br.trcs.ptg.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil { 
	
	/**
	 * 
	 */
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
