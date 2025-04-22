package com.example.prova_smarttasks.AnaliseInteligente;

import android.graphics.Color;
import android.util.Log;

import java.util.Calendar;

public class CalculoDeDias {

    static public String calculoDeDias(String data) {
        Log.i("data", "data salva: " + data);

        Calendar dataAtual = Calendar.getInstance();
        Calendar dataTarefa = Calendar.getInstance();

        // Obtém os componentes da data atual
        int year = dataAtual.get(Calendar.YEAR);
        int month = dataAtual.get(Calendar.MONTH); // Janeiro é 0, então adicionamos 1
        int day = dataAtual.get(Calendar.DAY_OF_MONTH);

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

        if (dias <= 3 && dias >= 0) {
            return "❗ Urgente " + dias + " dia(s) para concluir a tarefa";
        } else if (dias <= 6 && dias >= 0) {
            return "⌛ Faltam: " + dias + " dia(s) restante(s)";
        } else if (dias < 0){
            return "⚠️ Tarefa atrasada ou concluída há " + Math.abs(dias) + " dia(s)";
        } else {
            return "✅ " + dias + " dia(s) restante(s)";
        }
    }
}
