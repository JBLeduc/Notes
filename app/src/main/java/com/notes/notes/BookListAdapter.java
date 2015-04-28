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

import com.notes.notes.Database_books.BookIdea;

import java.util.List;

/**
 * Created by Jean-Baptiste on 10/04/15.
 */
public class BookListAdapter extends ArrayAdapter<BookIdea> {
    private Context context;
    private int resource;
    private List<BookIdea> objects;
    private SparseBooleanArray mSelectedItemsIds ;




    public BookListAdapter(Context context, int resource, List<BookIdea> objects) {
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

        BookIdea bookIdea = objects.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.listTextSubject);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.listTextContent);
        ImageView reminderIconeView = (ImageView) convertView.findViewById(R.id.reminder_icone);

        titleTextView.setText(bookIdea.getTitle());
        authorTextView.setText(bookIdea.getAuthor());


        if (bookIdea.getPenseBete()==1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_star_outline_grey600_18dp);
        }
        if (bookIdea.getForReading()==1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_star_half_grey600_18dp);
        }
        if (bookIdea.getFavorite()==1) {
            reminderIconeView.setBackgroundResource(R.drawable.ic_star_grey600_18dp);
        }


        return convertView;

    }

    @Override
    public void remove(BookIdea bookIdea) {
        objects.remove(bookIdea);

        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
        Log.d("Toggle", position + " et " + !mSelectedItemsIds.get(position));
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
