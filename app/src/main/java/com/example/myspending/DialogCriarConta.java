package com.example.myspending;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogCriarConta extends DialogFragment {

    private EditText txtNome,txtValor;
    private CriarGastoListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_criar_gasto, null);

        txtNome = (EditText)view.findViewById(R.id.txtNome);
        txtValor = (EditText)view.findViewById(R.id.txtValor);

        builder.setView(view);
        builder.setTitle("Inserir despesa");
        builder.setPositiveButton("INSERIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ((txtNome.getText().toString().length() > 0) && (txtValor.getText().toString().length() > 0)) {
                    float valor = Float.parseFloat(txtValor.getText().toString());
                    DateFormat df = new SimpleDateFormat("MM");
                    if (valor > 0) {
                        Gasto gasto = new Gasto();
                        gasto.setNome(txtNome.getText().toString());
                        gasto.setValor(valor);
                        gasto.setMes(Integer.parseInt(df.format(new Date())));
                        DateFormat dateFormat = new SimpleDateFormat("d,MMM");
                        gasto.setData(dateFormat.format(new Date()));
                        listener.salvarGasto(gasto);
                    }
                } else {
                    Toast.makeText(getActivity(), "Você precisa preencher todos os campos para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CriarGastoListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "deve implementar DialogCriarConta");
        }
    }


    public interface CriarGastoListener {
        void salvarGasto(Gasto gasto);
    }

}
