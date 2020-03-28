package com.example.myspending.Historico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myspending.R;

public class Historico_do_mes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_do_mes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
