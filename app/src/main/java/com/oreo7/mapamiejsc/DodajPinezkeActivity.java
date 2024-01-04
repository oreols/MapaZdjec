package com.oreo7.mapamiejsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DodajPinezkeActivity extends AppCompatActivity {
    private Button pinezkabutton;
    private ListView listaZdjec;
    private List<String> itemy;
    private AparatActivity aparatActivity;


    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.dodajpinezke);

        pinezkabutton = findViewById(R.id.zapiszpinezke);
        //listaZdjec = findViewById(R.id.listazdjec);

        //List<String> itemy = (List<String>) getIntent().getSerializableExtra("items");
        //CustomImageAdapter adapter = new CustomImageAdapter(this, R.layout.item_row, itemy);
        //listaZdjec.setAdapter(adapter);

        pinezkabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPinezkeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        //pinezkabutton = findViewById(R.id.zapiszpinezke);
        //pinezkabutton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Intent intent = new Intent(DodajPinezkeActivity.this, MapActivity.class);
                //startActivity(intent);
                //setContentView(R.layout.activity_map);
            //}

        //});
    }
}

