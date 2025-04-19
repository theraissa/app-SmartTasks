package com.example.prova_smarttasks.AnaliseInteligente;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova_smarttasks.ListagemAdapter;
import com.example.prova_smarttasks.R;
import com.example.prova_smarttasks.Tarefa;

import java.util.List;

public class AnaliseInteligenteAdapter  extends RecyclerView.Adapter<AnaliseInteligenteAdapter.MyViewHolder>{

    private List<Tarefa> listagemTarefas;
    private Context context;

    public AnaliseInteligenteAdapter(Context context, List<Tarefa> listagemTarefas) {
        this.context = context;
        this.listagemTarefas = listagemTarefas;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView data;
        TextView prazo;


        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewNTituloAnaliseInteligente);
            data = itemView.findViewById(R.id.textViewDataAnaliseInteligente);
            prazo = itemView.findViewById(R.id.textViewTempoAnaliseInteligente);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa_analise_inteligente, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tarefa tarefa = listagemTarefas.get(position);
        holder.titulo.setText(tarefa.getTitulo());
        holder.data.setText(tarefa.getData());
        holder.prazo.setText("Calculo de quantos dias faltam");
    }

    @Override
    public int getItemCount() {
        return listagemTarefas.size();
    }
}
