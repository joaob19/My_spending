package com.example.myspending.Mes_atual;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Gastos_da_categoria.GastosDaCategoria;
import com.example.myspending.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MesAtual extends Fragment implements DialogCriarConta.CriarGastoListener {
ListView lista_gastos;
FloatingActionButton btnAddConta;
ArrayList<Categoria> categorias = new ArrayList<>();
CategoriaAdapter categoria_adapter;
GastosDAO gastosDAO;
float total_credito=0,total_debito=0,total_combustivel=0,total_educacao=0,total_domesticos=0,totalgerais=0,total_mercado=0,
            total_telefonia=0,total_privado=0,total_publico=0,total_vestuario=0,total_do_mes=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mes_atual, container, false);

        gastosDAO = new GastosDAO(getActivity());

        lista_gastos = (ListView)view.findViewById(R.id.listView_MesAtual);
        lista_gastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=11){
                    Intent intent = new Intent(getActivity(), GastosDaCategoria.class);
                    switch (position){
                        case 0:
                            intent.putExtra("categoria","Cartão de crédito");
                            break;
                        case 1:
                            intent.putExtra("categoria","Cartão de débito");
                            break;
                        case 2:
                            intent.putExtra("categoria","Combustível");
                            break;
                        case 3:
                            intent.putExtra("categoria","Educação");
                            break;
                        case 4:
                            intent.putExtra("categoria","Gastos domésticos");
                            break;
                        case 5:
                            intent.putExtra("categoria","Gastos gerais");
                            break;
                        case 6:
                            intent.putExtra("categoria","Mercado");
                            break;
                        case 7:
                            intent.putExtra("categoria","Telefonia");
                            break;
                        case 8:
                            intent.putExtra("categoria","Transporte público");
                            break;
                        case 9:
                            intent.putExtra("categoria","Transporte privado");
                            break;
                        case 10:
                            intent.putExtra("categoria","Vestuário");
                            break;
                    }
                    startActivity(intent);
                }
            }
        });
        iniciarCategorias();
        somarGastos();
        btnAddConta = (FloatingActionButton)view.findViewById(R.id.btnAddGasto);
        btnAddConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCriarConta dialog = new DialogCriarConta();
                dialog.setTargetFragment(MesAtual.this,1);
                dialog.show(getActivity().getSupportFragmentManager(),"Criar gasto");
            }
        });
        return view;
    }



    @Override
    public void salvarGasto(Gasto gasto) {
        gastosDAO.inserirConta(gasto);
        somarGastos();
    }

    public void iniciarCategorias(){
        Categoria cartao_credito= new Categoria("Cartão de crédito",total_credito);
        Categoria cartao_débito= new Categoria("Cartão de débito",total_debito);
        Categoria combustivel= new Categoria("Combustível",total_combustivel);
        Categoria educacao= new Categoria("Educação",total_educacao);
        Categoria gastos_domesticos= new Categoria("Gastos domésticos",total_domesticos);
        Categoria gastos_gerais= new Categoria("Gastos gerais",totalgerais);
        Categoria mercado= new Categoria("Mercado",total_mercado);
        Categoria telefonia= new Categoria("Telefonia",total_telefonia);
        Categoria transporte_publico= new Categoria("Transporte público",total_publico);
        Categoria transporte_privado= new Categoria("Transporte privado",total_privado);
        Categoria vestuario= new Categoria("Vestuário",total_vestuario);
        Categoria total= new Categoria("Total do mês",total_do_mes);
        categorias.add(cartao_credito);
        categorias.add(cartao_débito);
        categorias.add(combustivel);
        categorias.add(educacao);
        categorias.add(gastos_domesticos);
        categorias.add(gastos_gerais);
        categorias.add(mercado);
        categorias.add(telefonia);
        categorias.add(transporte_publico);
        categorias.add(transporte_privado);
        categorias.add(vestuario);
        categorias.add(total);
        categoria_adapter = new CategoriaAdapter(getActivity(),categorias);
        lista_gastos.setAdapter(categoria_adapter);
    }

    public void somarGastos(){
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("com.example.myspending", Context.MODE_PRIVATE);
        int mesAtual = sharedPreferences.getInt("mes",0);
        if(mesAtual!=0){
            ArrayList<Gasto> gastos= new ArrayList<Gasto>(gastosDAO.obterContas());
            zerarTotais();
            for(int i=0;i<gastos.size();i++){
                if(gastos.get(i).getMes()==mesAtual){
                    switch (gastos.get(i).getCategoria()){
                        case "Cartão de crédito":
                            total_credito+=(gastos.get(i).getValor());
                            break;
                        case "Cartão de débito":
                            total_debito+=(gastos.get(i).getValor());
                            break;
                        case "Combustível":
                            total_combustivel+=(gastos.get(i).getValor());
                            break;
                        case "Educação":
                            total_educacao+=(gastos.get(i).getValor());
                            break;
                        case "Gastos domésticos":
                            total_domesticos+=(gastos.get(i).getValor());
                            break;
                        case "Gastos gerais":
                            totalgerais+=(gastos.get(i).getValor());
                            break;
                        case "Mercado":
                            total_mercado+=(gastos.get(i).getValor());
                            break;
                        case "Telefonia":
                            total_telefonia+=(gastos.get(i).getValor());
                            break;
                        case "Transporte público":
                            total_publico+=(gastos.get(i).getValor());
                            break;
                        case "Transporte privado":
                            total_privado+=(gastos.get(i).getValor());
                            break;
                        case "Vestuário":
                            total_vestuario+=(gastos.get(i).getValor());
                            break;
                    }
                    total_do_mes+=gastos.get(i).getValor();
                }
            }
            categorias.set(0,new Categoria("Cartão de crédito",total_credito));
            categorias.set(1,new Categoria("Cartão de débito",total_debito));
            categorias.set(2,new Categoria("Combustível",total_combustivel));
            categorias.set(3,new Categoria("Educação",total_educacao));
            categorias.set(4,new Categoria("Gastos domésticos",total_domesticos));
            categorias.set(5,new Categoria("Gastos gerais",totalgerais));
            categorias.set(6,new Categoria("Mercado",total_mercado));
            categorias.set(7,new Categoria("Telefonia",total_telefonia));
            categorias.set(8,new Categoria("Transporte público",total_publico));
            categorias.set(9,new Categoria("Transporte privado",total_privado));
            categorias.set(10,new Categoria("Vestuário",total_vestuario));
            categorias.set(11,new Categoria("Total do mês",total_do_mes));
        }
    }

    @Override
    public void onResume() {
        somarGastos();
        super.onResume();
    }


    public void zerarTotais(){
        total_credito=0;
        total_debito=0;
        total_combustivel=0;
        total_educacao=0;
        total_domesticos=0;
        totalgerais=0;
        total_mercado=0;
        total_telefonia=0;
        total_privado=0;
        total_publico=0;
        total_vestuario=0;
        total_do_mes=0;
    }

}
