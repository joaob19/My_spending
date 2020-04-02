package com.example.myspending.Mes_atual;

public class Categoria {

    private String nome;
    private float total_da_categoria=0;

    public Categoria(String nome,float total_da_categoria){
        this.nome=nome;
        this.total_da_categoria=total_da_categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTotal_da_categoria() {
        return total_da_categoria;
    }

    public void setTotal_da_categoria(float total_da_categoria) {
        this.total_da_categoria = total_da_categoria;
    }
}
