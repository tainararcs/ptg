package br.trcs.ptg.model;

import java.time.LocalDate;

import br.trcs.ptg.enums.Bimester;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa um teste/prova gerado no sistema.
 * Um teste está associado a:
 * - um usuário (autor ou responsável).
 * - uma disciplina.
 * - um bimestre.
 * - um resultado (número de acertos).
 */
@Entity
@Table(name = "tests")
public class Test {

    /**
     * Identificador único do teste, com valor gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Data de realização ou criação do teste.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * Usuário responsável pelo teste.
     * Relacionamento muitos-para-um:
     * vários testes podem pertencer ao mesmo usuário.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User userID;

    /**
     * Disciplina à qual o teste pertence.
     * Relacionamento muitos-para-um:
     * vários testes podem estar associados à mesma disciplina.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subjectId;

    /**
     * Bimestre ao qual o teste se refere.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Bimester bimester;

    /**
     * Quantidade de respostas corretas obtidas no teste.
     */
    @Column(nullable = false)
    private int numberCorrectAnswers;

    /**
     * @return id do teste.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do teste.
     *
     * @param id identificador do teste.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return data do teste.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Define a data do teste.
     *
     * @param date data do teste.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return usuário responsável pelo teste.
     */
    public User getUserID() {
        return userID;
    }

    /**
     * Define o usuário responsável pelo teste.
     *
     * @param userID usuário.
     */
    public void setUserID(User userID) {
        this.userID = userID;
    }

    /**
     * @return disciplina associada ao teste.
     */
    public Subject getSubjectId() {
        return subjectId;
    }

    /**
     * Define a disciplina associada ao teste.
     *
     * @param subjectId disciplina.
     */
    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return bimestre do teste.
     */
    public Bimester getBimester() {
        return bimester;
    }

    /**
     * Define o bimestre do teste.
     *
     * @param bimester bimestre.
     */
    public void setBimester(Bimester bimester) {
        this.bimester = bimester;
    }

    /**
     * Retorna o número de respostas corretas.
     *
     * @return número de acertos.
     */
    public int getNumberCorrectAnswers() {
        return numberCorrectAnswers;
    }

    /**
     * Define o número de respostas corretas.
     *
     * @param numberCorrectAnswers número de acertos.
     */
    public void setNumberCorrectAnswers(int numberCorrectAnswers) {
        this.numberCorrectAnswers = numberCorrectAnswers;
    }
}