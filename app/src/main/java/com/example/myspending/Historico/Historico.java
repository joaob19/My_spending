package com.example.myspending.Historico;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        lista_TodosOsMeses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),Historico_do_mes.class);

                switch (position){
                    case 0:
                        intent.putExtra("mes",1);
                        break;
                    case 1:
                        intent.putExtra("mes",2);
                        break;
                    case 2:
                        intent.putExtra("mes",3);
                        break;
                    case 3:
                        intent.putExtra("mes",4);
                        break;
                    case 4:
                        intent.putExtra("mes",5);
                        break;
                    case 5:
                        intent.putExtra("mes",6);
                        break;
                    case 6:
                        intent.putExtra("mes",7);
                        break;
                    case 7:
                        intent.putExtra("mes",8);
                        break;
                    case 8:
                        intent.putExtra("mes",9);
                        break;
                    case 9:
                        intent.putExtra("mes",10);
                        break;
                    case 10:
                        intent.putExtra("mes",11);
                        break;
                    case 11:
                        intent.putExtra("mes",12);
                        break;
                }
                startActivity(intent);
            }
        });

    return view;
    }

}
