package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.Question;

/**
 * DAO específico para a entidade {@link Question}.
 * Estende o DAO genérico e adiciona consultas específicas relacionadas ao domínio de questões (questions).
 */
@Repository
public class QuestionDAO extends DAO<Question> {

    /**
     * Construtor padrão.
     * Informa à superclasse qual entidade este DAO gerencia.
     */
    public QuestionDAO() {
        super(Question.class);
    }

    /**
     * Busca questões específicas com base em:
     * - disciplina (subject)
     * - tópico (topic)
     * - bimestre (bimester)
     *
     * @param subjectId identificador da disciplina.
     * @param topicId identificador do tópico.
     * @param bimester bimestre associado ao tópico.
     * @return lista de questões que atendem aos critérios.
     */
    public List<Question> searchSpecifiQuestions(Integer subjectId, Integer topicId, Bimester bimester) {
    	return findByQuery("""
				SELECT DISTINCT q FROM Question q JOIN FETCH q.optionsList
				WHERE q.subjectId.id = :subjectId AND q.topicId.id = :topicId AND q.topicId.bimester = :bimester
			""",
			Map.of("subjectId", subjectId, "topicId", topicId, "bimester", bimester)
		);
    }
}