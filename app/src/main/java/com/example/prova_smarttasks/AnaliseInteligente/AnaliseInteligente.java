package com.example.prova_smarttasks.AnaliseInteligente;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova_smarttasks.R;
import com.example.prova_smarttasks.Tarefa;

import java.util.ArrayList;

public class AnaliseInteligente extends AppCompatActivity {

    private SQLiteDatabase bancoDeDados;
    private RecyclerView recyclerView;
    private AnaliseInteligenteAdapter adapter;
    private ArrayList<Tarefa> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_analise_inteligente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerViewListagemTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaTarefas = new ArrayList<>();

        listarBanco(); // preenche listaTarefas

        adapter = new AnaliseInteligenteAdapter(this, listaTarefas);
        recyclerView.setAdapter(adapter);
    }

    public void listarBanco() {
        try {
            bancoDeDados = openOrCreateDatabase("tarefaDB", MODE_PRIVATE, null);
            Cursor cursor = bancoDeDados.rawQuery("SELECT id, titulo, descricao, data FROM tarefa ORDER BY data ASC LIMIT 5", null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String titulo = cursor.getString(1);
                    String descricao = cursor.getString(2);
                    String data = cursor.getString(3);

                    Tarefa tarefa = new Tarefa(id, titulo, descricao, data);
                    listaTarefas.add(tarefa);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AbrirTelaPrincipal(View v) {
        finish();
    }
}