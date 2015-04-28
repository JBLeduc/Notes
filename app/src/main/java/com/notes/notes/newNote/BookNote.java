package com.notes.notes.newNote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.notes.notes.Book;
import com.notes.notes.Database_books.BookIdea;
import com.notes.notes.Database_books.BooksDataSource;
import com.notes.notes.R;


public class BookNote extends ActionBarActivity {

    private CardView register;
    private BooksDataSource dataSource;
    private EditText titleField;
    private EditText authorField;
    private EditText remarkField;
    private RadioButton radioPenseBete;
    private RadioButton radioForReading;
    private RadioButton radioFavorite;

    private int penseBete;
    private int forReading;
    private int favorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_note);

        //on récupère tous les élements de l'UI
        titleField = (EditText) findViewById(R.id.book_title);
        authorField = (EditText) findViewById(R.id.book_author);
        remarkField = (EditText) findViewById(R.id.book_notes);

        radioPenseBete = (RadioButton) findViewById(R.id.radioPenseBete);
        radioForReading = (RadioButton) findViewById(R.id.radioForReading);
        radioFavorite = (RadioButton) findViewById(R.id.radioFavorite);

        register = (CardView) findViewById(R.id.book_register);

        //on crée la database et on l'ouvre
        dataSource = new BooksDataSource(this);
        dataSource.open();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioPenseBete.isChecked()) {
                    penseBete = 1;
                }
                if (radioForReading.isChecked()) {
                    forReading = 1;
                }
                if (radioFavorite.isChecked()) {
                    favorite = 1;
                }
                //on insère un nouveau commentaire dans la base de données (ce qui revient aussi à en créer un que n'utilisera pas en tant que tel)
                BookIdea bookIdea = dataSource.createBookIdea(titleField.getText().toString(), authorField.getText().toString(), remarkField.toString(), penseBete,forReading,favorite);

                //en cliquant sur enregistrer l'utilisateur est redirigé vers la liste des notes
                Intent iRegisterButton = new Intent(BookNote.this, Book.class);
                startActivity(iRegisterButton);


            }
        });

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
