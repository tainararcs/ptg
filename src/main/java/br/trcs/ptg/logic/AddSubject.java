package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.Subject;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por tratar a lógica de adição de uma nova disciplina (Subject) no sistema.
 * <br>
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.ADD_SUBJECT_LOGIC)
public class AddSubject implements Logic {

    /**
     * Método principal que processa a requisição para adicionar uma nova disciplina.
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

        // Recupera o parâmetro "name" da requisição.
        String name = request.getParameter("name");

        // Validação básica do campo.
        if (name == null || name.isBlank()) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_SUBJECT_PAGE; 
        }

        // Inicializa o DAO genérico para Subject.
        DAO<Subject> dao = new DAO<>(Subject.class);

        // Verifica se a disciplina já existe no banco.
        if (dao.findByName(name) != null) {
            request.setAttribute(Consts.ERROR, "Disciplina já cadastrada");
            return Consts.ADD_SUBJECT_PAGE; 
        }

        Subject subject = new Subject();
        subject.setName(name);

        dao.insert(subject);

        request.setAttribute(Consts.MSG, "Disciplina cadastrada com sucesso");
        return Consts.ADD_SUBJECT_PAGE; 
    }
}