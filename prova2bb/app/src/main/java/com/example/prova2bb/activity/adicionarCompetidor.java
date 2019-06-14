package com.example.prova2bb.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.prova2bb.R;
import com.example.prova2bb.helper.competidorDAO;
import com.example.prova2bb.model.competidores;

public class adicionarCompetidor extends AppCompatActivity {

    private TextInputEditText editCompetidor;
    private competidores competidorAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_competidor);

        editCompetidor = findViewById(R.id.textCompetidor);

        competidorAtual = (competidores) getIntent().getSerializableExtra("competidorSelecionada");

        if(competidorAtual != null){
            editCompetidor.setText(competidorAtual.getName());
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_remover_competidor,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_salvar_passada:
                //Executar ação
                competidorDAO competidoDAO = new competidorDAO(getApplicationContext());

                if(competidorAtual != null){
                    String nomeCompetidor = editCompetidor.getText().toString();

                    if(!nomeCompetidor.isEmpty()) {
                        competidores Competidores = new competidores();
                        Competidores.setName(nomeCompetidor);
                        Competidores.setId_com(competidorAtual.getId_com());

                        if (competidoDAO.atualizar(Competidores)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar competidor", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar competidor", Toast.LENGTH_SHORT).show();

                        }
                    }


                }else{
                    String nomeCompetidor = editCompetidor.getText().toString();

                    if(!nomeCompetidor.isEmpty()){
                        competidores Competidor = new competidores();
                        Competidor.setName(nomeCompetidor);

                        if(competidoDAO.salvar(Competidor)){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao salvar Competidor",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao salvar competidor",Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                break;

            case R.id.item_deletar:

                competidoDAO = new competidorDAO(getApplicationContext());
                 competidores competidor = new competidores();
                 competidor.setId_com(competidorAtual.getId_com());
                 competidor.setName(competidorAtual.getName());


                        if (competidoDAO.deletar(competidor)) {

                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao Deletar competidor", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao deletar competidor", Toast.LENGTH_SHORT).show();

                        }






        }
        return super.onOptionsItemSelected(item);
    }
}
