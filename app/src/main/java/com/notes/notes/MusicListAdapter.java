package com.notes.notes;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notes.notes.Database_music.MusicIdea;

import java.util.List;

/**
 * Created by Jean-Baptiste on 09/04/15.
 */
public class MusicListAdapter extends ArrayAdapter<MusicIdea> {
    private Context context;
    private int resource;
    private List<MusicIdea> objects;
    private SparseBooleanArray mSelectedItemsIds;


    public MusicListAdapter(Context context, int resource, List<MusicIdea> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        mSelectedItemsIds = new SparseBooleanArray();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(resource, parent, false);
        }

        MusicIdea musicIdea = objects.get(position);

        TextView subjectTextView = (TextView) convertView.findViewById(R.id.listTextLineOne);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.listTextLineTwo);

        subjectTextView.setText(musicIdea.getTitle());
        contentTextView.setText(musicIdea.getComposer());


        return convertView;

    }

    @Override
    public void remove(MusicIdea musicIdea) {
        objects.remove(musicIdea);

        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
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
