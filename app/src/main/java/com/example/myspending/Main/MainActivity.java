package com.example.myspending.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myspending.Ativos.MeusAtivos;
import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Mes_atual.MesAtual;
import com.example.myspending.Historico.Historico;
import com.example.myspending.R;
import com.example.myspending.Relatorio.RelatorioDoMes;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
ViewPager viewPager;
TabLayout tabLayout;
PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.options);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        verificarAno();
        verificaMes();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MesAtual());
        fragments.add(new MeusAtivos());
        fragments.add(new RelatorioDoMes());
        fragments.add(new Historico());
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Meus gastos");
        tabLayout.getTabAt(1).setText("Meus ativos");
        tabLayout.getTabAt(2).setText("Relatório do mês");
        tabLayout.getTabAt(3).setText("Histórico");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Ajuda:
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Ajuda")
                        .setMessage(R.string.Ajuda)
                        .setPositiveButton("ok",null);
                adb.show();
                break;
        }

        return false;
    }

    public void verificaMes(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myspending",Context.MODE_PRIVATE);
        int mes = sharedPreferences.getInt("mes",0);
        DateFormat df = new SimpleDateFormat("MM");
        if(mes==0){
            sharedPreferences.edit().putInt("mes",Integer.parseInt(df.format(new Date()))).apply();

        }
        else {
            int mesatual= Integer.parseInt(df.format(new Date()));
            if(mes!=mesatual){
                sharedPreferences.edit().putInt("mes",Integer.parseInt(df.format(new Date()))).apply();
            }
        }
    }

    public void verificarAno(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myspending",Context.MODE_PRIVATE);
        int ano = sharedPreferences.getInt("ano",0);
        DateFormat df = new SimpleDateFormat("Y");
        if(ano==0){
            sharedPreferences.edit().putInt("ano",Integer.parseInt(df.format(new Date()))).apply();
        }
        else {
            int mesatual= Integer.parseInt(df.format(new Date()));
            if(ano!=mesatual){
                sharedPreferences.edit().putInt("ano",Integer.parseInt(df.format(new Date()))).apply();
                GastosDAO gastosDAO = new GastosDAO(this);
                gastosDAO.limparTudo();
            }
        }
    }
}
