package com.example.myspending.Este_mes;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.myspending.DialogCriarConta;
import com.example.myspending.Gasto;
import com.example.myspending.GastoAdapter;
import com.example.myspending.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MesAtual extends Fragment implements DialogCriarConta.CriarGastoListener {
ListView lista_contas;
FloatingActionButton btnAddConta;
static ArrayList<Gasto> gastos = new ArrayList<>();
TextView txtTotalDoMes,txtMensagem;
static GastoAdapter conta_adapter;
GastosDAO gastosDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mes_atual, container, false);

        gastosDAO = new GastosDAO(getActivity());
        gastos = new ArrayList<Gasto>(gastosDAO.obterContas());

        txtTotalDoMes = (TextView)view.findViewById(R.id.txtTotalDoMes);
        txtMensagem = (TextView)view.findViewById(R.id.txt00);

        conta_adapter= new GastoAdapter(getActivity(), gastos);
        lista_contas = (ListView)view.findViewById(R.id.listView_MesAtual);
        lista_contas.setAdapter(conta_adapter);
        lista_contas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Excluir").setMessage("Deseja mesmo excluir?")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gastosDAO.excluirConta(gastos.get(position));
                                gastos.remove(position);
                                conta_adapter.notifyDataSetInvalidated();
                                calcularGastos();
                                verificarContas();
                            }
                        })
                        .setNegativeButton("NÃO", null);
                adb.show();
            }
        });

        btnAddConta = (FloatingActionButton)view.findViewById(R.id.btnAddGasto);
        btnAddConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCriarConta dialog = new DialogCriarConta();
                dialog.setTargetFragment(MesAtual.this,1);
                dialog.show(getActivity().getSupportFragmentManager(),"Criar gasto");
            }
        });

        verificarContas();
        calcularGastos();
        return view;
    }


    public void calcularGastos(){
        if((gastos !=null)&&(gastos.size()>0)){
            float total=0;
            DecimalFormat df = new DecimalFormat("0.00");
            for(int i = 0; i< gastos.size(); i++){
                total+= gastos.get(i).getValor();
            }
            txtTotalDoMes.setText("Gastos desse mês = R$ "+df.format(total));
        }
        else{
            txtTotalDoMes.setText(" ");
        }
    }

    public void verificarContas(){
        if(gastos.size()==0){
            txtMensagem.setText("Seus gastos aparecerão aqui. Para adicionar um gasto clique no botão no canto inferior direito.");
        }
        else{
            txtMensagem.setText(" ");
        }
    }

    @Override
    public void salvarGasto(Gasto gasto) {
        gastosDAO.inserirConta(gasto);
        gastos =new ArrayList<Gasto>(gastosDAO.obterContas());
        conta_adapter= new GastoAdapter(getActivity(), gastos);
        lista_contas.setAdapter(conta_adapter);
        calcularGastos();
        verificarContas();
    }
}
