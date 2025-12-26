package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;

/**
 * 
 */
@Repository
public class TestDAO extends DAO<Test> {
	
	public TestDAO() {
        super(Test.class);
    }

	/**
	 * 
	 * @return
	 */
    public List<Test> searchAllTests() {
        return findByQuery("SELECT t FROM Test t ORDER BY t.date DESC", Map.of());
    }
    
    /**
     * 
     * @param userLogged
     * @return
     */
    public List<Test> searchUserTests(User userLogged) {
        return findByQuery(
			"SELECT t FROM Test t WHERE t.userID = :user ORDER BY t.date DESC",
	        Map.of("user", userLogged)
        );
    }
}
