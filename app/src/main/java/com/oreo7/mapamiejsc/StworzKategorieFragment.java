package com.oreo7.mapamiejsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    private NavigationView navigationView;
    private LayoutInflater inflater;
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stworzkategorielayout);

        powrotbutton = (ImageButton) findViewById(R.id.powrotbutton);
        powrotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wroc();



            }


        });
    }
    public void wroc() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}

