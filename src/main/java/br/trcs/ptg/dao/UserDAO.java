package br.trcs.ptg.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.model.User;

/**
 * 
 */
@Repository
public class UserDAO extends DAO<User> {

    public UserDAO() {
        super(User.class);
    }

    /**
     * 
     * @param name
     * @param password
     * @return
     */
    public User findByNameAndPassword(String name, String password) {
        return findSingleByQuery(
            "SELECT u FROM User u WHERE u.name = :name AND u.password = :password",
            Map.of("name", name, "password", password)
        );
    }
}

