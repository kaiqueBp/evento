package com.example.myee.model;

import androidx.annotation.NonNull;

public class Event {
    private Integer id;
    private String nome;
    private String categoria;
    private String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return nome+"     "+data+"     "+categoria;
    }
}
