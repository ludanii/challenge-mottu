package br.com.fiap.challengemottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Table(name = "tab_funcionarios")
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;
    private String nomeUsuario;
    @Email
    private String email;
    private String senha;

    @OneToMany(mappedBy = "funcionario")
    private List<Patio> patios;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Patio> getPatios() {
        return patios;
    }

    public void setPatios(List<Patio> patios) {
        this.patios = patios;
    }
}
