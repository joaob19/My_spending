package com.example.myspending.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.myspending.Banco_de_dados.Ativo;
import com.example.myspending.R;

public class DialogCriarAtivo extends DialogFragment {

    private EditText txtNome,txtValor;
    private DialogCriarAtivo.CriarAtivoListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_criar_ativo,null);
        txtNome = (EditText)view.findViewById(R.id.txtNomeAtivo);
        txtValor = (EditText)view.findViewById(R.id.txtValorAtivo);

        builder.setView(view)
                .setTitle("Criar ativo")
                .setNegativeButton("Cancelar",null)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (txtNome.getText().toString().length()>0 && txtValor.getText().toString().length()>0){
                            float valor = Float.parseFloat(txtValor.getText().toString());
                            if(valor>0){
                                Ativo ativo = new Ativo();
                                ativo.setNome(txtNome.getText().toString());
                                ativo.setValor(Float.parseFloat(txtValor.getText().toString()));
                                listener.salvarAtivo(ativo);
                            }
                            else{
                                Toast.makeText(getActivity(), "Você deve utilizar valores maiores que R$ 0.00", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "Você deve preencher todos os campos para salvar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogCriarAtivo.CriarAtivoListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "deve implementar DialogCriarAtivo");
        }
    }


    public interface CriarAtivoListener {
        void salvarAtivo(Ativo ativo);
    }


}
