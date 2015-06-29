package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class OptionsWordListActivity extends Activity {
    private List<Word> wordList = new ArrayList<Word>();
    private int letterID;
    private String uppercaseLetter;
    private String lowercaseLetter;
    private String pronunciation;
    private DBHandler db ;
    private TextView letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        Intent i = getIntent();

        Bundle extras = getIntent().getExtras();

        // getting attached intent data
        letterID = extras.getInt("letter id");
        uppercaseLetter = extras.getString("letter uppercase");
        lowercaseLetter = extras.getString("letter lowercase");
        pronunciation = extras.getString("letter pronunciation");

        db = new DBHandler(this);

        wordList = db.getWords(letterID);

        initializer();

        final ListView wordListView = (ListView)findViewById(R.id.wordList);
        final WordListAdapter adapter = new WordListAdapter(this, wordList);
        wordListView.setAdapter(adapter);
    }

    private void initializer() {
        letter = (TextView)findViewById(R.id.letter);
        letter.setText(uppercaseLetter + " / " + lowercaseLetter + " (" + pronunciation + ")");
        setFont();
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
    }
}
