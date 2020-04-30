package com.example.myspending.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myspending.Banco_de_dados.Ativo;
import com.example.myspending.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AtivosAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Ativo> ativos;

    public AtivosAdapter(Context context, ArrayList<Ativo> ativos){
        super(context, R.layout.layout_ativo, ativos);
        this.ativos= new ArrayList<Ativo>(ativos);
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_ativo,null);

        TextView txtNome = (TextView)view.findViewById(R.id.txtLayoutativo1);
        TextView txtValor= (TextView)view.findViewById(R.id.txtLayoutativo2);

        txtNome.setText(ativos.get(position).getNome());
        DecimalFormat df = new DecimalFormat("0.00");
        txtValor.setText("R$ "+df.format(ativos.get(position).getValor()));

        return view;
    }
}
