package com.example.myspending;


import java.io.Serializable;

public class Gasto implements Serializable {
    private int id,mes;
    private String nome,data;
    private float valor;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }


    public void setValor(float valor) {
        this.valor = valor;
    }
}
