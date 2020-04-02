package com.example.myspending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myspending.Banco_de_dados.Gasto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CategoriaAdapter extends ArrayAdapter {

private final Context context;
private final ArrayList<Categoria> categorias;

public CategoriaAdapter(Context context, ArrayList<Categoria> categorias){
    super(context, R.layout.layout_categoria, categorias);
    this.context=context;
    this.categorias = categorias;
}

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_categoria,parent,false);
        TextView nome=(TextView)view.findViewById(R.id.txt_nome_categoria),
                total=(TextView)view.findViewById(R.id.txt_total_categoria);
        ImageView icone = (ImageView)view.findViewById(R.id.ic_categoria);

        DecimalFormat df = new DecimalFormat("0.00");
        nome.setText(categorias.get(position).getNome());
        total.setText("R$ "+df.format(categorias.get(position).getTotal_da_categoria()));
        switch (categorias.get(position).getNome()){
            case "Cartão de crédito":
                icone.setImageResource(R.drawable.ic_credito);
                break;
            case "Cartão de débito":
                icone.setImageResource(R.drawable.ic_credito);
                break;
            case "Combustível":
                icone.setImageResource(R.drawable.ic_combustivel);
                break;
            case "Educação":
                icone.setImageResource(R.drawable.ic_educacao);
                break;
            case "Gastos domésticos":
                icone.setImageResource(R.drawable.ic_gastos_domesticos);
                break;
            case "Gastos gerais":
                icone.setImageResource(R.drawable.ic_gastos_gerais);
                break;
            case "Mercado":
                icone.setImageResource(R.drawable.ic_mercado);
                break;
            case "Telefonia":
                icone.setImageResource(R.drawable.ic_telefonia);
                break;
            case "Transporte público":
                icone.setImageResource(R.drawable.ic_transporte_publico);
                break;
            case "Transporte privado":
                icone.setImageResource(R.drawable.ic_transporte_privado);
                break;
            case "Vestuário":
                icone.setImageResource(R.drawable.ic_vestuario);
                break;
            case "Total do mês":
                icone.setImageResource(R.drawable.ic_total);
                break;
        }
        return view;
    }
}
