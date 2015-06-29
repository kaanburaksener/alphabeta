package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class LetterListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Letter> letterList;

    public LetterListAdapter(Activity activity, List<Letter> letterList) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.letterList = letterList;
    }

    @Override
    public int getCount() {
        return letterList.size();
    }

    @Override
    public Letter getItem(int position) {
        return letterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = inflater.inflate(R.layout.letter_row_layout, null);
        TextView uppercaseLetter = (TextView)rowView.findViewById(R.id.uppercaseLetter);
        TextView lowercaseLetter = (TextView)rowView.findViewById(R.id.lowercaseLetter);

        Letter letter = letterList.get(position);

        uppercaseLetter.setText(letter.getUppercaseLetter());
        lowercaseLetter.setText(letter.getLowercaseLetter());

        return rowView;
    }
}
