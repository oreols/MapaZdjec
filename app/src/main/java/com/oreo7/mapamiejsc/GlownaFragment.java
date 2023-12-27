package com.oreo7.mapamiejsc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GlownaFragment extends Fragment {
    private Button dodajButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.glowna, container, false);
        Context context = this.getActivity().getBaseContext();
        dodajButton = view.findViewById(R.id.dodaj);
        dodajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AparatActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}

