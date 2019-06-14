package com.example.prova2bb.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prova2bb.R;
import com.example.prova2bb.helper.competidorDAO;
import com.example.prova2bb.helper.passadasDAO;
import com.example.prova2bb.model.competidores;
import com.example.prova2bb.model.passadas;

public class adicionarPassada extends AppCompatActivity {

    private competidores competidor;
    private TextInputEditText editPassada;
    private passadas passada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_passada);

        editPassada = findViewById(R.id.editTextpassada);



        //Recebe os Dados da outra activity
        passada = (passadas) getIntent().getSerializableExtra("passadaSelecionada");
        competidor = (competidores) getIntent().getSerializableExtra("competidorSelecionada");


        //Seta o titulo que aparece para o usuario da activity
        setTitle(competidor.getName());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_remover_passada, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_salvar_passada:
                //Executar ação
                passadasDAO passadaDAO = new passadasDAO(getApplicationContext());


                if (passada != null) {
                    Double tempo = Double.parseDouble(editPassada.getText().toString());



                    if (tempo != 0 || tempo > 0 || tempo != null) {
                        passadas Passada = new passadas();
                        Passada.setTempo(tempo);
                        Passada.setCom_id(competidor);
                        Passada.setId_pass(passada.getId_pass());


                        if (passadaDAO.atualizar(Passada)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar Passada", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar Passada", Toast.LENGTH_SHORT).show();

                        }
                    }


                } else {
                    Double tempo = Double.parseDouble(editPassada.getText().toString());

                    if (tempo != 0 || tempo != null) {
                        passadas Passada = new passadas();
                        Passada.setTempo(tempo);
                        Passada.setCom_id(competidor);


                        if (passadaDAO.salvar(Passada)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar Passada", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar Passada", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        }
        return super.onOptionsItemSelected(item);
    }
}


