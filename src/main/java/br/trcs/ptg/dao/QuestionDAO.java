package br.trcs.ptg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.Question;

/**
 * 
 */
@Repository
public class QuestionDAO extends DAO<Question> {
	
	 public QuestionDAO() {
	        super(Question.class);
    }

	 /**
	  * 
	  * @param subjectId
	  * @param topicId
	  * @param bimester
	  * @return
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
