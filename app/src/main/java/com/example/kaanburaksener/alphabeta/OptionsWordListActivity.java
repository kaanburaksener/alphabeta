package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
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
    private Letter currentLetter;
    private DBHandler db;
    private TextView letter;
    private TextView addNewWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        db = new DBHandler(this);

        // getting attached intent data
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        letterID = extras.getInt("letter id");

        currentLetter = db.getLetter(letterID);
        wordList = db.getWords(letterID);

        initializer();

        final ListView wordListView = (ListView)findViewById(R.id.wordList);
        final WordListAdapter adapter = new WordListAdapter(this, wordList);
        wordListView.setAdapter(adapter);
    }

    private void initializer() {
        letter = (TextView)findViewById(R.id.letter);
        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");
        addNewWord = (TextView)findViewById(R.id.addNewWord);
        addNewWord.setOnClickListener(router);
        setFont();
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
        addNewWord.setTypeface(typeface);
    }

    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.addNewWord:
                    Intent i1 = new Intent(getApplicationContext(),AddNewWordActivity.class);

                    i1.putExtra("letter id", letterID);

                    startActivity(i1);
                    finish();
                    break;
            }
        }
    };
}
