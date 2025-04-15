package com.example.prova_smarttasks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CadastroTarefa extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    public TextView avisoCadastroTarefa;

    EditText titulo;
    EditText descricao;
    EditText dataDaTarefa;
    Calendar dataCadastroTarefa = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titulo = findViewById(R.id.editTextTitulo);
        descricao = findViewById(R.id.editTextDescricao);
        dataDaTarefa = findViewById(R.id.editTextData);
        avisoCadastroTarefa = findViewById(R.id.textViewAvisoCadastroTarefa);

        dataDaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirJanela();
            }
        });
        criarBancoDados();
    }
    private void abrirJanela(){
        int ano = dataCadastroTarefa.get(Calendar.YEAR);
        int mes = dataCadastroTarefa.get(Calendar.MONTH);
        int dia = dataCadastroTarefa.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataCadastroTarefa.set(Calendar.YEAR, year);
                dataCadastroTarefa.set(Calendar.MONTH, month);
                dataCadastroTarefa.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        }, ano, mes, dia);
        datePickerDialog.show();
    }
    private void updateLabel() {
        String myformat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myformat, new Locale("pt", "BR"));
        dataDaTarefa.setText(sdf.format(dataCadastroTarefa.getTime()));
    }
    public void cadastrarDataDeTarefa(){
        Calendar hoje = Calendar.getInstance();
        int anoAtual = hoje.get(Calendar.YEAR);
        int mesAtual = hoje.get(Calendar.MONTH);
        int diaAtual = hoje.get(Calendar.DAY_OF_MONTH);
        avisoCadastroTarefa.setText("Nova Tarefa:" + diaAtual + "/" + mesAtual + "/" + anoAtual);
    }
    public void SalvarTarefa(View view){
        cadastrarDataDeTarefa();
        registrarTarefa();
    }
    public void AbrirTelaPrincipal(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registrarTarefa();
    }

    public void criarBancoDados() {
        bancoDados = openOrCreateDatabase("tarefaDB", MODE_PRIVATE, null);
        try {
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tarefa(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "titulo VARCHAR," +
                    "descricao VARCHAR," +
                    "data TIMESTAMP)");
            Log.i(null, "Banco criado!!");
        } catch (Exception e) {
            Log.i(null, "Erro ao criar banco :/");
        }
    }
    public void registrarTarefa() {
        if (!TextUtils.isEmpty(titulo.getText().toString())) {
            try {
                bancoDados = openOrCreateDatabase("tarefaDB", MODE_PRIVATE, null);
                String sql = "INSERT INTO tarefa (titulo, descricao, data) VALUES (?, ?, ?)";
                SQLiteStatement stmt = bancoDados.compileStatement(sql);
                stmt.bindString(1, titulo.getText().toString());
                stmt.bindString(2, descricao.getText().toString());
                stmt.bindString(3, dataDaTarefa.getText().toString());
                stmt.executeInsert();
                finish();
                Log.i(null, "Registros inseridos com sucesso!");
            } catch (Exception e) {
                Log.i(null, "Erro ao inserir no Banco de Dados!");
            }
        }
    }


}