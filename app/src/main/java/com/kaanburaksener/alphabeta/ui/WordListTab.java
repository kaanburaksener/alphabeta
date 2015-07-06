package com.kaanburaksener.alphabeta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kaanburaksener.alphabeta.R;
import com.kaanburaksener.alphabeta.core.Letter;
import com.kaanburaksener.alphabeta.core.Word;
import com.kaanburaksener.alphabeta.db.DBHandler;
import com.kaanburaksener.alphabeta.ui.adapter.WordListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 06.07.2015.
 */
public class WordListTab extends Fragment {
    private List<Word> wordList = new ArrayList<Word>();
    private int letterID;
    private Letter currentLetter;
    private DBHandler db;
    private TextView letter;
    private LinearLayout wordListTab;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.word_list_tab,container,false);

        letterID = getArguments().getInt("letter id");

        initializer(v);

        final ListView wordListView = (ListView)v.findViewById(R.id.wordList);
        final WordListAdapter adapter = new WordListAdapter(getActivity(), wordList);
        wordListView.setAdapter(adapter);

        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Selected item
                Word word = adapter.getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getActivity(), LetterWordListDetailActivity.class);

                // Sending data to new activity
                i.putExtra("letter id", letterID);
                i.putExtra("word inRussian", word.getInRussian());
                i.putExtra("word inTurkish", word.getInTurkish());
                i.putExtra("word pronunciation", word.getPronunciation());

                startActivity(i);
                getActivity().finish();
            }
        });

        return v;
    }

    private void initializer(View v) {
        letter = (TextView)v.findViewById(R.id.letter);
        wordListTab = (LinearLayout)v.findViewById(R.id.wordListTab);

        db = new DBHandler(getActivity().getApplicationContext());

        // Getting letter and its word list from the database
        currentLetter = db.getLetter(letterID);
        wordList = db.getWords(letterID);

        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");
    }
}
