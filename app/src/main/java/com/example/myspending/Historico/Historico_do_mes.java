package com.example.myspending.Historico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Banco_de_dados.Gasto;
import com.example.myspending.Adapters.GastoAdapter;
import com.example.myspending.Dialogs.DialogHistorico;
import com.example.myspending.R;

import java.util.ArrayList;

public class Historico_do_mes extends AppCompatActivity {
    ArrayList<Gasto> gastos = new ArrayList<>();
    ListView lista_gastos;
    GastoAdapter gasto_adapter;
    TextView txtTotalDoMes,txtMensagem;
    Toolbar toolbar;
    GastosDAO gastosDAO;
    int mes =0;
    Button btnMostrarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_do_mes);

        toolbar = (Toolbar)findViewById(R.id.toolbar_historicoDoMes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        mes = intent.getIntExtra("mes",0);
        verificarMes();

        gastosDAO = new GastosDAO(this);

        lista_gastos = (ListView)findViewById(R.id.lista_gastos_historico);
        carregarGastos();
        txtMensagem = (TextView)findViewById(R.id.txt01);
        verificarGastos();

        btnMostrarRegistro = findViewById(R.id.btnMostrarRegistro);
        btnMostrarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myspending", Context.MODE_PRIVATE);
                int mesAtual = sharedPreferences.getInt("mes",0);
                if(mes>=mesAtual){
                    Toast.makeText(Historico_do_mes.this, "Você só pode consultar relatórios de meses que já passaram.", Toast.LENGTH_SHORT).show();
                }
                else{
                    DialogHistorico dialog = new DialogHistorico(mes);
                    dialog.show(getSupportFragmentManager(),"Criar gasto");
                }
            }
        });

    }

    public void verificarGastos(){
        if(gastos.size()==0){
            txtMensagem.setText("Você não registrou nenhum gasto neste mês");
        }
        else{
            txtMensagem.setText(" ");
        }
    }

    public void carregarGastos(){
        if(mes !=0){
            gastos = new ArrayList<>();
            ArrayList<Gasto> todosOSGastos = new ArrayList<Gasto>(gastosDAO.obterContas());
            if(todosOSGastos.size()>0){
                for(int i=0;i<todosOSGastos.size();i++){
                    if(todosOSGastos.get(i).getMes()== mes){
                        gastos.add(todosOSGastos.get(i));
                    }
                }
                gasto_adapter = new GastoAdapter(this, gastos);
                lista_gastos.setAdapter(gasto_adapter);
            }
        }
    }

    public void verificarMes(){
        switch (mes){
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
