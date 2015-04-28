package com.notes.notes.Database_film;

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
public class FilmsDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private FilmDatabaseHandler dbHelper;
    private String[] allColumns = { FilmDatabaseHandler.COLUMN_ID, FilmDatabaseHandler.COLUMN_TITLE, FilmDatabaseHandler.COLUMN_DIRECTOR, FilmDatabaseHandler.COLUMN_FILM, FilmDatabaseHandler.COLUMN_VIDEO};

    public FilmsDataSource(Context context) {
        dbHelper = new FilmDatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public FilmIdea createFilmIdea(String title, String director, int film, int video) {
        ContentValues values = new ContentValues();

        values.put(FilmDatabaseHandler.COLUMN_TITLE, title);

        values.put(FilmDatabaseHandler.COLUMN_DIRECTOR, director);

        values.put(FilmDatabaseHandler.COLUMN_FILM, film);

        values.put(FilmDatabaseHandler.COLUMN_VIDEO, video);

        long insertId = database.insert(FilmDatabaseHandler.TABLE_FILMS, null,
                values);
        Cursor cursor = database.query(FilmDatabaseHandler.TABLE_FILMS,
                allColumns, FilmDatabaseHandler.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        FilmIdea newFilmIdea = cursorToFilmIdea(cursor);
        cursor.close();
        return newFilmIdea;
    }

    public void deleteFilmIdea(FilmIdea filmIdea) {
        long id = filmIdea.getId();
        System.out.println("FilmIdea deleted with id: " + id);
        database.delete(FilmDatabaseHandler.TABLE_FILMS, FilmDatabaseHandler.COLUMN_ID
                + " = " + id, null);
    }

    public List<FilmIdea> getAllFilmIdeas() {
        List<FilmIdea> filmIdeas = new ArrayList<FilmIdea>();

        Cursor cursor = database.query(FilmDatabaseHandler.TABLE_FILMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FilmIdea filmIdea = cursorToFilmIdea(cursor);
            filmIdeas.add(filmIdea);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return filmIdeas;
    }


    private FilmIdea cursorToFilmIdea(Cursor cursor) {
        FilmIdea filmIdea = new FilmIdea();
        filmIdea.setId(cursor.getLong(0));
        filmIdea.setTitle(cursor.getString(1));
        filmIdea.setDirector(cursor.getString(2));
        filmIdea.setFilm(cursor.getInt(3));
        filmIdea.setVideo(cursor.getInt(4));
        return filmIdea;
    }
}
