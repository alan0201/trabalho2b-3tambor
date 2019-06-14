package com.example.prova2bb.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.prova2bb.model.competidores;
import com.example.prova2bb.model.passadas;
import com.example.prova2bb.model.ranking;

import java.util.ArrayList;
import java.util.List;

public class rankingDAO {



    private SQLiteDatabase le;
    private SQLiteDatabase escreve;

    public rankingDAO(Context context) {
        DbHelper db = new DbHelper(context);
        le = db.getReadableDatabase();
        escreve = db.getWritableDatabase();
    }



    public List<ranking> buscaRanking(){

        List<ranking> rankinglist =  new ArrayList<>();
        String sql = "SELECT C.nome, P.tempo FROM "+DbHelper.TABELA_COMPETIDORES+" C " +
                " left JOIN "+DbHelper.TABELA_PASSADAS+" P ON (C.id_com = P.com_id)" +
                " order by P.tempo ASC ; ";

        Cursor c = le.rawQuery(sql, null);


            while(c.moveToNext()) {

                ranking rank = new ranking();


                rank.setNome(c.getString(0));
                rank.setTempo(c.getDouble(1));

                rankinglist.add(rank);
            }
            return rankinglist;



    }
}
