package br.com.fiap.challengemottu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatio;
    private String localizacao;
    private int quantidadeVagas;

    public Long getIdPatio() {
        return idPatio;
    }

    public void setIdPatio(Long idPatio) {
        this.idPatio = idPatio;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getquantidadeVagas() {
        return quantidadeVagas;
    }

    public void setquantidadeVagas(int quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }
}
