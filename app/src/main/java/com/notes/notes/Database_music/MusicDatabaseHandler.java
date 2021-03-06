package com.notes.notes.Database_music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jean-Baptiste on 26/03/15.
 */

public class MusicDatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_MUSICS = "musics";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMPOSER = "composer";
    public static final String COLUMN_INTERPRETER = "interpreter";


    private static final String DATABASE_NAME = "musics.db";
    private static final int DATABASE_VERSION = 1;


    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MUSICS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text not null, " + COLUMN_COMPOSER
            + " text not null, " + COLUMN_INTERPRETER
            + " text not null);";

    public MusicDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MusicDatabaseHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSICS);
        onCreate(db);
    }

}
