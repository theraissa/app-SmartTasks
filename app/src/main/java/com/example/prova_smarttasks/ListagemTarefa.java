package com.example.prova_smarttasks;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ListagemTarefa extends AppCompatActivity {

    private SQLiteDatabase bancoDeDados;
    private RecyclerView recyclerView;
    private ListagemAdapter adapter;
    private ArrayList<Tarefa> listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewListagemTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaTarefas = new ArrayList<>();

        listarBanco(); // preenche listaTarefas

        adapter = new ListagemAdapter(this, listaTarefas);
        recyclerView.setAdapter(adapter);

    }
    public void AbrirTelaPrincipal(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void listarBanco() {
        try {
            bancoDeDados = openOrCreateDatabase("tarefaDB", MODE_PRIVATE, null);
            Cursor cursor = bancoDeDados.rawQuery("SELECT id, titulo, descricao, data FROM tarefa", null);

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








}