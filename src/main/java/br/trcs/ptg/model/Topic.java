package br.trcs.ptg.model;

import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.enums.Grade;
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
 * Entidade JPA que representa uma matéria específica dentro de uma disciplina.
 * Um tópico está associado a:
 * - uma disciplina (Subject).
 * - uma série/ano (Grade).
 * - um bimestre (Bimester).
 */
@Entity
@Table(name = "topics")
public class Topic {

    /**
     * Identificador único de uma matéria, com valor gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome do tópico.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Série/ano ao qual o tópico pertence.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    /**
     * Bimestre em que o tópico é trabalhado.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Bimester bimester;

    /**
     * Disciplina associada ao tópico.
     * Relacionamento muitos-para-um:
     * vários tópicos podem pertencer à mesma disciplina.
     * Representa a chave estrangeira na tabela "topics".
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subjectId;

    /**
     * @return id do tópico.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do tópico.
     *
     * @param id identificador do tópico.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nome do tópico.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do tópico.
     *
     * @param name nome do tópico.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return grade do tópico.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Define a série/ano do tópico.
     *
     * @param grade série/ano do tópico.
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * @return bimestre do tópico.
     */
    public Bimester getBimester() {
        return bimester;
    }

    /**
     * Define o bimestre do tópico.
     *
     * @param bimester bimestre do tópico.
     */
    public void setBimester(Bimester bimester) {
        this.bimester = bimester;
    }

    /**
     * @return disciplina do tópico.
     */
    public Subject getSubjectId() {
        return subjectId;
    }

    /**
     * Define a disciplina associada ao tópico.
     *
     * @param subjectId disciplina do tópico.
     */
    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }
}