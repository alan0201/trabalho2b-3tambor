package com.example.prova2bb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prova2bb.R;
import com.example.prova2bb.helper.rankingDAO;
import com.example.prova2bb.model.ranking;


import java.util.List;

public class rankingPassadas extends AppCompatActivity {

    private AdapterView adapter;
    private rankingDAO rDAO ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_passadas);
        rDAO = new rankingDAO(getApplicationContext());


        ListView rankingList =  findViewById(R.id.ListViewRanking);
        List<ranking> ranki = rDAO.buscaRanking();

        if(ranki != null){

            ArrayAdapter<ranking> adapter = new ArrayAdapter<ranking>(this,android.R.layout.simple_list_item_1,
                    ranki);

            rankingList.setAdapter(adapter);
        }else{
            Log.i("TESTE","ERRO");
        }









    }
}
