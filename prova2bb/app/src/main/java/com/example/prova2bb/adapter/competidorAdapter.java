package com.example.prova2bb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prova2bb.R;
import com.example.prova2bb.model.competidores;

import java.util.List;

public class competidorAdapter extends RecyclerView.Adapter<competidorAdapter.MyViewHolder> {

    private List<competidores> listaCompetidores;

    public competidorAdapter(List<competidores> lista) {
        this.listaCompetidores = lista;
    }

    @NonNull
    @Override
    public competidorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_competidor_adapter ,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        competidores competidor = listaCompetidores.get(i);
        myViewHolder.competidor.setText(competidor.getName());
    }

    @Override
    public int getItemCount() {
        return this.listaCompetidores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView competidor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            competidor = itemView.findViewById(R.id.textCompetidor);
        }
    }
}
