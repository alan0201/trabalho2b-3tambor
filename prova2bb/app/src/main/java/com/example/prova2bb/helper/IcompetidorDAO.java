package com.example.prova2bb.helper;




import com.example.prova2bb.model.competidores;

import java.util.List;

public interface IcompetidorDAO {

    public boolean salvar(competidores competidor);
    public boolean atualizar(competidores competidor);
    public boolean deletar(competidores competidor);



    public List<competidores> lista();
}
