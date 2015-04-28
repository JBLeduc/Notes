package com.notes.notes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste on 08/04/15.
 */
public class CommentsDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private NotesDatabaseHandler dbHelper;
    private String[] allColumns = { NotesDatabaseHandler.COLUMN_ID, NotesDatabaseHandler.COLUMN_SUBJECT, NotesDatabaseHandler.COLUMN_COMMENT, NotesDatabaseHandler.COLUMN_URGENT, NotesDatabaseHandler.COLUMN_BUY};

    public CommentsDataSource(Context context) {
        dbHelper = new NotesDatabaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Comment createComment(String subject, String comment, int urgent, int buy) {
        ContentValues values = new ContentValues();

        values.put(NotesDatabaseHandler.COLUMN_SUBJECT, subject);

        values.put(NotesDatabaseHandler.COLUMN_COMMENT, comment);

        values.put(NotesDatabaseHandler.COLUMN_URGENT, urgent);

        values.put(NotesDatabaseHandler.COLUMN_BUY, buy);

        long insertId = database.insert(NotesDatabaseHandler.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(NotesDatabaseHandler.TABLE_COMMENTS,
                allColumns, NotesDatabaseHandler.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(Comment comment) {
        long id = comment.getId();
        Log.d("Comment deleted with id: " + id, comment.getComment());
        database.delete(NotesDatabaseHandler.TABLE_COMMENTS, NotesDatabaseHandler.COLUMN_ID
                + " = " + id, null);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(NotesDatabaseHandler.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return comments;
    }


    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setSubject(cursor.getString(1));
        comment.setComment(cursor.getString(2));
        comment.setUrgent(cursor.getInt(3));
        comment.setBuy(cursor.getInt(4));
        return comment;
    }
}
