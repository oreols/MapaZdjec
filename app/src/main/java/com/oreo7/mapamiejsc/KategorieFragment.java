package com.oreo7.mapamiejsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



public class KategorieFragment extends Fragment {
    private Button button;
    private TextView tekstNazwa;
    private TextView tekstOpis;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategorie, container, false);

        button = view.findViewById(R.id.kategorieButton);


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
