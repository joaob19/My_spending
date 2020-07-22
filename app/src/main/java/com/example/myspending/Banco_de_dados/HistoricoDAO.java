package com.example.myspending.Banco_de_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class HistoricoDAO {

    private SQLiteDatabase banco;
    Conexao conexao;

    public HistoricoDAO(Context context){
        this.conexao = new Conexao(context);
        this.banco=conexao.getWritableDatabase();
    }

    public void atualizarHistorico(Historico historico){
        ContentValues values = new ContentValues();
        values.put("id", historico.getId());
        values.put("totalAtivos", historico.getTotalAtivos());
        values.put("totalGastos", historico.getTotalGastos());
        values.put("totalSaldo", historico.getTotalSaldo());
        values.put("mes", historico.getMes());
        String[] id = new String[]{Integer.toString(historico.getId())};
        SQLiteDatabase banco = this.banco;
        banco.update("historicos",values,"id=?",id);
    }

    public void excluirHistorico(Historico historico){
        String[] id = new String[]{Integer.toString(historico.getId())};
        banco.delete("historicos","id=?",id);
    }

    public long inserirHistorico(Historico historico){
        ContentValues values = new ContentValues();
        values.put("totalAtivos", historico.getTotalAtivos());
        values.put("totalGastos", historico.getTotalGastos());
        values.put("totalSaldo", historico.getTotalSaldo());
        values.put("mes", historico.getMes());
        return banco.insert("historicos",null,values);
    }

    public ArrayList obterHistoricos(){
        ArrayList historicos = new ArrayList();
        Cursor cursor = banco.query("historicos",new String[]{"id","mes","totalAtivos","totalGastos","totalsaldo"},null,null,null,null,null);

        while(cursor.moveToNext()){
            Historico historico = new Historico();
            historico.setId(cursor.getInt(0));
            historico.setMes(cursor.getInt(1));
            historico.setTotalAtivos(cursor.getFloat(2));
            historico.setTotalGastos(cursor.getFloat(3));
            historico.setTotalSaldo(cursor.getFloat(4));
            historicos.add(historico);
        }
        return historicos;
    }

    public Historico getHistorico(int mes){
        ArrayList<Historico> historicos = new ArrayList<Historico>(obterHistoricos());
        Historico historico = new Historico();

        for(int i=0;i<historicos.size();i++){
            if(historicos.get(i).getMes()==mes){
                historico.setId(historicos.get(i).getId());
                historico.setMes(historicos.get(i).getMes());
                historico.setTotalAtivos(historicos.get(i).getTotalAtivos());
                historico.setTotalGastos(historicos.get(i).getTotalGastos());
                historico.setTotalSaldo(historicos.get(i).getTotalSaldo());
            }
        }
        return historico;
    }

    public void limparTudo(){
        banco.execSQL("delete from historicos");
    }

}
