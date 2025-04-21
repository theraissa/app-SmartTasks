package com.example.prova_smarttasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prova_smarttasks.AnaliseInteligente.AnaliseInteligente;
import com.example.prova_smarttasks.ListagemTarefa.ListagemTarefa;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void AbrirTelaCadastroTarefa(View v) {
        Intent intent = new Intent(this, CadastroTarefa.class);
        startActivity(intent);
    }

    public void AbrirTelaListagemTarefas(View v) {
        Intent intent = new Intent(this, ListagemTarefa.class);
        startActivity(intent);
    }

    public void AbrirTelaCompartilharLink(View v) {
        Intent intent = new Intent(this, CompartilharLink.class);
        startActivity(intent);
    }

    public void AbrirTelaAnaliseInteligente(View v) {
        Intent intent = new Intent(this, AnaliseInteligente.class);
        startActivity(intent);
    }

    public void AbrirTelaSobreNos(View v) {
        Intent intent = new Intent(this, SobreNos.class);
        startActivity(intent);
    }

}