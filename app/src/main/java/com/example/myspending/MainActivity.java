package com.example.myspending;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myspending.Banco_de_dados.GastosDAO;
import com.example.myspending.Este_mes.MesAtual;
import com.example.myspending.Historico.Historico;
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
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        verificarAno();
        verificaMes();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MesAtual());
        fragments.add(new Historico());
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Esse mês");
        tabLayout.getTabAt(1).setText("Histórico");
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
