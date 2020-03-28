package com.example.myspending.Este_mes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myspending.Gasto;
import com.example.myspending.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GastoAdapter extends ArrayAdapter {

private final Context context;
private final ArrayList<Gasto> gastos;

public GastoAdapter(Context context, ArrayList<Gasto> gastos){
    super(context, R.layout.layout_gasto, gastos);
    this.context=context;
    this.gastos = gastos;
}

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_gasto,parent,false);
        TextView nome=(TextView)view.findViewById(R.id.txt_Nome_conta),
                preco=(TextView)view.findViewById(R.id.txt_Preco_conta),
                txtData=(TextView)view.findViewById(R.id.txtDataConta);

        DecimalFormat df = new DecimalFormat("0.00");
        nome.setText(gastos.get(position).getNome());
        preco.setText("R$ "+df.format(gastos.get(position).getValor()));
        txtData.setText(gastos.get(position).getData());
        return view;
    }
}
