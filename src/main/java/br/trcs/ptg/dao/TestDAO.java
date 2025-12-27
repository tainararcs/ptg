package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;

/**
 * DAO específico para a entidade {@link Test}.
 * Responsável por consultas relacionadas a provas/testes, incluindo ordenação e filtragem por usuário.
 */
@Repository
public class TestDAO extends DAO<Test> {

    /**
     * Construtor padrão.
     * Define {@link Test} como a entidade gerenciada.
     */
    public TestDAO() {
        super(Test.class);
    }

    /**
     * Retorna todos os testes cadastrados no sistema, ordenados pela data em ordem decrescente.
     *
     * @return lista de testes ordenada por data.
     */
    public List<Test> searchAllTests() {
        return findByQuery(
            "SELECT t FROM Test t ORDER BY t.date DESC",
            Map.of()
        );
    }

    /**
     * Retorna todos os testes associados a um usuário específico.
     * A consulta filtra pelo usuário logado e ordena os resultados pela data em ordem decrescente.
     *
     * @param userLogged usuário autenticado.
     * @return lista de testes do usuário.
     */
    public List<Test> searchUserTests(User userLogged) {
        return findByQuery(
			"SELECT t FROM Test t WHERE t.userID = :user ORDER BY t.date DESC",
	        Map.of("user", userLogged)
        );
    }
}