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

/**
 * Componente Spring responsável por tratar a lógica de adição de uma nova questão no sistema.
 * <br>
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.ADD_QUESTION_LOGIC)
public class addQuestion implements Logic {

    /**
     * Método principal que processa a requisição para adicionar uma nova questão.
     * 
     * @param request  HttpServletRequest contendo os parâmetros enviados pelo formulário.
     * @param response HttpServletResponse usado para redirecionamentos e validações.
     * @return String que representa a página a ser exibida após a execução.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Valida se o usuário tem permissão de administrador.
        SecurityUtils.validaPermission(request, response, "admin");

        // Recupera parâmetros da requisição.
        String statement = request.getParameter("statement");
        String obs = request.getParameter("obs");
        Integer correctIndex = Integer.valueOf(request.getParameter("correctOption"));
        String[] optionsIds = request.getParameterValues("options");
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));

        // Validação básica dos campos obrigatórios.
        if (statement == null || statement.isBlank() || correctIndex == null || optionsIds == null || subjectId == null || topicId == null) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_QUESTION_PAGE;
        }

        // Criação de objetos Subject e Topic sem abrir novas sessions.
        Subject subject = new Subject(); 
        Topic topic = new Topic();

        // Criação e configuração da Question.
        Question question = new Question();
        question.setStatement(statement);
        question.setObs(obs);
        subject.setId(subjectId);
        topic.setId(topicId);
        question.setSubjectId(subject);
        question.setTopicId(topic);
        question.setOptionsList(storeOptions(question, optionsIds, correctIndex));

        // Persistência da questão no banco de dados.
        DAO<Question> dao = new DAO<Question>(Question.class);
        dao.insert(question);

        request.getSession().setAttribute(Consts.MSG, "Questão cadastrada com sucesso");
        return Consts.REDIRECT_ADD_QUESTION_PAGE;
    }

    /**
     * Cria e retorna a lista de alternativas da questão.
     * 
     * @param question A questão à qual as alternativas pertencem.
     * @param optionsIds Array de Strings contendo os textos das alternativas.
     * @param correctIndex Índice da alternativa correta no array optionsIds.
     * @return Lista de {@link OptionsQuestion} já associadas à questão.
     */
    private List<OptionsQuestion> storeOptions(Question question, String[] optionsIds, Integer correctIndex) {
        List<OptionsQuestion> optionList = new ArrayList<>();

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