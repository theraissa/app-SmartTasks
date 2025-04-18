package com.example.prova_smarttasks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListagemAdapter extends RecyclerView.Adapter<ListagemAdapter.MyViewHolder> {

    private List<Tarefa> listagemTarefas;
    private Context context;

    //Construtor
    public ListagemAdapter(Context context, List<Tarefa> listagemTarefas) {
        this.context = context;
        this.listagemTarefas = listagemTarefas;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        Button btnDetalhes;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewNomeListagem);
            btnDetalhes = itemView.findViewById(R.id.buttonDetalhar);
        }
    }
    //O ViewHolder guarda as referências dos elementos visuais de cada item da lista
    //O itemView representa um único card/item da lista que está na tela.
    // Ele é baseado no XML item_tarefa.xml.

    //Esse método cria o layout visual de cada item da lista
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tarefa tarefa = listagemTarefas.get(position);
        holder.titulo.setText(tarefa.getTitulo());

        holder.btnDetalhes.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalharTarefa.class);
            // Para passar os dados da tarefa, implemente Parcelable na classe Tarefa
            // intent.putExtra("tarefa", tarefa);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listagemTarefas.size();
    }
}
