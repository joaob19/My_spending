package com.example.myspending.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myspending.Banco_de_dados.Historico;
import com.example.myspending.Banco_de_dados.HistoricoDAO;
import com.example.myspending.R;

import java.text.DecimalFormat;

public class DialogHistorico extends DialogFragment {

    private TextView txtTotalAtivos,txtTotalGastos,txtTotalSaldo;
    private int mes=0;

    public DialogHistorico(int mes){
        this.mes=mes;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_historico, null);

        txtTotalAtivos = view.findViewById(R.id.txt_dialog_historico_1);
        txtTotalGastos = view.findViewById(R.id.txt_dialog_historico_2);
        txtTotalSaldo = view.findViewById(R.id.txt_dialog_historico_3);

        builder.setView(view);
        builder.setTitle("Relatório completo");
        builder.setPositiveButton("OK", null);

        HistoricoDAO historicoDAO = new HistoricoDAO(getActivity());
        Historico historico = historicoDAO.getHistorico(mes);

        DecimalFormat df = new DecimalFormat("0.00");
        if(mes!=0){
            txtTotalAtivos.setText("Total de ativos = R$ "+ df.format(historico.getTotalAtivos()));
            txtTotalGastos.setText("Total de gastos = R$ "+ df.format(historico.getTotalGastos()));
            txtTotalSaldo.setText("Saldo total = R$ "+ df.format(historico.getTotalSaldo()));
        }


        return builder.create();
    }
}
