package com.notes.notes.newNote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.notes.notes.Database_music.MusicDataSource;
import com.notes.notes.Database_music.MusicIdea;
import com.notes.notes.Music;
import com.notes.notes.R;


public class MusicNote extends ActionBarActivity {
    private CardView register;
    private EditText titleField;
    private EditText composerField;
    private EditText interpreterField;
    private MusicDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_note);

        register = (CardView) findViewById(R.id.music_register);

        titleField = (EditText) findViewById(R.id.music_title);
        composerField = (EditText) findViewById(R.id.music_composer);
        interpreterField = (EditText) findViewById(R.id.music_interpreter);

        dataSource = new MusicDataSource(this);
        dataSource.open();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicIdea musicIdea = dataSource.createMusicIdea(titleField.getText().toString(), composerField.getText().toString(), interpreterField.getText().toString());

                Intent iRegisterButton = new Intent(MusicNote.this, Music.class);
                startActivity(iRegisterButton);
            }
        });











    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_note, menu);
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
