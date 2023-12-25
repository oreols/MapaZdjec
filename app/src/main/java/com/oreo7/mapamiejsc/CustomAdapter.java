package com.oreo7.mapamiejsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, nazwa, opis;
    CustomAdapter(Context context, ArrayList id, ArrayList nazwa, ArrayList opis){
        this.context = context;
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nazwa_txt.setText(String.valueOf(nazwa.get(position)));
        holder.opis_txt.setText(String.valueOf(opis.get(position)));


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa_txt, opis_txt;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            nazwa_txt = itemView.findViewById(R.id.nazwa);
            opis_txt = itemView.findViewById(R.id.opis);
        }

    }
}
