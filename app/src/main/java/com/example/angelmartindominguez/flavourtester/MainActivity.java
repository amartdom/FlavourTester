package com.example.angelmartindominguez.flavourtester;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlador();
    }

    public void controlador(){

        Button entry =  findViewById(R.id.enter);

        //hago clic y se abre el 2
        entry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent menu = new Intent(getApplicationContext(), MenuLateral.class);
                startActivity(menu);
            }
        });
    }
}
