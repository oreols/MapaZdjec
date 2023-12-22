package com.oreo7.mapamiejsc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "KATEGORIE";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAZWA = "nazwa";
    public static final String OPIS = "opis";

    // Database Information
    static final String DB_NAME = "OREO7_KATEGORIE.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAZWA + " TEXT NOT NULL, " + OPIS + " TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean dodaj(KategoriaModel kategoriaModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAZWA, kategoriaModel.getName());
        cv.put(OPIS, kategoriaModel.getOpis());

        long insert = db.insert(TABLE_NAME, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean onDelete(KategoriaModel kategoriaModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " = " + kategoriaModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

    public List<KategoriaModel> wyswietlWszystkie(){
        List<KategoriaModel> zwrocListe = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
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

}
