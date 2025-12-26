package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.OptionsQuestion;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.model.Subject;
import br.trcs.ptg.model.Topic;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(Consts.ADD_QUESTION_LOGIC)
public class addQuestion implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecurityUtils.validaPermission(request, response, "admin");
		
		String statement = request.getParameter("statement");
        String obs = request.getParameter("obs");
        Integer correctIndex = Integer.valueOf(request.getParameter("correctOption"));
        String[] optionsIds = request.getParameterValues("options");
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));
        
        // Validação simples.
        if (statement == null || statement.isBlank() || correctIndex == null || optionsIds == null || subjectId == null || topicId == null) {
        	request.setAttribute(Consts.ERROR, "Preencha todos os campos");
	        return Consts.ADD_QUESTION_PAGE;
        }
        
        // Sem abrir novas Sessions.
	    Subject subject = new Subject(); 
	    Topic topic = new Topic();
	    
        //Criação da Question 
        Question question = new Question();
        question.setStatement(statement);
        question.setObs(obs);
        subject.setId(subjectId);
        topic.setId(topicId);
        question.setSubjectId(subject);
        question.setTopicId(topic);
		question.setOptionsList(storeOptions(question, optionsIds, correctIndex));
		
		DAO<Question> dao = new DAO<Question>(Question.class);
		dao.insert(question);
		
		request.getSession().setAttribute(Consts.MSG, "Questão cadastrada com sucesso");
	    return Consts.REDIRECT_ADD_QUESTION_PAGE;
	}
	
	// Armazena as alternativas
	private List<OptionsQuestion> storeOptions(Question question, String[] optionsIds, Integer correctIndex) {
		List<OptionsQuestion> optionList = new ArrayList<OptionsQuestion>();

		for (int i = 0; i < optionsIds.length; i++) {
		    OptionsQuestion option = new OptionsQuestion();
		    option.setText(optionsIds[i]);
		    option.setCorrect(i == correctIndex);
		    option.setQuestionId(question);
		    optionList.add(option);
		}
		
		return optionList;
	}
}