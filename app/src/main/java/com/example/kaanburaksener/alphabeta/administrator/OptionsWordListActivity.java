package com.example.kaanburaksener.alphabeta.administrator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.core.Letter;
import com.example.kaanburaksener.alphabeta.R;
import com.example.kaanburaksener.alphabeta.core.Word;
import com.example.kaanburaksener.alphabeta.adapter.WordListAdapter;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class OptionsWordListActivity extends Activity {
    private List<Word> wordList;
    private int letterID;
    private Letter currentLetter;
    private DBHandler db;
    private TextView letter;
    private TextView addNewWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        // Getting data from incoming activity
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        letterID = extras.getInt("letter id");

        initializer();

        final ListView wordListView = (ListView)findViewById(R.id.wordList);
        final WordListAdapter adapter = new WordListAdapter(this, wordList);
        wordListView.setAdapter(adapter);

        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Selected item
                Word word = adapter.getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(OptionsWordListActivity.this, OptionsWordListDetailActivity.class);

                // Sending data to new activity
                i.putExtra("letter id", letterID);
                i.putExtra("word inRussian", word.getInRussian());
                i.putExtra("word inTurkish", word.getInTurkish());
                i.putExtra("word pronunciation", word.getPronunciation());

                startActivity(i);
            }
        });
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        letter = (TextView)findViewById(R.id.letter);
        addNewWord = (TextView)findViewById(R.id.addNewWord);

        setFont();

        db = new DBHandler(this);

        // Getting the letter from the database
        currentLetter = db.getLetter(letterID);
        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");

        // Getting the letter's words from the database
        wordList = new ArrayList<Word>();
        wordList = db.getWords(letterID);

        addNewWord.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
        addNewWord.setTypeface(typeface);
    }

    /**
     * This function is used to create navigation between the activities
     */
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
