package com.example.angelmartindominguez.flavourtester;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Testing extends AppCompatActivity {

    private ProgressBar pbCon, pbSt, pbTest, pbEnd;
    private TextView tvCon,tvSt,tvTest,tvEnding;
    private ImageView ivCon,ivSt,ivTest,ivEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        pbCon = findViewById(R.id.progressBarConectar);
        pbSt = findViewById(R.id.progressBarStart);
        pbTest = findViewById(R.id.progressBarPrueba);
        pbEnd = findViewById(R.id.progressBarEnd);
        tvCon = findViewById(R.id.tvConnect);
        tvSt = findViewById(R.id.tvStart);
        tvTest = findViewById(R.id.tvPrueba);
        tvEnding = findViewById(R.id.tvEnd);
        ivCon = findViewById(R.id.imageViewCon);
        ivSt = findViewById(R.id.imageViewStart);
        ivTest = findViewById(R.id.imageViewPrueba);
        ivEnd = findViewById(R.id.imageViewEnd);
        Timer tm = new Timer();
        tm.schedule(new Tarea1(this),1000);
        tm.schedule(new Tarea2(this),2000);
        tm.schedule(new Tarea3(this),5000);
        tm.schedule(new Tarea4(this),6000);
        tm.schedule(new Fin(),7000);
    }

    private class Tarea1 extends TimerTask{
        Testing pantalla = null;
        Tarea1(Testing screen){
            pantalla = screen;
        }
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pantalla.tvCon.setTextColor(getResources().getColor(R.color.colorText));
                    pantalla.tvCon.setTypeface(null, Typeface.NORMAL);
                    pantalla.tvCon.setText("Conectado");
                    pantalla.pbCon.setVisibility(View.INVISIBLE);
                    pantalla.ivCon.setVisibility(View.VISIBLE);
                    pantalla.tvSt.setTypeface(null,Typeface.BOLD);
                    pantalla.tvSt.setText("Iniciando prueba");
                    pantalla.tvSt.setTextColor(getResources().getColor(R.color.colorResaltado));
                }
            });
        }


    }

    private class Tarea2 extends TimerTask{
        Testing pantalla = null;
        Tarea2(Testing screen){
            pantalla = screen;
        }
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pantalla.tvSt.setTextColor(getResources().getColor(R.color.colorText));
                    pantalla.tvSt.setTypeface(null, Typeface.NORMAL);
                    pantalla.tvSt.setText("Prueba iniciada");
                    pantalla.pbSt.setVisibility(View.INVISIBLE);
                    pantalla.ivSt.setVisibility(View.VISIBLE);
                    pantalla.tvTest.setTypeface(null,Typeface.BOLD);
                    pantalla.tvTest.setText("Probando");
                    pantalla.tvTest.setTextColor(getResources().getColor(R.color.colorResaltado));
                }
            });
        }


    }

    private class Tarea3 extends TimerTask{
        Testing pantalla = null;
        Tarea3(Testing screen){
            pantalla = screen;
        }
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pantalla.tvTest.setTextColor(getResources().getColor(R.color.colorText));
                    pantalla.tvTest.setTypeface(null, Typeface.NORMAL);
                    pantalla.tvTest.setText("Probado");
                    pantalla.pbTest.setVisibility(View.INVISIBLE);
                    pantalla.ivTest.setVisibility(View.VISIBLE);
                    pantalla.tvEnding.setTypeface(null,Typeface.BOLD);
                    pantalla.tvEnding.setText("Finalizando");
                    pantalla.tvEnding.setTextColor(getResources().getColor(R.color.colorResaltado));
                }
            });
        }


    }

    private class Tarea4 extends TimerTask{
        Testing pantalla = null;
        Tarea4(Testing screen){
            pantalla = screen;
        }
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pantalla.tvEnding.setTextColor(getResources().getColor(R.color.colorText));
                    pantalla.tvEnding.setTypeface(null, Typeface.NORMAL);
                    pantalla.tvEnding.setText("Finalizado");
                    pantalla.pbEnd.setVisibility(View.INVISIBLE);
                    pantalla.ivEnd.setVisibility(View.VISIBLE);
                }
            });
        }


    }

    private class Fin extends TimerTask{

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }
    }


}
