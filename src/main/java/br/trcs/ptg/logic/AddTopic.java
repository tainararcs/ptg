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

@Component(Consts.ADD_TOPIC_LOGIC)
public class AddTopic implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecurityUtils.validaPermission(request, response, "admin");
		
		String name = request.getParameter("name");
		Grade grade = Grade.valueOf(request.getParameter("grade"));
		Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));
		Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
		
        // Validação simples.
        if (name == null || name.isBlank() || grade == null || bimester == null || subjectId == null) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_SUBJECT_PAGE;
        }
        
        // 
		Subject subject = new Subject();
		subject.setId(subjectId);
		
        Topic topic = new Topic();
        topic.setName(name);
        topic.setGrade(grade);
        topic.setBimester(bimester);
        topic.setSubjectId(subject);

        DAO<Topic> dao = new DAO<Topic>(Topic.class);
        dao.insert(topic);
        
        request.getSession().setAttribute(Consts.MSG, "Matéria cadastrada com sucesso");
        return Consts.REDIRECT_ADD_TOPIC_PAGE;
	}
}