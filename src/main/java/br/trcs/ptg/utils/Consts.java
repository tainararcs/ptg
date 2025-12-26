package br.trcs.ptg.utils;

public class Consts {	
	
	private Consts() {}
	
	public static final Integer NUMBER_QUESTIONS = 3; 
	
	public static final String USER_LOGGED = "userLogged";
	
	// Atributos
	public static final String ERROR = "error";
	public static final String MSG = "msg";
	public static final String RETURN = "returnPage";
	public static final String INFO = "infoType";
	public static final String SUBJECT_INFO = "subject";
	public static final String TOPIC_INFO = "topic";
   
	//
	public static final String MENU_JSP = "/WEB-INF/views/menu.jsp";
	public static final String HEADER_JSP = "/WEB-INF/views/header.jsp";
	public static final String FOOTER_JSP = "/WEB-INF/views/footer.jsp";
	public static final String MESSAGE_JSP = "/WEB-INF/views/message.jsp";
  
	// JSPs principais
	public static final String LOGIN_PAGE = "login";
	public static final String HOME_PAGE = "home";
	public static final String ADD_QUESTION_PAGE = "addquestion";
	public static final String ADD_SUBJECT_PAGE = "addsubject";
	public static final String ADD_TOPIC_PAGE = "addtopic";
	public static final String ADD_USER_PAGE = "adduser";
	public static final String ADD_TEST_PAGE = "addtest";
	public static final String SHOW_REPORT_PAGE = "showreport";
  
	// Redirecionamentos
	public static final String REDIRECT_LOGIN_PAGE = "redirect:login";
	public static final String REDIRECT_HOME_PAGE = "redirect:home";
	public static final String REDIRECT_ADD_QUESTION_PAGE = "redirect:addquestion";
	public static final String REDIRECT_ADD_SUBJECT_PAGE = "redirect:addsubject";
	public static final String REDIRECT_ADD_TOPIC_PAGE = "redirect:addtopic";
	public static final String REDIRECT_ADD_USER_PAGE = "redirect:adduser";
	public static final String REDIRECT_ADD_TEST_PAGE = "redirect:addtest";
	public static final String REDIRECT_SHOW_REPORT_PAGE = "redirect:showreport";
	public static final String REDIRECT_LIST_SUBJECT = "redirect:action:listSubject";
	public static final String REDIRECT_LIST_TOPIC_LOGIC = "redirect:action:listTopic";
	public static final String REDIRECT_PREPARE_TESTS_LOGIC = "redirect:action:prepareAddTests";
	public static final String REDIRECT_EXPORT_PDF_LOGIC = "redirect:action:exportPDF";

	// Lógica
	public static final String LOGIN_LOGIC = "doLogin";
	public static final String LOGOUT_LOGIC = "doLogout";
	public static final String ADD_QUESTION_LOGIC = "addQuestion";
	public static final String ADD_SUBJECT_LOGIC = "addSubject";
	public static final String ADD_TOPIC_LOGIC = "addTopic";
	public static final String ADD_USER_LOGIC = "addUser";
	public static final String ADD_TEST_LOGIC = "addTest";
	public static final String GENERATE_TEST_LOGIC = "generateTest";
	public static final String GENERATE_REPORT_LOGIC = "generateReport";
	public static final String LIST_TOPIC_LOGIC = "listTopic";
	public static final String LIST_SUBJECT_LOGIC = "listSubject";
	public static final String PREPARE_TESTS_LOGIC = "prepareAddTests";
	public static final String EXPORT_PDF_LOGIC = "exportPDF";
	
	// Ações
	public static final String ACTION_LOGIN = "action/doLogin";
	public static final String ACTION_LOGOUT = "action/doLogout";
	public static final String ACTION_ADD_QUESTION = "action/addQuestion";
	public static final String ACTION_ADD_SUBJECT = "action/addSubject";
	public static final String ACTION_ADD_TOPIC = "action/addTopic";
	public static final String ACTION_ADD_USER = "action/addUser";
	public static final String ACTION_ADD_TEST = "action/addTest";
	public static final String ACTION_GENERATE_REPORT = "action/generateReport";
	public static final String ACTION_GENERATE_TEST = "action/generateTest";
	public static final String ACTION_LIST_TOPIC = "action/listTopic";
	public static final String ACTION_LIST_SUBJECT = "action/listSubject";
	public static final String ACTION_PREPARE_TESTS = "action/prepareAddTests";
	public static final String ACTION_EXPORT_PDF = "action/exportPDF";
}
