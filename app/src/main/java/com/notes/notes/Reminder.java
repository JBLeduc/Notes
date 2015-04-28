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

import com.notes.notes.Database.Comment;
import com.notes.notes.Database.CommentsDataSource;
import com.notes.notes.newNote.ReminderNote;
import com.software.shell.fab.ActionButton;

import java.util.List;


public class Reminder extends ActionBarActivity {
    private ActionButton actionButton;
    private CommentsDataSource dataSource;
    private ListView listView;
    private ReminderListAdapter myAdapter;

    // Test premier commit


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        //on crée et on ouvre la base de donnée
        dataSource = new CommentsDataSource(this);
        dataSource.open();

        //on récupère tout le contenu de la BDD
        List<Comment> values = dataSource.getAllComments();

        //on crée un nouvel adapter selon le schéma de la classe associée, ici ReminderListAdapter
        myAdapter = new ReminderListAdapter(this,R.layout.list_standard_row,values);

        //on récupère la listeView
        listView = (ListView) findViewById(R.id.listReminder);

        //on lui assigne un adapter
        listView.setAdapter(myAdapter);

        //on vérifie s'il y a des nouvelles notes à ajouter à la listeView
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
                                Comment selectedItem = myAdapter.getItem(selected.keyAt(i));
                                myAdapter.remove(selectedItem);
                                dataSource.deleteComment(selectedItem);
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

        actionButton = (ActionButton) findViewById(R.id.action_button_reminder);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent iActionButton = new Intent(Reminder.this, ReminderNote.class);
                    startActivity(iActionButton);
                }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);
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
