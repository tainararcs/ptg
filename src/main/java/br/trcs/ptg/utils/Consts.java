package br.trcs.ptg.utils;


/** 
* Classe utilitária responsável por centralizar constantes utilizadas em toda a aplicação.
*/
public class Consts {	
	
	/**
     * Construtor privado para impedir a instanciação da classe.
     */
	private Consts() {}
	
	/**
	 * Caminho base da localização das JSPs.
	 */
	public static final String VIEWS_PATH = "/WEB-INF/views/";
	
	 /**
     * Quantidade padrão de questões geradas em um teste.
     */
	public static final Integer NUMBER_QUESTIONS = 5; 
	
	/**
     * Nome do atributo de sessão que armazena o usuário logado.
     */
	public static final String USER_LOGGED = "userLogged";
	
	/**
	 * Atributos de Request.
	 */
	public static final String ERROR = "error";
	public static final String MSG = "msg";
   
	/**
	 * Componentes JSP comuns.
	 */
	public static final String MENU_JSP = "/WEB-INF/views/menu.jsp";
	public static final String HEADER_JSP = "/WEB-INF/views/header.jsp";
	public static final String FOOTER_JSP = "/WEB-INF/views/footer.jsp";
	public static final String MESSAGE_JSP = "/WEB-INF/views/message.jsp";
  
	/**
	 *  Páginas JSPs principais.
	 */
	public static final String LOGIN_PAGE = "login";
	public static final String HOME_PAGE = "home";
	public static final String ADD_QUESTION_PAGE = "addquestion";
	public static final String ADD_SUBJECT_PAGE = "addsubject";
	public static final String ADD_TOPIC_PAGE = "addtopic";
	public static final String ADD_USER_PAGE = "adduser";
	public static final String ADD_TEST_PAGE = "addtest";
	public static final String SHOW_REPORT_PAGE = "showreport";
	public static final String SHOW_USER_PAGE = "showusers";
	public static final String SHOW_QUESTION_PAGE = "showquestions";
	
  
	/**
	 *  Redirecionamentos
	 */
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

	/**
     * Ações executadas pelos controllers/servlets.
     */
	public static final String LOGIN_LOGIC = "doLogin";
	public static final String LOGOUT_LOGIC = "doLogout";
	public static final String ADD_QUESTION_LOGIC = "addQuestion";
	public static final String ADD_SUBJECT_LOGIC = "addSubject";
	public static final String ADD_TOPIC_LOGIC = "addTopic";
	public static final String ADD_USER_LOGIC = "addUser";
	public static final String ADD_TEST_LOGIC = "addTest";
	public static final String GENERATE_TEST_LOGIC = "generateTest";
	public static final String LIST_TEST_LOGIC = "listTests";
	public static final String LIST_TOPIC_LOGIC = "listTopic";
	public static final String LIST_USER_LOGIC = "listUsers";
	public static final String LIST_QUESTION_LOGIC = "listQuestions";
	public static final String LIST_SUBJECT_LOGIC = "listSubject";
	public static final String PREPARE_TESTS_LOGIC = "prepareAddTests";
	public static final String EXPORT_PDF_LOGIC = "exportPDF";
	
	/**
     * Endpoints utilizados nas requisições da aplicação (rotas HTTP).
     */
	public static final String ACTION_LOGIN = "action/doLogin";
	public static final String ACTION_LOGOUT = "action/doLogout";
	public static final String ACTION_ADD_QUESTION = "action/addQuestion";
	public static final String ACTION_ADD_SUBJECT = "action/addSubject";
	public static final String ACTION_ADD_TOPIC = "action/addTopic";
	public static final String ACTION_ADD_USER = "action/addUser";
	public static final String ACTION_ADD_TEST = "action/addTest";
	public static final String ACTION_LIST_TEST = "action/listTests";
	public static final String ACTION_GENERATE_TEST = "action/generateTest";
	public static final String ACTION_LIST_TOPIC = "action/listTopic";
	public static final String ACTION_LIST_SUBJECT = "action/listSubject";
	public static final String ACTION_LIST_USER = "action/listUsers";
	public static final String ACTION_LIST_QUESTION = "action/listQuestions";
	public static final String ACTION_PREPARE_TESTS = "action/prepareAddTests";
	public static final String ACTION_EXPORT_PDF = "action/exportPDF";
}