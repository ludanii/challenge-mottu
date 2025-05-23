package br.com.fiap.challengemottu.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "tab_motos")
@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;
    private String modelo;
    private String placa;
    private String status; // disponível, alugada, em manutenção
    private String setor; // bom, intermediário, ruim

    @ManyToOne
    @JoinColumn(name = "id_patio")
    private Patio patio;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
    private Cliente cliente;

    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
