package com.example.myspending.Banco_de_dados;

public class Historico {

    int id,mes;
    float totalAtivos,totalGastos,totalSaldo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getTotalAtivos() {
        return totalAtivos;
    }

    public void setTotalAtivos(float totalAtivos) {
        this.totalAtivos = totalAtivos;
    }

    public float getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(float totalGastos) {
        this.totalGastos = totalGastos;
    }

    public float getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(float totalSaldo) {
        this.totalSaldo = totalSaldo;
    }
}
