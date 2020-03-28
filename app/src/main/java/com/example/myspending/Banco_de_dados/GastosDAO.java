package com.example.myspending.Banco_de_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myspending.Gasto;

import java.util.ArrayList;

public class GastosDAO {

    private SQLiteDatabase banco;
    Conexao conexao;

    public GastosDAO(Context context){
        this.conexao = new Conexao(context);
        this.banco=conexao.getWritableDatabase();
    }

    public void atualizarConta(Gasto gasto){
        ContentValues values = new ContentValues();
        values.put("id", gasto.getId());
        values.put("nome", gasto.getNome());
        values.put("valor", gasto.getValor());
        values.put("mes", gasto.getMes());
        values.put("data", gasto.getData());
        String[] id = new String[]{Integer.toString(gasto.getId())};
        SQLiteDatabase banco = this.banco;
        banco.update("gastos",values,"id=?",id);
    }

    public void excluirConta(Gasto gasto){
        String[] id = new String[]{Integer.toString(gasto.getId())};
        SQLiteDatabase banco = this.banco;
        banco.delete("gastos","id=?",id);
    }

    public long inserirConta(Gasto gasto){
        ContentValues values = new ContentValues();
        values.put("nome", gasto.getNome());
        values.put("valor", gasto.getValor());
        values.put("mes", gasto.getMes());
        values.put("data", gasto.getData());
        return this.banco.insert("gastos",null,values);
    }

    public ArrayList obterContas(){
        ArrayList gastos = new ArrayList();
        Cursor cursor = banco.query("gastos",new String[]{"id","nome","valor","mes","data"},null,null,null,null,null);

        while(cursor.moveToNext()){
            Gasto gasto = new Gasto();
            gasto.setId(cursor.getInt(0));
            gasto.setNome(cursor.getString(1));
            gasto.setValor(cursor.getFloat(2));
            gasto.setMes(cursor.getInt(3));
            gasto.setData(cursor.getString(4));
            gastos.add(gasto);
        }

        return gastos;
    }

    public void limparTudo(){
        SQLiteDatabase banco = this.banco;
        banco.execSQL("delete from gastos");
    }

}
