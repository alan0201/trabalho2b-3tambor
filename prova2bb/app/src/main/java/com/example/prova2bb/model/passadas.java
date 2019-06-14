package com.example.prova2bb.model;

import java.io.Serializable;

public class passadas implements Serializable {

    private Long id_pass;
    private Double tempo;
    private competidores com_id;

    public Long getId_pass() {
        return id_pass;
    }

    public void setId_pass(Long id_pass) {
        this.id_pass = id_pass;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public competidores getCom_id() {
        return com_id;
    }

    public void setCom_id(competidores com_id) {
        this.com_id = com_id;
    }
}
