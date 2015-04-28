package com.notes.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.notes.notes.Database_music.MusicDataSource;
import com.notes.notes.Database_music.MusicIdea;
import com.notes.notes.newNote.MusicNote;
import com.software.shell.fab.ActionButton;

import java.util.List;


public class Music extends ActionBarActivity {
    private ListView listView;
    private ActionButton actionButton;
    private MusicListAdapter myAdapter;
    private MusicDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        dataSource = new MusicDataSource(this);
        dataSource.open();


        List<MusicIdea> values = dataSource.getAllMusicIdeas();

        myAdapter = new MusicListAdapter(this,R.layout.list_standard_two_lines,values);

        listView = (ListView) findViewById(R.id.listMusic);

        listView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                final int checkCount = listView.getCheckedItemCount();
                if (checkCount == 1 || checkCount == 0) {
                    mode.setTitle(checkCount + " sélectionné");
                }
                else {
                    mode.setTitle(checkCount + " sélectionnés");
                }

                myAdapter.toggleSelection(position);


            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_context_menu, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_icon:
                        SparseBooleanArray selected = myAdapter.getSelectedIds();

                        for (int i = (selected.size()-1); i >= 0 ; i--) {
                            if(selected.valueAt(i)) {
                               MusicIdea selectedItem = myAdapter.getItem(selected.keyAt(i));
                                myAdapter.remove(selectedItem);
                                dataSource.deleteMusicIdea(selectedItem);
                            }
                        }
                        mode.finish();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });


        actionButton = (ActionButton) findViewById(R.id.action_button_music);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iActionButton = new Intent(Music.this, MusicNote.class);
                startActivity(iActionButton);
            }
        });



        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
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
