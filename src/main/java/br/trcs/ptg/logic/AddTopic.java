package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.enums.Grade;
import br.trcs.ptg.model.Subject;
import br.trcs.ptg.model.Topic;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por tratar a lógica de adição de uma nova matéria (Topic) no sistema.
 * <br>
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.ADD_TOPIC_LOGIC)
public class AddTopic implements Logic {

    /**
     * Método principal que processa a requisição para adicionar uma nova matéria.
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

        // Recupera os parâmetros da requisição.
        String name = request.getParameter("name");
        Grade grade = Grade.valueOf(request.getParameter("grade"));
        Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));

        // Validação básica dos campos obrigatórios.
        if (name == null || name.isBlank() || grade == null || bimester == null || subjectId == null) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_TOPIC_PAGE;
        }

        DAO<Topic> dao = new DAO<>(Topic.class);

        // Verifica se a matéria já existe no banco.
        if (dao.findByName(name) != null) {
            request.setAttribute(Consts.ERROR, "Matéria já cadastrada");
            return Consts.ADD_TOPIC_PAGE; 
        }

        // Criação do objeto Subject com o ID informado.
        Subject subject = new Subject();
        subject.setId(subjectId);

        Topic topic = new Topic();
        topic.setName(name);
        topic.setGrade(grade);
        topic.setBimester(bimester);
        topic.setSubjectId(subject);

        dao.insert(topic);

        request.setAttribute(Consts.MSG, "Matéria cadastrada com sucesso");
        return Consts.ADD_TOPIC_PAGE;
    }
}