package com.fiebtcc.barbersclub.barbersclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Agenda")
public class Agenda {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column
    private Date horario;
    @Column(length = 25)
    private String tipo_servico;
    @Column(nullable = false)
    private boolean codStatus;

    @Transient  //Atributos que não estão em uma coluna
    @JsonIgnore
    private String mensagemErro = "";

    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getTipo_servico() {
        return tipo_servico;
    }

    public void setTipo_seriveco(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarAgenda(){

        return isValid;
    }
}