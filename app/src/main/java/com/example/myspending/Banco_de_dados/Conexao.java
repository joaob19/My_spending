package com.example.myspending.Banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {
public static final String nome="My spending database";
public static  final int versao=1;

public Conexao(Context context){
super(context,"My spending database",null,1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gastos(id integer primary key,nome varchar(50),valor float,mes integer,data varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
