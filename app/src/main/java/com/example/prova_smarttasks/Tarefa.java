package com.example.prova_smarttasks;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String data;

    public Tarefa(int id, String titulo, String descricao, String data) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getData() { return data; }
}
