package com.example.prova2bb.model;

import java.io.Serializable;

public class ranking implements Serializable {

    private String nome;
    private Double tempo;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return nome +" -- "+ tempo+"s" ;

    }
}
