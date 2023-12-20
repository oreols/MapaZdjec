package com.oreo7.mapamiejsc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;


public class KategorieFragment extends Fragment {
    private Button button;
    private TextView tekstNazwa;
    private TextView tekstOpis;
    private ListView listView;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategorie, container, false);

        button = view.findViewById(R.id.kategorieButton);
        Context context = this.getActivity();
        DBHelper dbHelper = new DBHelper(context);

        List<KategoriaModel> everyone = dbHelper.wyswietlWszystkie();
        Toast toast = Toast.makeText(context, everyone.toString(), Toast.LENGTH_SHORT);





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
}
