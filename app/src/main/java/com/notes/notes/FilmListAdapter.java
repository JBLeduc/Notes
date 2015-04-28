package com.notes.notes;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.notes.notes.Database_film.FilmIdea;

import java.util.List;

/**
 * Created by Jean-Baptiste on 09/04/15.
 */
public class FilmListAdapter extends ArrayAdapter<FilmIdea> {
    private Context context;
    private int resource;
    private List<FilmIdea> objects;
    private SparseBooleanArray mSelectedItemsIds;


    public FilmListAdapter(Context context, int resource, List<FilmIdea> objects) {
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

        FilmIdea filmIdea = objects.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.listTextSubject);
        TextView directorTextView = (TextView) convertView.findViewById(R.id.listTextContent);
        ImageView reminderIconeView = (ImageView) convertView.findViewById(R.id.reminder_icone);

        titleTextView.setText(filmIdea.getTitle());
        directorTextView.setText(filmIdea.getDirector());

        if (filmIdea.getFilm() == 1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_theaters_grey600_18dp);
        }
        if (filmIdea.getVideo() == 1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_play_circle_outline_grey600_18dp);
        }


        return convertView;
    }

    @Override
    public void remove(FilmIdea filmIdea) {
        objects.remove(filmIdea);

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
