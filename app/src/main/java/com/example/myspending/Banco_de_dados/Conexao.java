package com.example.myspending.Banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Conexao extends SQLiteOpenHelper {
public static final String nome="My spending database";
public static  final int versao=2;

public Conexao(Context context){
super(context,nome,null,versao);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists gastos(id integer primary key,nome varchar(50),valor float,mes integer,data varchar(20),categoria varchar(50))");
        db.execSQL("create table if not exists ativos(id integer primary key, nome varchar(50),valor float)");
        Log.d("SQLite","OnCreate chamado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if not exists ativos(id integer primary key, nome varchar(50),valor float)");
    }


}
