package br.trcs.ptg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa o relacionamento entre testes e questões sorteadas.
 * Cada registro indica que uma determinada questão faz parte de um teste específico.
 */
@Entity
@Table(name = "questions_test")
public class QuestionsTest {

    /**
     * Identificador único do relacionamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Teste ao qual a questão pertence.
     * Relacionamento muitos-para-um:
     * várias questões podem compor o mesmo teste.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "testId", nullable = false)
    private Test testId;

    /**
     * Questão associada ao teste.
     * Relacionamento muitos-para-um:
     * uma mesma questão pode aparecer em vários testes.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "questionId", nullable = false)
    private Question questionId;

    /**
     * @return id do relacionamento.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do registro.
     *
     * @param id identificador.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return teste associado.
     */
    public Test getTestId() {
        return testId;
    }

    /**
     * Define o teste associado.
     *
     * @param testId teste.
     */
    public void setTestId(Test testId) {
        this.testId = testId;
    }

    /**
     * @return questão associada.
     */
    public Question getQuestionId() {
        return questionId;
    }

    /**
     * Define a questão associada.
     *
     * @param questionId questão.
     */
    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }
}