package com.notes.notes.Database_film;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */

public class FilmDatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_FILMS= "films";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_FILM = "film";
    public static final String COLUMN_VIDEO = "video";


    private static final String DATABASE_NAME = "films.db";
    private static final int DATABASE_VERSION = 1;


    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FILMS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text not null, " + COLUMN_DIRECTOR
            + " text not null, " + COLUMN_FILM
            + " integer, " + COLUMN_VIDEO
            + " integer);";

    public FilmDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FilmDatabaseHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        onCreate(db);
    }

}
