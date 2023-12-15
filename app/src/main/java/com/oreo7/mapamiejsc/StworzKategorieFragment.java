package com.oreo7.mapamiejsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class StworzKategorieFragment extends AppCompatActivity {
    private ImageButton powrotbutton;
    private Button zapiszbutton;
    private NavigationView navigationView;
    private LayoutInflater inflater;
    private ViewGroup container;
    private EditText nazwa;
    private EditText opis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stworzkategorielayout);

        powrotbutton = (ImageButton) findViewById(R.id.powrotbutton);
        zapiszbutton = (Button) findViewById(R.id.zapisz);
        nazwa = (EditText) findViewById(R.id.nazwa);
        opis = (EditText) findViewById(R.id.opis);
        powrotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wroc();



            }
        });
        zapiszbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nazwaString = nazwa.getText().toString();
                String opisString = opis.getText().toString();
                KategoriaModel kategoriaModel = new KategoriaModel(0, nazwaString, opisString);
                DBHelper dbHelper = new DBHelper(StworzKategorieFragment.this);
                boolean success = dbHelper.dodaj(kategoriaModel);
            }
        });
    }
    public void wroc() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}

