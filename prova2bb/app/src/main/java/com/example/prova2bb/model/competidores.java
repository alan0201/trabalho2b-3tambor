package com.example.prova2bb.model;

import java.io.Serializable;

public class competidores implements Serializable {

    private Long id_com;
    private String name;



    public Long getId_com() {
        return id_com;
    }

    public void setId_com(Long id_com) {
        this.id_com = id_com;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
