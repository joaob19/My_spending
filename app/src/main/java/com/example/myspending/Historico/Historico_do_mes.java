package com.example.myspending.Historico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Mes_atual.GastoAdapter;
import com.example.myspending.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Historico_do_mes extends AppCompatActivity {
    ArrayList<Gasto> gastos = new ArrayList<>();
    ListView lista_gastos;
    GastoAdapter gasto_adapter;
    TextView txtTotalDoMes,txtMensagem;
    Toolbar toolbar;
    GastosDAO gastosDAO;
    int mesAtual=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_do_mes);

        toolbar = (Toolbar)findViewById(R.id.toolbar_historicoDoMes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        mesAtual = intent.getIntExtra("mes",0);
        verificarMes();

        gastosDAO = new GastosDAO(this);

        lista_gastos = (ListView)findViewById(R.id.lista_gastos_historico);
        carregarGastos();
        txtTotalDoMes = (TextView)findViewById(R.id.txtTotalDoMes_historico);
        txtMensagem = (TextView)findViewById(R.id.txt01);
        calcularGastos();
        verificarGastos();

    }

    public void verificarGastos(){
        if(gastos.size()==0){
            txtMensagem.setText("Você não registrou nenhum gasto neste mês");
        }
        else{
            txtMensagem.setText(" ");
        }
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

    public void carregarGastos(){
        if(mesAtual!=0){
            gastos = new ArrayList<>();
            ArrayList<Gasto> todosOSGastos = new ArrayList<Gasto>(gastosDAO.obterContas());
            if(todosOSGastos.size()>0){
                for(int i=0;i<todosOSGastos.size();i++){
                    if(todosOSGastos.get(i).getMes()==mesAtual){
                        gastos.add(todosOSGastos.get(i));
                    }
                }
                gasto_adapter = new GastoAdapter(this, gastos);
                lista_gastos.setAdapter(gasto_adapter);
            }
        }
    }

    public void verificarMes(){
        switch (mesAtual){
            case 1:
                getSupportActionBar().setTitle("Janeiro");
                break;
            case 2:
                getSupportActionBar().setTitle("Fevereiro");
                break;
            case 3:
                getSupportActionBar().setTitle("Março");
                break;
            case 4:
                getSupportActionBar().setTitle("Abril");
                break;
            case 5:
                getSupportActionBar().setTitle("Maio");
                break;
            case 6:
                getSupportActionBar().setTitle("Junho");
                break;
            case 7:
                getSupportActionBar().setTitle("Julho");
                break;
            case 8:
                getSupportActionBar().setTitle("Agosto");
                break;
            case 9:
                getSupportActionBar().setTitle("Setembro");
                break;
            case 10:
                getSupportActionBar().setTitle("Outubro");
                break;
            case 11:
                getSupportActionBar().setTitle("Novembro");
                break;
            case 12:
                getSupportActionBar().setTitle("Dezembro");
                break;
        }
    }

}
