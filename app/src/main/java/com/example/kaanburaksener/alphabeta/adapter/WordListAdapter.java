package com.example.kaanburaksener.alphabeta.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.R;
import com.example.kaanburaksener.alphabeta.core.Word;

import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class WordListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Word> wordList;

    public WordListAdapter(Activity activity, List<Word> wordList) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.wordList = wordList;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public Word getItem(int position) {
        return wordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = inflater.inflate(R.layout.word_row_layout, null);
        TextView inRussian = (TextView)rowView.findViewById(R.id.inRussian);
        TextView inTurkish = (TextView)rowView.findViewById(R.id.inTurkish);

        Word word = wordList.get(position);

        inRussian.setText(word.getInRussian());
        inTurkish.setText(word.getInTurkish());

        return rowView;
    }
}

