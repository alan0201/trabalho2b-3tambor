package com.example.prova2bb.helper;


import com.example.prova2bb.model.passadas;

import java.util.List;

public interface IpassadaDAO {


    public boolean salvar(passadas passada);
    public boolean atualizar(passadas passada);
    public boolean deletar(passadas passada);
    public List<passadas> lista();
}
