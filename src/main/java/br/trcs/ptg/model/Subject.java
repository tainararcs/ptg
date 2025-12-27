package br.trcs.ptg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa uma disciplina do sistema.
 * Esta classe é mapeada para a tabela "subjects" no banco de dados.
 */
@Entity
@Table(name = "subjects")
public class Subject {

    /**
     * Identificador único da disciplina, com valor gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome da disciplina.
     */
    @Column(nullable = false)
    private String name;

    /**
     * @return id da disciplina.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador da disciplina.
     *
     * @param id identificador da disciplina.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nome da disciplina.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da disciplina.
     *
     * @param name nome da disciplina.
     */
    public void setName(String name) {
        this.name = name;
    }
}