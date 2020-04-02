package com.example.myspending;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Mes_atual.GastoAdapter;

import java.util.ArrayList;

public class GastosDaCategoria extends AppCompatActivity {
    ArrayList<Gasto> gastos = new ArrayList<>();
    GastoAdapter gasto_adapter;
    GastosDAO gastosDAO;
    String categoriaAtual;
    ListView lista_gastos;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_da_categoria);

        toolbar = (Toolbar)findViewById(R.id.toolbar_gastos_da_categoria);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gastosDAO = new GastosDAO(this);
        lista_gastos = (ListView)findViewById(R.id.listView_gastos_categoria);

        Intent intent = getIntent();
        categoriaAtual = intent.getStringExtra("categoria");
        verificaMes();
        carregarGastos();

    }

    public void carregarGastos(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myspending", Context.MODE_PRIVATE);
        int mesAtual = sharedPreferences.getInt("mes",0);
        if(mesAtual!=0){
            gastos = new ArrayList<>();
            ArrayList<Gasto> todosOSGastos = new ArrayList<Gasto>(gastosDAO.obterContas());
            if(todosOSGastos.size()>0){
                for(int i=0;i<todosOSGastos.size();i++){
                    if((todosOSGastos.get(i).getMes()==mesAtual)&&(todosOSGastos.get(i).getCategoria().equalsIgnoreCase(categoriaAtual))){
                        gastos.add(todosOSGastos.get(i));
                    }
                }
                gasto_adapter = new GastoAdapter(this, gastos);
                lista_gastos.setAdapter(gasto_adapter);
            }
        }
    }

    public void verificaMes(){
        switch (categoriaAtual){
            case "Cartão de crédito":
                getSupportActionBar().setTitle("Cartão de crédito");
                break;
            case "Cartão de débito":
                getSupportActionBar().setTitle("Cartão de débito");
                break;
            case "Combustível":
                getSupportActionBar().setTitle("Combustível");
                break;
            case "Educação":
                getSupportActionBar().setTitle("Educação");
                break;
            case "Gastos domésticos":
                getSupportActionBar().setTitle("Gastos domésticos");
                break;
            case "Gastos gerais":
                getSupportActionBar().setTitle("Gastos gerais");
                break;
            case "Mercado":
                getSupportActionBar().setTitle("Mercado");
                break;
            case "Telefonia":
                getSupportActionBar().setTitle("Telefonia");
                break;
            case "Transporte público":
                getSupportActionBar().setTitle("Transporte público");
                break;
            case "Transporte privado":
                getSupportActionBar().setTitle("Transporte privado");
                break;
            case "Vestuário":
                getSupportActionBar().setTitle("Vestuário");
                break;
            case "Total do mês":
                getSupportActionBar().setTitle("Total do mês");
                break;
        }
    }



}
