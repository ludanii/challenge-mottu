package br.com.fiap.challengemottu.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tab_patios")
@Entity
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String logradouro;

    private Integer capacidade;
    
    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios = new ArrayList<>();

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    private List<Moto> motos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }
}
