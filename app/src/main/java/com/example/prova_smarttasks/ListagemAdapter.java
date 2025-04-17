package com.example.prova_smarttasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListagemAdapter extends RecyclerView.Adapter<ListagemAdapter.MyViewHolder> {

    private List<Listagem> listagemTarefas;
    private OnDetalharClickListener listener;

    public interface OnDetalharClickListener {
        void onDetalharClick(Listagem tarefa);
    }

    //método construtor
    public ListagemAdapter(List<Listagem> listaTarefas, OnDetalharClickListener listener) {
        this.listagemTarefas = listaTarefas;
        this.listener = listener;
    }

    //Aqui definimos os elementos que vamos manipular para cada item da lista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        Button botaoDetalhar;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNomeListagem);
            botaoDetalhar = itemView.findViewById(R.id.buttonDetalhar);
        }

        //conecta dados com a view, como coloca o nome da tarefa no TextView
        public void bind(Listagem tarefa, OnDetalharClickListener listener) {
            nome.setText(tarefa.getNome());
            botaoDetalhar.setOnClickListener(v -> listener.onDetalharClick(tarefa));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listagem_tarefa, parent, false);
        return new MyViewHolder(item);
    }

    //Aqui colocamos os dados da tarefa no item visual correspondente da posição atual.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(listagemTarefas.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listagemTarefas.size();
    }
}
