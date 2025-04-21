package com.example.prova_smarttasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prova_smarttasks.ListagemTarefa.ListagemTarefa;

public class DetalharTarefa extends AppCompatActivity {

    private TextView nomeTarefa, descricaoTarefa, dataTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhar_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomeTarefa = findViewById(R.id.textViewNomeTarefa);
        descricaoTarefa = findViewById(R.id.textViewDescricaoTarefa);
        dataTarefa = findViewById(R.id.textViewDataTarefa);

        // Receber os dados do Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String titulo = intent.getStringExtra("titulo");
        String descricao = intent.getStringExtra("descricao");
        String data = intent.getStringExtra("data");

        // Exibir os dados na tela
        nomeTarefa.setText(titulo);
        descricaoTarefa.setText(descricao);
        dataTarefa.setText(data);
    }

    public void AbrirTelaListagemTarefas(View v) {
        Intent intent = new Intent(this, ListagemTarefa.class);
        startActivity(intent);
    }
}