package com.example.myspending.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myspending.Main.MainActivity;
import com.example.myspending.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.Vermelho));
            getWindow().setStatusBarColor(getResources().getColor(R.color.Vermelho));
        }

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Mostra a tela de abertura até o tempo estipulado acabar e em segudida,
                //abre a tela inicial do app
                Intent StartIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(StartIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

}
