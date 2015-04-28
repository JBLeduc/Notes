package com.notes.notes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.notes.notes.Database.Comment;

import java.util.List;

/**
 * Created by Jean-Baptiste on 09/04/15.
 */
public class ReminderListAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private int resource;
    private List<Comment> objects;
    private SparseBooleanArray mSelectedItemsIds;



    public ReminderListAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView==null){

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(resource, parent, false);
        }

        Comment comment = objects.get(position);

        TextView subjectTextView = (TextView) convertView.findViewById(R.id.listTextSubject);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.listTextContent);
        ImageView reminderIconeView = (ImageView) convertView.findViewById(R.id.reminder_icone);

        subjectTextView.setText(comment.getSubject());
        contentTextView.setText(comment.getComment());

        Log.d("Etat adapter","urgent : "+comment.getUrgent() + " buy : "+comment.getBuy());

        if (comment.getUrgent()==1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_grade_grey600_18dp);
        }
        if (comment.getBuy()==1) {
                reminderIconeView.setBackgroundResource(R.drawable.ic_credit_card_grey600_18dp);
        }




        return convertView;

    }

    @Override
    public void remove(Comment comment) {
        objects.remove(comment);

        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
        Log.d("Toggle",position + " et " + !mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }


    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }






}
