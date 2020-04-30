package com.example.myspending.Ativos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myspending.Adapters.AtivosAdapter;
import com.example.myspending.Banco_de_dados.Ativo;
import com.example.myspending.Banco_de_dados.AtivosDAO;
import com.example.myspending.Dialogs.DialogCriarAtivo;
import com.example.myspending.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MeusAtivos extends Fragment implements DialogCriarAtivo.CriarAtivoListener {

    TextView txttalAtivos,txtAviso;
    ImageButton btnAdd;
    ListView listaAtivos;
    ArrayList<Ativo> arrayAtivos = new ArrayList<>();
    AtivosAdapter ativosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meus_ativos, container, false);

        txttalAtivos = (TextView)view.findViewById(R.id.txtTotalAtivos);
        txtAviso = (TextView)view.findViewById(R.id.txtAvisoAtivos);
        listaAtivos = (ListView)view.findViewById(R.id.listview_ativos);
        carregarAtivos();

        listaAtivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                        .setTitle("Excluir")
                        .setMessage("Deseja mesmo excluir esse ativo?")
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AtivosDAO ativosDAO = new AtivosDAO(getActivity());
                                ativosDAO.excluirAtivo(arrayAtivos.get(position));
                                carregarAtivos();
                            }
                        });
                adb.show();
            }
        });

        btnAdd = (ImageButton)view.findViewById(R.id.btnAddAtivo);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCriarAtivo dialog = new DialogCriarAtivo();
                dialog.setTargetFragment(MeusAtivos.this,1);
                dialog.show(getActivity().getSupportFragmentManager(),"Criar ativo");
            }
        });

        return view;
    }

    public void carregarAtivos(){
        AtivosDAO ativosDAO = new AtivosDAO(getActivity());
        arrayAtivos = new ArrayList<Ativo>(ativosDAO.obterAtivos());
        ativosAdapter = new AtivosAdapter(getActivity(),arrayAtivos);
        listaAtivos.setAdapter(ativosAdapter);
        verificarAtivos();
        somarAtivos();
    }

    public void verificarAtivos(){
        if (arrayAtivos.size()>0){
            txtAviso.setText(" ");
        }
        else{
            txtAviso.setText("Você ainda não pussi nenhum ativo. Para adicionar um ativo clique no botão + no canto inferior direito");
        }
    }

    public void somarAtivos(){
        if(arrayAtivos.size()>0){
            float total=0;
            for (int i=0;i<arrayAtivos.size();i++){
                total+=arrayAtivos.get(i).getValor();
            }
            DecimalFormat df = new DecimalFormat("0.00");
            txttalAtivos.setText("Total de ativos = R$ "+df.format(total));
        }
        else{
            txttalAtivos.setText("Total de ativos = R$ 0.00");

        }
    }

    @Override
    public void salvarAtivo(Ativo ativo) {
        AtivosDAO ativosDAO = new AtivosDAO(getActivity());
        ativosDAO.inserirAtivo(ativo);
        carregarAtivos();
    }

}
