package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.adapter.WordListAdapter;
import com.example.kaanburaksener.alphabeta.core.Letter;
import com.example.kaanburaksener.alphabeta.core.Word;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class LetterWordListActivity extends Activity {
    private List<Word> wordList = new ArrayList<Word>();
    private int letterID;
    private Letter currentLetter;
    private DBHandler db;
    private TextView letter;
    private LinearLayout wordListTab;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_word_list);

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
                Intent i = new Intent(LetterWordListActivity.this, LetterWordListDetailActivity.class);

                // Sending data to new activity
                i.putExtra("letter id", letterID);
                i.putExtra("word inRussian", word.getInRussian());
                i.putExtra("word inTurkish", word.getInTurkish());
                i.putExtra("word pronunciation", word.getPronunciation());

                startActivity(i);
            }
        });

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        wordListView.setOnTouchListener(gestureListener);
        wordListTab.setOnTouchListener(gestureListener);
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        letter = (TextView)findViewById(R.id.letter);
        wordListTab = (LinearLayout) findViewById(R.id.wordListTab);

        setFont();

        db = new DBHandler(this);

        // Getting letter and its word list from the database
        currentLetter = db.getLetter(letterID);
        wordList = db.getWords(letterID);

        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
    }

    /**
     * This class is used to detect gesture and routing between activities
     */
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        // Detect a single-click and left-right swipes
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() < e2.getX()) {
                //Swipe from left to right
            }

            if (e1.getX() > e2.getX()) {
                // Swipe from right to left
            }

            if (e1.getY() < e2.getY()) {
                // Swipe from up to down
                Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                i1.putExtra("letter id", letterID);
                startActivity(i1);
                finish();
                overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top );
            }

            if (e1.getY() > e2.getY()) {
                // Swipe from down to up
            }

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
}
