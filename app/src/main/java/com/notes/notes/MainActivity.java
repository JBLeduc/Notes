package com.notes.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private CardView toReminder;
    private CardView toMusic;
    private CardView toFilm;
    private CardView toBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toMusic = (CardView) findViewById(R.id.card_view_music);
        toFilm = (CardView) findViewById(R.id.card_view_films);
        toBook = (CardView) findViewById(R.id.card_view_books);
        toReminder = (CardView) findViewById(R.id.card_view_reminder);

        toMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMusicActivity = new Intent(MainActivity.this,Music.class);
                startActivity(iMusicActivity);
            }
        });

        toFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFilmActivity = new Intent(MainActivity.this,Film.class);
                startActivity(iFilmActivity);
            }
        });

        toBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBookActivity = new Intent(MainActivity.this,Book.class);
                startActivity(iBookActivity);
            }
        });

        toReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReminderActivity = new Intent(MainActivity.this,Reminder.class);
                startActivity(iReminderActivity);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
