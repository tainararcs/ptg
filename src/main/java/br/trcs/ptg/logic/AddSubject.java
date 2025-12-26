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

@Component(Consts.ADD_SUBJECT_LOGIC)
public class AddSubject implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecurityUtils.validaPermission(request, response, "admin");
		
        String name = request.getParameter("name");

        // Validação simples
        if (name == null || name.isBlank()) {
            request.setAttribute(Consts.ERROR, "Nome da matéria é obrigatório");
            return Consts.ADD_SUBJECT_PAGE; 
        }

        Subject subject = new Subject();
        subject.setName(name);

        DAO<Subject> dao = new DAO<Subject>(Subject.class);
        dao.insert(subject);

        request.setAttribute(Consts.MSG, "Disciplina cadastrada com sucesso");
        return Consts.ADD_SUBJECT_PAGE; 
	}
}