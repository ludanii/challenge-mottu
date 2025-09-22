package br.com.fiap.challengemottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Table(name = "tab_clientes")
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String telefone;
    private Integer idade;

    @OneToMany(mappedBy = "cliente")
    private List<Moto> motos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }
}
