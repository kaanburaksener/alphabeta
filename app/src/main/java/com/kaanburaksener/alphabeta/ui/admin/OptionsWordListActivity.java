package com.kaanburaksener.alphabeta.ui.admin;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kaanburaksener.alphabeta.core.Letter;
import com.kaanburaksener.alphabeta.R;
import com.kaanburaksener.alphabeta.core.Word;
import com.kaanburaksener.alphabeta.ui.MainMenuActivity;
import com.kaanburaksener.alphabeta.ui.adapter.WordListAdapter;
import com.kaanburaksener.alphabeta.db.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class OptionsWordListActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private List<Word> wordList;
    private int letterID;
    private Letter currentLetter;
    private DBHandler db;
    private TextView letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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

        setFont();

        db = new DBHandler(this);

        // Getting the letter from the database
        currentLetter = db.getLetter(letterID);
        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");

        // Getting the letter's words from the database
        wordList = new ArrayList<Word>();
        wordList = db.getWords(letterID);
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_word_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_new_word) {
            Intent i1 = new Intent(getApplicationContext(),AddNewWordActivity.class);
            i1.putExtra("letter id", letterID);
            startActivity(i1);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
