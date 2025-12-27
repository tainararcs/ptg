package br.trcs.ptg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa uma alternativa de resposta de uma questão.
 */
@Entity
@Table(name = "options_question")
public class OptionsQuestion {

    /**
     * Identificador único da alternativa, com valor gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Texto da alternativa.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    /**
     * Indica se a alternativa é correta.
     * true  -> alternativa correta
     * false -> alternativa incorreta
     */
    @Column(nullable = false)
    private Boolean correct;

    /**
     * Questão à qual esta alternativa pertence.
     * Relacionamento muitos-para-um:
     * várias alternativas pertencem à mesma questão.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "questionId", nullable = false)
    private Question questionId;

    /**
     * @return id da alternativa.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador da alternativa.
     *
     * @param id identificador da alternativa.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return texto da alternativa.
     */
    public String getText() {
        return text;
    }

    /**
     * Define o texto da alternativa.
     *
     * @param text texto da alternativa.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retorna se a alternativa é correta.
     *
     * @return true se correta, false caso contrário.
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * Define se a alternativa é correta.
     *
     * @param correct indicador de correção.
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    /**
     * @return questão associada à alternativa.
     */
    public Question getQuestionId() {
        return questionId;
    }

    /**
     * Define a questão associada à alternativa.
     *
     * @param questionId questão.
     */
    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }
}