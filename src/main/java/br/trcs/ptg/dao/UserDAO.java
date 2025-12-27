package br.trcs.ptg.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.model.User;

/**
 * DAO específico para a entidade {@link User}.
 * Centraliza consultas relacionadas a autenticação e recuperação de usuários.
 */
@Repository
public class UserDAO extends DAO<User> {

    /**
     * Construtor padrão.
     * Define {@link User} como a entidade gerenciada.
     */
    public UserDAO() {
        super(User.class);
    }

    /**
     * Busca um usuário pelo nome e senha.
     *
     * @param name nome do usuário.
     * @param password senha do usuário.
     * @return usuário autenticado ou null.
     */
    public User findByNameAndPassword(String name, String password) {
        return findSingleByQuery(
            "SELECT u FROM User u WHERE u.name = :name AND u.password = :password",
            Map.of("name", name, "password", password)
        );
    }
}