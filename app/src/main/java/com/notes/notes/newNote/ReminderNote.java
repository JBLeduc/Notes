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

import com.notes.notes.Database.Comment;
import com.notes.notes.Database.CommentsDataSource;
import com.notes.notes.R;
import com.notes.notes.Reminder;

public class ReminderNote extends ActionBarActivity {
     private CardView register;
                private CommentsDataSource dataSource;
                private EditText subjectField;
                private EditText contentField;
                private RadioButton radioUrgent;
                private RadioButton radioBuy;

                private int urgent;
                private int buy;


                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_reminder_note);

                    //on récupère tous les élements de l'UI
                    subjectField = (EditText) findViewById(R.id.reminder_subject);
                    contentField = (EditText) findViewById(R.id.reminder_content);

                    radioUrgent = (RadioButton) findViewById(R.id.radioUrgent);
                    radioBuy = (RadioButton) findViewById(R.id.radioToBuy);

                    register = (CardView) findViewById(R.id.reminder_register);

                    //on crée la database et on l'ouvre
                    dataSource = new CommentsDataSource(this);
                    dataSource.open();



                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (radioUrgent.isChecked()) {
                                urgent = 1;
                                Log.d("Etat1","urgent : "+urgent+" buy : "+buy);
                            }
                            if (radioBuy.isChecked()) {
                                buy = 1;
                                Log.d("Etat2","urgent : "+urgent+" buy : "+buy);

                            }
                            //on insère un nouveau commentaire dans la base dedonnées (ce qui revient aussi à en créer un que n'utilisera pas en tant que tel)
                Comment comment = dataSource.createComment(subjectField.getText().toString(),contentField.getText().toString(),urgent,buy);

                //en cliquant sur enregistrer l'utilisateur est redirigé vers la liste des notes
                Intent iRegisterButton = new Intent(ReminderNote.this, Reminder.class);
                startActivity(iRegisterButton);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder_note, menu);
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
