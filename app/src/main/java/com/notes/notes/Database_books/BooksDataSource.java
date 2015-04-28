package com.notes.notes.Database_books;

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
public class BooksDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private BookDatabaseHandler dbHelper;
    private String[] allColumns = { BookDatabaseHandler.COLUMN_ID, BookDatabaseHandler.COLUMN_TITLE, BookDatabaseHandler.COLUMN_AUTHOR, BookDatabaseHandler.COLUMN_REMARK, BookDatabaseHandler.COLUMN_PENSEBETE, BookDatabaseHandler.COLUMN_FORREADING, BookDatabaseHandler.COLUMN_FAVORITE};

    public BooksDataSource(Context context) {
        dbHelper = new BookDatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BookIdea createBookIdea(String title, String author,String remark,int penseBete, int forReading, int favorite) {
        ContentValues values = new ContentValues();

        values.put(BookDatabaseHandler.COLUMN_TITLE, title);

        values.put(BookDatabaseHandler.COLUMN_AUTHOR, author);

        values.put(BookDatabaseHandler.COLUMN_REMARK, remark);

        values.put(BookDatabaseHandler.COLUMN_PENSEBETE, penseBete);

        values.put(BookDatabaseHandler.COLUMN_FORREADING, forReading);

        values.put(BookDatabaseHandler.COLUMN_FAVORITE, favorite);

        long insertId = database.insert(BookDatabaseHandler.TABLE_BOOKS, null,
                values);
        Cursor cursor = database.query(BookDatabaseHandler.TABLE_BOOKS,
                allColumns, BookDatabaseHandler.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        BookIdea newBookIdea = cursorToBookIdea(cursor);
        cursor.close();
        return newBookIdea;
    }

    public void deleteBookIdea(BookIdea bookIdea) {
        long id = bookIdea.getId();
        System.out.println("BookIdea deleted with id: " + id);
        database.delete(BookDatabaseHandler.TABLE_BOOKS, BookDatabaseHandler.COLUMN_ID
                + " = " + id, null);
    }

    public List<BookIdea> getAllBooksIdeas() {
        List<BookIdea> bookIdeas = new ArrayList<BookIdea>();

        Cursor cursor = database.query(BookDatabaseHandler.TABLE_BOOKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BookIdea bookIdea = cursorToBookIdea(cursor);
            bookIdeas.add(bookIdea);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return bookIdeas;
    }


    private BookIdea cursorToBookIdea(Cursor cursor) {
        BookIdea bookIdea = new BookIdea();
        bookIdea.setId(cursor.getLong(0));
        bookIdea.setTitle(cursor.getString(1));
        bookIdea.setAuthor(cursor.getString(2));
        bookIdea.setRemark(cursor.getString(3));
        bookIdea.setPenseBete(cursor.getInt(4));
        bookIdea.setForReading(cursor.getInt(5));
        bookIdea.setFavorite(cursor.getInt(6));
        return bookIdea;
    }
}
