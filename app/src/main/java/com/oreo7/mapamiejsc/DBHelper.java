package com.oreo7.mapamiejsc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "KATEGORIE";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAZWA = "nazwa";
    public static final String OPIS = "opis";
    public static final String TABLE_NAME_LOCATIONS = "LOCATIONS";
    public static final String _ID_LOC = "_id_loc";
    public static final String LOCATION_NAME = "location_name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    // Database Information
    static final String DB_NAME = "OREO7_KATEGORIE.DB";

    // database version
    static final int DB_VERSION = 4;

    // Creating table query


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "onCreate called");
        String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAZWA + " TEXT NOT NULL, " + OPIS + " TEXT);";
        db.execSQL(CREATE_TABLE);
        String CREATE_TABLE_LOCATIONS = "create table " + TABLE_NAME_LOCATIONS + "("
                + _ID_LOC + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOCATION_NAME + " TEXT NOT NULL, "
                + LATITUDE + " REAL, "
                + LONGITUDE + " REAL);";
        db.execSQL(CREATE_TABLE_LOCATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHelper", "onUpgrade called");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOCATIONS);
        onCreate(db);
    }

    public boolean dodaj(KategoriaModel kategoriaModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAZWA, kategoriaModel.getName());
        cv.put(OPIS, kategoriaModel.getOpis());

        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean onDelete(KategoriaModel kategoriaModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " = " + kategoriaModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }

    public List<KategoriaModel> wyswietlWszystkie() {
        List<KategoriaModel> zwrocListe = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int kategoriaId = cursor.getInt(0);
                String kategoriaNazwa = cursor.getString(1);
                String kategoriaOpis = cursor.getString(2);

                KategoriaModel kategoriaModel = new KategoriaModel(kategoriaId, kategoriaNazwa, kategoriaOpis);
                zwrocListe.add(kategoriaModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return zwrocListe;
    }

    public boolean dodajLocation(LocationModel locationModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LOCATION_NAME, locationModel.getNazwa());
        cv.put(LATITUDE, locationModel.getLatitude());
        cv.put(LONGITUDE, locationModel.getLongitude());

        Log.d("DBHelper", "Attempting to insert location into the database...");

        long insert = db.insert(TABLE_NAME_LOCATIONS, null, cv);

        if (insert == -1) {
            Log.e("DBHelper", "Error inserting location into the database");
            return false;
        } else {
            Log.d("DBHelper", "Location inserted successfully");
            return true;
        }
    }

    public void wyswietlWszystkieLokacje() {
        String queryString = "SELECT * FROM " + TABLE_NAME_LOCATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        AparatActivity aparatActivity = new AparatActivity();
        if (cursor.moveToFirst()) {
            do {
                long LokalizacjaId = cursor.getInt(0);
                String nazwa = cursor.getString(1);
                double latitude = cursor.getDouble(2);
                double longitude = cursor.getDouble(3);

                //MapActivity.LokalizacjaId1 = LokalizacjaId;
                //MapActivity.nazwa1 = nazwa;
                //MapActivity.latitude1 = latitude;
                //MapActivity.longitude1 = longitude;

                MapActivity.latitudes.add(latitude);
                MapActivity.longitudes.add(longitude);
                MapActivity.rozmiar += 1;

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }


}


