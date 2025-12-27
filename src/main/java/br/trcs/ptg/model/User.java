package br.trcs.ptg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa um usuário do sistema.
 * Esta classe é mapeada para a tabela "users" no banco de dados.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Identificador único do usuário, com valor gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nome do usuário, utilizado para identificação e autenticação no sistema.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Senha do usuário.
     * Observação: a senha é armazenada com hash.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Perfil do usuário.
     * Define o papel ou nível de acesso do usuário.
     */
    @Column(nullable = false)
    private String profile;

    /**
     * @return id do usuário.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do usuário.
     *
     * @param id identificador do usuário.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nome do usuário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do usuário.
     *
     * @param name nome do usuário.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return senha do usuário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return perfil do usuário.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Define o perfil do usuário.
     *
     * @param profile perfil do usuário.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }
}