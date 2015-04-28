package com.notes.notes.newNote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.notes.notes.Database_film.FilmIdea;
import com.notes.notes.Database_film.FilmsDataSource;
import com.notes.notes.Film;
import com.notes.notes.R;


public class FilmNote extends ActionBarActivity {
    private CardView register;
    private FilmsDataSource dataSource;
    private EditText titleField;
    private EditText directorField;
    private RadioButton radioFilm;
    private RadioButton radioVideo;

    private int film;
    private int video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_note);

        //on récupère tous les élements de l'UI
        titleField = (EditText) findViewById(R.id.film_title);
        directorField = (EditText) findViewById(R.id.film_director);

        radioFilm = (RadioButton) findViewById(R.id.radioFilm);
        radioVideo = (RadioButton) findViewById(R.id.radioVideo);

        register = (CardView) findViewById(R.id.film_register);

        //on crée la database et on l'ouvre
        dataSource = new FilmsDataSource(this);
        dataSource.open();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioFilm.isChecked()) {
                    film = 1;
                    Log.d("Etat1", "film : " + film + " video : " + video);
                }
                if (radioVideo.isChecked()) {
                    video = 1;
                    Log.d("Etat2","urgent : "+ film +" buy : "+ video);

                }
                //on insère un nouveau commentaire dans la base de données (ce qui revient aussi à en créer un que n'utilisera pas en tant que tel)
                FilmIdea filmIdea = dataSource.createFilmIdea(titleField.getText().toString(), directorField.getText().toString(), film, video);

                //en cliquant sur enregistrer l'utilisateur est redirigé vers la liste des notes
                Intent iRegisterButton = new Intent(FilmNote.this, Film.class);
                startActivity(iRegisterButton);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_film_note, menu);
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
