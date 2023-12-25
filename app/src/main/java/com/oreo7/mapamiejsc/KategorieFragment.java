package com.oreo7.mapamiejsc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class KategorieFragment extends Fragment {
    private Button button;
    private TextView tekstNazwa;
    private TextView tekstOpis;
    private ListView listView;
    private Context context;
    ArrayList<String> id, nazwa, opis;
    private DBHelper dbHelper;
    CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategorie, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.kategorieButton);
        context = this.getActivity().getBaseContext();
        dbHelper = new DBHelper(context);



        id = new ArrayList<>();
        nazwa = new ArrayList<>();
        opis = new ArrayList<>();

        displayData();

        customAdapter = new CustomAdapter(context, id, nazwa, opis);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDodajKategoria();
            }
        });

        return view;
    }
        public void openDodajKategoria(){
            Intent intent = new Intent(getActivity(), StworzKategorieFragment.class);
            startActivity(intent);

    }

    public void displayData(){
        Cursor cursor = dbHelper.readAllData();

            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                nazwa.add(cursor.getString(1));
                opis.add(cursor.getString(2));
            }
        }
    }



