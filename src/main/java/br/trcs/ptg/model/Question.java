package br.trcs.ptg.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa uma questão (pergunta) do sistema.
 * Uma questão está associada a:
 * - uma disciplina (Subject).
 * - uma matéria (Topic).
 * - um conjunto de alternativas (OptionsQuestion).
 */
@Entity
@Table(name = "questions")
public class Question {

    /**
     * Identificador único da questão, com valor gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Enunciado da questão.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String statement;

    /**
     * Lista de alternativas associadas à questão.
     * Relacionamento um-para-muitos:
     * uma questão pode possuir várias alternativas.
     *
     * - mappedBy: a chave estrangeira está no lado da entidade OptionsQuestion.
     */
    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionsQuestion> optionsList;

    /**
     * Observações adicionais sobre a questão. Campo opcional.
     */
    private String obs;

    /**
     * Disciplina à qual a questão pertence.
     * Relacionamento muitos-para-um:
     * várias questões podem pertencer à mesma disciplina.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subjectId;

    /**
     * Matéria a qual a questão está associada.
     * Relacionamento muitos-para-um:
     * várias questões podem pertencer ao mesmo tópico.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "topicId", nullable = false)
    private Topic topicId;

    /**
     * @return id da questão.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador da questão.
     *
     * @param id identificador da questão.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return enunciado da questão.
     */
    public String getStatement() {
        return statement;
    }

    /**
     * Define o enunciado da questão.
     *
     * @param statement texto do enunciado.
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     * @return lista de alternativas da questão.
     */
    public List<OptionsQuestion> getOptionsList() {
        return optionsList;
    }

    /**
     * Define a lista de alternativas da questão.
     *
     * @param optionsList lista de alternativas.
     */
    public void setOptionsList(List<OptionsQuestion> optionsList) {
        this.optionsList = optionsList;
    }

    /**
     * @return observações adicionais da questão.
     */
    public String getObs() {
        return obs;
    }

    /**
     * Define observações adicionais da questão.
     *
     * @param obs observações
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return disciplina associada à questão.
     */
    public Subject getSubjectId() {
        return subjectId;
    }

    /**
     * Define a disciplina associada à questão.
     *
     * @param subjectId disciplina.
     */
    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return matéria associada à questão.
     */
    public Topic getTopicId() {
        return topicId;
    }

    /**
     * Define a matéroa associada à questão.
     *
     * @param topicId matéria.
     */
    public void setTopicId(Topic topicId) {
        this.topicId = topicId;
    }
}