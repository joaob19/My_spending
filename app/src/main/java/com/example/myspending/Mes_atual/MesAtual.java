package com.example.myspending.Mes_atual;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Categoria;
import com.example.myspending.CategoriaAdapter;
import com.example.myspending.GastosDaCategoria;
import com.example.myspending.Historico.Historico_do_mes;
import com.example.myspending.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MesAtual extends Fragment implements DialogCriarConta.CriarGastoListener {
ListView lista_gastos;
FloatingActionButton btnAddConta;
ArrayList<Categoria> categorias = new ArrayList<>();
CategoriaAdapter categoria_adapter;
GastosDAO gastosDAO;
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
    }

    public void iniciarCategorias(){
        Categoria cartao_credito= new Categoria("Cartão de crédito");
        Categoria cartao_débito= new Categoria("Cartão de débito");
        Categoria combustivel= new Categoria("Combustível");
        Categoria educacao= new Categoria("Educação");
        Categoria gastos_domesticos= new Categoria("Gastos domésticos");
        Categoria gastos_gerais= new Categoria("Gastos gerais");
        Categoria mercado= new Categoria("Mercado");
        Categoria telefonia= new Categoria("Telefonia");
        Categoria transporte_publico= new Categoria("Transporte público");
        Categoria transporte_privado= new Categoria("Transporte privado");
        Categoria vestuario= new Categoria("Vestuário");
        Categoria total= new Categoria("Total do mês");
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


}
