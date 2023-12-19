package com.oreo7.mapamiejsc;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}