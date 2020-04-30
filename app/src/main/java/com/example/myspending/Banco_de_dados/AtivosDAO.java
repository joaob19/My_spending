package com.example.myspending.Banco_de_dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AtivosDAO {

    private SQLiteDatabase banco;
    Conexao conexao;

    public AtivosDAO(Context context){
        this.conexao = new Conexao(context);
        this.banco=conexao.getWritableDatabase();
    }

    public void atualizarAtivo(Ativo ativo){
        ContentValues values = new ContentValues();
        values.put("id", ativo.getId());
        values.put("nome", ativo.getNome());
        values.put("valor", ativo.getValor());
        String[] id = new String[]{Integer.toString(ativo.getId())};
        SQLiteDatabase banco = this.banco;
        banco.update("ativos",values,"id=?",id);
    }

    public void excluirAtivo(Ativo ativo){
        String[] id = new String[]{Integer.toString(ativo.getId())};
        SQLiteDatabase banco = this.banco;
        banco.delete("ativos","id=?",id);
    }

    public long inserirAtivo(Ativo ativo){
        ContentValues values = new ContentValues();
        values.put("nome", ativo.getNome());
        values.put("valor", ativo.getValor());
        return this.banco.insert("ativos",null,values);
    }

    public ArrayList obterAtivos(){
        ArrayList ativos = new ArrayList();
        Cursor cursor = banco.query("ativos",new String[]{"id","nome","valor"},null,null,null,null,null);

        while(cursor.moveToNext()){
            Ativo ativo = new Ativo();
            ativo.setId(cursor.getInt(0));
            ativo.setNome(cursor.getString(1));
            ativo.setValor(cursor.getFloat(2));
            ativos.add(ativo);
        }

        return ativos;
    }


}
