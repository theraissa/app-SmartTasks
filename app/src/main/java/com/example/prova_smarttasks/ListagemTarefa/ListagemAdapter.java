package com.example.prova_smarttasks.ListagemTarefa;

import static com.example.prova_smarttasks.AnaliseInteligente.CalculoDeDias.calculoDeDias;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prova_smarttasks.AnaliseInteligente.CalculoDeDias;
import com.example.prova_smarttasks.DetalharTarefa;
import com.example.prova_smarttasks.R;
import com.example.prova_smarttasks.Tarefa;

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
            intent.putExtra("id", tarefa.getId());  // Passando o ID da tarefa
            intent.putExtra("titulo", tarefa.getTitulo());  // Passando o título
            intent.putExtra("descricao", tarefa.getDescricao());  // Passando a descrição
            intent.putExtra("data", tarefa.getData());  // Passando a data
            intent.putExtra("prioridade", calculoDeDias(tarefa.getData()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listagemTarefas.size();
    }

}
