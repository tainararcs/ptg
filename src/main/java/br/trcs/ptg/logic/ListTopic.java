package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.Topic;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component(Consts.LIST_TOPIC_LOGIC)
public class ListTopic implements Logic {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String subjectParam = request.getParameter("subjectId");

    	if (subjectParam == null || !subjectParam.matches("\\d+")) {
    	    response.setContentType("application/json");
    	    response.setCharacterEncoding("UTF-8");
    	    response.getWriter().write("[]");
    	    return null;
    	}

    	Integer subjectId = Integer.valueOf(subjectParam);

        DAO<Topic> dao = new DAO<>(Topic.class);

        var topics = (subjectId == null)
            ? List.of()
            : dao.findByQuery(
                "SELECT t FROM Topic t WHERE t.subjectId.id = :id",
                Map.of("id", Integer.valueOf(subjectId))
              );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(topics));

        return null;
    }
}
