package com.example.prova2bb.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    public static int VERSION = 1;
    public static String NOME_DB ="DB_TAREFA";
    public static String TABELA_COMPETIDORES = "competidores";
    public static String TABELA_PASSADAS = "passadas";



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA_COMPETIDORES +
                " (id_com INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);";

        try {
            db.execSQL(sql);
            Log.i("LOGBANCO","Suceso ao criar tabela");

        }catch (Exception e) {
            Log.i("LOGBANCO", "Erro ao criar a tabela" + e.getMessage());
        }


        String sql1 = "CREATE TABLE IF NOT EXISTS  " +TABELA_PASSADAS+
                "(id_pass INTEGER PRIMARY KEY AUTOINCREMENT, tempo DOUBLE, com_id INTEGER, FOREIGN KEY(com_id) " +
                "REFERENCES competidores (id_com) );";
        try {
            db.execSQL(sql1);
            Log.i("LOGBANCO","Suceso ao criar tabela");

        }catch (Exception e){
            Log.i("LOGBANCO","Erro ao criar a tabela"+e.getMessage());
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS competidores";
        String sql1 = "DROP TABLE IF EXISTS passadas";

        try {
            db.execSQL(sql);
            db.execSQL(sql1);
            onCreate(db);
            Log.i("LOGBANCO", "Sucesso ao atualizar APP");
        }catch (Exception e){
            Log.i("LOGBANCO", "Erro ao atualizar APP"+e.getMessage());
        }

    }
}
