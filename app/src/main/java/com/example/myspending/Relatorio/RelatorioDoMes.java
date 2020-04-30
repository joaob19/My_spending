package com.example.myspending.Relatorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myspending.Adapters.CategoriaAdapter;
import com.example.myspending.Banco_de_dados.Ativo;
import com.example.myspending.Banco_de_dados.AtivosDAO;
import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Mes_atual.Categoria;
import com.example.myspending.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RelatorioDoMes extends Fragment {

    TextView txtTotalGastos,txtTotalAtivos,txtSaldoatual;
    float totalGastos=0,totalAtivos=0,saldoAtual=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relatorio_do_mes, container, false);

        txtTotalAtivos = (TextView)view.findViewById(R.id.txtRelatorio1);
        txtTotalGastos = (TextView)view.findViewById(R.id.txtRelatorio2);
        txtSaldoatual = (TextView)view.findViewById(R.id.txtRelatorio3);

        somarAtivos();
        somarGastos();
        calcularSaldo();

        return view;
    }

    public void somarGastos(){
        GastosDAO gastosDAO = new GastosDAO(getActivity());
        DecimalFormat df = new DecimalFormat("0.00");

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("com.example.myspending", Context.MODE_PRIVATE);
        int mesAtual = sharedPreferences.getInt("mes",0);
        if(mesAtual!=0){
            ArrayList<Gasto> gastos= new ArrayList<Gasto>(gastosDAO.obterContas());
            for(int i=0;i<gastos.size();i++){
                if(gastos.get(i).getMes()==mesAtual){
                    totalGastos+=gastos.get(i).getValor();
                }
            }
            txtTotalGastos.setText("Total de gastos = R$ "+df.format(totalGastos));
        }
    }

    public void somarAtivos(){
        AtivosDAO ativosDAO = new AtivosDAO(getActivity());
        DecimalFormat df = new DecimalFormat("0.00");

            ArrayList<Ativo> ativos= new ArrayList<Ativo>(ativosDAO.obterAtivos());
            if(ativos.size()>0){
                for(int i=0;i<ativos.size();i++){
                    totalAtivos+=ativos.get(i).getValor();
                }
            }
            txtTotalAtivos.setText("Total de ativos = R$ "+df.format(totalAtivos));
    }

    public void calcularSaldo(){
        saldoAtual = (totalAtivos-totalGastos);
        DecimalFormat df = new DecimalFormat("0.00");
        txtSaldoatual.setText("Saldo atual = R$ "+df.format(saldoAtual));
    }

    @Override
    public void onResume() {
        super.onResume();
        totalGastos=0;
        totalAtivos=0;
        saldoAtual=0;
        somarAtivos();
        somarGastos();
        calcularSaldo();
    }

}
