package com.example.myspending.Historico;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myspending.R;

import java.util.ArrayList;
import java.util.List;

public class Historico extends Fragment {

ListView lista_TodosOsMeses;
ArrayAdapter<CharSequence> adapter_meses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_historico, container, false);

        lista_TodosOsMeses = (ListView)view.findViewById(R.id.lista_todosOsMeses);
        adapter_meses = ArrayAdapter.createFromResource(getActivity(),R.array.array_meses,android.R.layout.simple_list_item_1);
        lista_TodosOsMeses.setAdapter(adapter_meses);

    return view;
    }

}
