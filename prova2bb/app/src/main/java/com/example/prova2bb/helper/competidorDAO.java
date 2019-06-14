package com.example.prova2bb.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.prova2bb.model.competidores;
import com.example.prova2bb.model.passadas;

import java.util.ArrayList;
import java.util.List;

public class competidorDAO implements IcompetidorDAO {

    private SQLiteDatabase le;
    private SQLiteDatabase escreve;

    public competidorDAO(Context context) {
        DbHelper db = new DbHelper(context);
        le = db.getReadableDatabase();
        escreve = db.getWritableDatabase();
    }


    @Override
    public boolean salvar(competidores competidor) {
        ContentValues cv = new ContentValues();
        cv.put("nome", competidor.getName() );

        try {
            escreve.insert(DbHelper.TABELA_COMPETIDORES, null, cv);
            Log.i("RESULTADO","competidor salvar com sucesso");

        }catch (Exception e){
            Log.i("RESULTADO", "Erro ao salvar tarefa");
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(competidores competidor) {
        ContentValues cv = new ContentValues();
        cv.put("nome", competidor.getName() );

        try {
            String[] agrs = {competidor.getId_com().toString()};
            escreve.update(DbHelper.TABELA_COMPETIDORES,cv, "id_com=?",agrs);
            Log.i("RESULTADO","Sucesso ao atualizar competidor");

        }catch (Exception e){
            Log.i("RESULTADO", "Erro ao atualizar competidor");
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(competidores competidor) {
        try {
            String[] args = {competidor.getId_com().toString()};
            escreve.delete(DbHelper.TABELA_COMPETIDORES, "id_com=?",args);
            Log.i("RESULTADO", "Sucesso ao removere competidor");

        }catch (Exception e){
            Log.i("RESULTADO", "erro ao removere competidor");
            return false;

        }

        return true;
    }

    @Override
    public List<competidores> lista() {

        List<competidores> competidoreslist =  new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_COMPETIDORES+";";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){

            competidores competidore = new competidores();

            Long id = c.getLong(c.getColumnIndex("id_com"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            competidore.setId_com(id);
            competidore.setName(nomeTarefa);

            competidoreslist.add(competidore);
        }



        return competidoreslist;
    }


    public passadas buscaPassada(competidores competidor){

        String sql = "SELECT * FROM "+DbHelper.TABELA_PASSADAS+" P " +
                " WHERE com_id = "+competidor.getId_com();
        Cursor c = le.rawQuery(sql, null);

        passadas pass = new passadas();
        if(c.getCount() != 0 ){

            if(c.moveToNext()){
                passadas passada = new passadas();
                Long id = c.getLong(c.getColumnIndex("id_pass"));
                Double tempo = c.getDouble(c.getColumnIndex("tempo"));

                passada.setId_pass(id);
                passada.setTempo(tempo);
                passada.setCom_id(competidor);
                pass = passada;

            }



           return pass;
        }else{
            return null;
        }






    }
}
