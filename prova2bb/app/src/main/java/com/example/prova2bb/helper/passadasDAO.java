package com.example.prova2bb.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.prova2bb.model.passadas;

import java.util.ArrayList;
import java.util.List;

public class passadasDAO implements IpassadaDAO{

    private SQLiteDatabase le;
    private SQLiteDatabase escreve;


    public passadasDAO(Context context) {
        DbHelper db = new DbHelper(context);
        le = db.getReadableDatabase();
        escreve = db.getWritableDatabase();
    }


    @Override
    public boolean salvar(passadas passada) {
        ContentValues cv = new ContentValues();
        cv.put("tempo", passada.getTempo());
        cv.put("com_id", passada.getCom_id().getId_com());



        try {
            escreve.insert(DbHelper.TABELA_PASSADAS, null, cv);
            Log.i("RESULTADO","Passada salvar com sucesso");

        }catch (Exception e){
            Log.i("RESULTADO", "Erro ao salvar passadavsfvdsfdfghdf");

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(passadas passada) {
        ContentValues cv = new ContentValues();
        cv.put("tempo", passada.getTempo());
        cv.put("com_id", passada.getCom_id().getId_com() );


        try {
            String[] agrs = {passada.getId_pass().toString()};
            escreve.update(DbHelper.TABELA_PASSADAS,cv, "id_pass=?",agrs);
            Log.i("RESULTADO","Sucesso ao atualizar passada");

        }catch (Exception e){
            Log.i("RESULTADO", "Erro ao atualizar passada");
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(passadas passada) {
        try {
            String[] args = {passada.getId_pass().toString()};
            escreve.delete(DbHelper.TABELA_PASSADAS, "id_pass=?",args);
            Log.i("RESULTADO", "Sucesso ao removere competidor");

        }catch (Exception e){
            Log.i("RESULTADO", "erro ao removere competidor");
            return false;

        }

        return true;
    }

    @Override
    public List<passadas> lista() {
        List<passadas> passadaslist =  new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_PASSADAS+";";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){

            passadas passada = new passadas();

            Long id = c.getLong(c.getColumnIndex("id_pass"));
            Double tempo = c.getDouble(c.getColumnIndex("tempo"));

            passada.setId_pass(id);
            passada.setTempo(tempo);

            passadaslist.add(passada);
        }



        return passadaslist;

    }
}
