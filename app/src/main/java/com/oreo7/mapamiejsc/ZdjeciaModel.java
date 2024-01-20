package com.oreo7.mapamiejsc;

import java.util.ArrayList;

public class ZdjeciaModel {
    private int id;
    private String sciezkaDoZdjecia;

    // Konstruktory, gettery, settery itp.

    public ZdjeciaModel(int id, String sciezkaDoZdjecia) {
        this.id = id;
        this.sciezkaDoZdjecia = sciezkaDoZdjecia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSciezkaDoZdjecia() {
        return sciezkaDoZdjecia;
    }

    public void setSciezkaDoZdjecia(String sciezkaDoZdjecia) {
        this.sciezkaDoZdjecia = sciezkaDoZdjecia;
    }
}
}
