package com.example.prova_smarttasks.AnaliseInteligente;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prova_smarttasks.R;
import com.example.prova_smarttasks.Tarefa;

import java.util.Calendar;
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
        holder.prazo.setText(calculoDeDias(tarefa.getData()));
    }

    @Override
    public int getItemCount() {
        return listagemTarefas.size();
    }

    public String calculoDeDias(String data) {
        Log.i("data", "data salva: " + data);

        Calendar dataAtual = Calendar.getInstance();
        Calendar dataTarefa = Calendar.getInstance();

        // Obtém os componentes da data atual
        int year = dataAtual.get(Calendar.YEAR);
        int month = dataAtual.get(Calendar.MONTH); // Janeiro é 0, então adicionamos 1
        int day = dataAtual.get(Calendar.DAY_OF_MONTH);

//        dataTarefa.set(Calendar.DAY_OF_MONTH, day);
//        dataTarefa.set(Calendar.MONTH, month);
//        dataTarefa.set(Calendar.YEAR, year);

        String str = data;
        String[] listaData = str.split("/");

        int diaTarefa = Integer.parseInt(listaData[0]);
        int mesTarefa = Integer.parseInt(listaData[1]) - 1;
        int anoTarefa = Integer.parseInt(listaData[2]);

        dataTarefa.set(Calendar.DAY_OF_MONTH, diaTarefa);
        dataTarefa.set(Calendar.MONTH, mesTarefa);
        dataTarefa.set(Calendar.YEAR, anoTarefa);

        long diferencaMillis = dataTarefa.getTimeInMillis() - dataAtual.getTimeInMillis();
        long dias = diferencaMillis / (1000 * 60 * 60 * 24);

        if (dias <= 3 && dias > 0) {
            return "Prioridade: Urgente, faltam " + dias + "dias para a concluir a tarefa";
        } else if (dias >= 10 && dias > 0) {
            return "Prioridade: Média, faltam " + dias + "dias para a concluir a tarefa";
        } else if (dias < 0){
            return "Tarefa atrasada ou concluida " + dias;
        } else {
            return "Dias restantes para a terafa " + dias;
        }
    }
}
