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
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ListagemTarefa extends AppCompatActivity {

    private SQLiteDatabase bancoDeDados;
    public ListView listaTarefas;
    ArrayList<Integer> arrayIds;

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

        listarBanco();
        //listaTarefas = findViewById(R.id.recyclerViewLista);


    }
    public void AbrirTelaPrincipal(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void listarBanco() {
        arrayIds = new ArrayList<Integer>();
        bancoDeDados = openOrCreateDatabase("tarefaDB", MODE_PRIVATE, null);
        Cursor meuCursor = bancoDeDados.rawQuery("SELECT id, titulo, descricao, data FROM tarefa", null);
        ArrayList<String> linhas = new ArrayList<>();
        ArrayAdapter<String> meuAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                linhas
        );
        //listaTarefas.setAdapter(meuAdapter);

        try {
            if (meuCursor.moveToFirst()) {
                Log.d("banco", meuCursor.getString(1));
                do {
                    String linha = "Tarefa: " + meuCursor.getString(1);
                    linhas.add(linha);
                    arrayIds.add(meuCursor.getInt(0));
                } while (meuCursor.moveToNext());
                meuCursor.close();
            }
        } catch (Exception e) {
            Log.i("DB", "erro ao acessar banco de dados");
        }
    }
}