package br.trcs.ptg.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por gerenciar a criação de {@link EntityManager} a partir de um {@link EntityManagerFactory}.
 * Centraliza a configuração do JPA e evita a duplicação de código para obtenção de conexões com o banco de dados.
 */
public class JPAUtil { 
	
	 /**
     * EntityManagerFactory compartilhado pela aplicação.
     * É criado uma única vez com base na unidade de persistência "ptg" definida no arquivo persistence.xml.
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ptg");

    /**
     * Cria e retorna um novo {@link EntityManager}.
     * Cada chamada a este método retorna uma nova instância.
     *
     * @return EntityManager pronto para uso.
     */
    public EntityManager getEntityManager() {
        EntityManager connection = emf.createEntityManager();

        // Log simples para fins de depuração.
        System.out.print(connection);

        return connection;
    }
}