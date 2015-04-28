package com.notes.notes.Database_music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste on 08/04/15.
 */
public class MusicDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MusicDatabaseHandler dbHelper;
    private String[] allColumns = { MusicDatabaseHandler.COLUMN_ID, MusicDatabaseHandler.COLUMN_TITLE, MusicDatabaseHandler.COLUMN_COMPOSER, MusicDatabaseHandler.COLUMN_INTERPRETER};

    public MusicDataSource(Context context) {
        dbHelper = new MusicDatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public MusicIdea createMusicIdea(String title, String composer,String interpreter) {
        ContentValues values = new ContentValues();

        values.put(MusicDatabaseHandler.COLUMN_TITLE, title);

        values.put(MusicDatabaseHandler.COLUMN_COMPOSER, composer);

        values.put(MusicDatabaseHandler.COLUMN_INTERPRETER, interpreter);

        long insertId = database.insert(MusicDatabaseHandler.TABLE_MUSICS, null,
                values);
        Cursor cursor = database.query(MusicDatabaseHandler.TABLE_MUSICS,
                allColumns, MusicDatabaseHandler.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        MusicIdea newMusicIdea = cursorToMusicIdea(cursor);
        cursor.close();
        return newMusicIdea;
    }

    public void deleteMusicIdea(MusicIdea musicIdea) {
        long id = musicIdea.getId();
        System.out.println("MusicIdea deleted with id: " + id);
        database.delete(MusicDatabaseHandler.TABLE_MUSICS, MusicDatabaseHandler.COLUMN_ID
                + " = " + id, null);
    }

    public List<MusicIdea> getAllMusicIdeas() {
        List<MusicIdea> musicIdeas = new ArrayList<MusicIdea>();

        Cursor cursor = database.query(MusicDatabaseHandler.TABLE_MUSICS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MusicIdea musicIdea = cursorToMusicIdea(cursor);
            musicIdeas.add(musicIdea);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return musicIdeas;
    }


    private MusicIdea cursorToMusicIdea(Cursor cursor) {
        MusicIdea musicIdea = new MusicIdea();
        musicIdea.setId(cursor.getLong(0));
        musicIdea.setTitle(cursor.getString(1));
        musicIdea.setComposer(cursor.getString(2));
        musicIdea.setInterpreter(cursor.getString(3));
        return musicIdea;
    }
}
