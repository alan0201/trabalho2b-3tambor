package com.example.prova2bb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.prova2bb.R;
import com.example.prova2bb.adapter.competidorAdapter;
import com.example.prova2bb.helper.RecyclerItemClickListener;
import com.example.prova2bb.helper.competidorDAO;
import com.example.prova2bb.model.competidores;
import com.example.prova2bb.model.passadas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private competidorAdapter compAdapter;
    private List<competidores> Competidores = new ArrayList<>();
    private competidores competidorSelecionada;
    private competidorDAO cDao;
    private passadas passadaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cDao = new competidorDAO(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView);

        //adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //pega o competidor pelo index
                        competidorSelecionada = Competidores.get(position);

                        //pega a passada pelo codigo do competidor
                        passadaSelecionada = cDao.buscaPassada(competidorSelecionada);

                        Bundle extras = new Bundle();
                        extras.putSerializable("competidorSelecionada", competidorSelecionada);
                        extras.putSerializable("passadaSelecionada",passadaSelecionada);

                        Intent intent = new Intent(getApplicationContext(), adicionarPassada.class);

                        intent.putExtras(extras);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.i("clique:", "onLongClick");


                        competidorSelecionada = Competidores.get(position);

                        Intent intent = new Intent(getApplicationContext(), adicionarCompetidor.class);
                        intent.putExtra("competidorSelecionada", competidorSelecionada);

                        startActivity(intent);


                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),adicionarCompetidor.class));
            }
        });
    }


    public void carregarlistacompetidores(){
        //listar tarefas
        competidorDAO  COmpetidorDAO = new competidorDAO(getApplicationContext());



        //exibir lista de tarefas no recycleview
        Competidores = COmpetidorDAO.lista();

        //confidurar um adapter
        compAdapter = new competidorAdapter(Competidores);

        //configurar o recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(compAdapter);

    }
    @Override
    protected void onStart() {
        carregarlistacompetidores();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
