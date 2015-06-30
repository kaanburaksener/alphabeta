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
import android.widget.Toast;

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


        wordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                Word word = adapter.getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(LetterWordListActivity.this, LetterWordListDetailActivity.class);

                // sending data to new activity
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

    private void initializer() {
        letter = (TextView)findViewById(R.id.letter);
        letter.setText(currentLetter.getUppercaseLetter() + " / " + currentLetter.getLowercaseLetter() + " (" + currentLetter.getPronunciation() + ")");
        wordListTab = (LinearLayout) findViewById(R.id.wordListTab);
        setFont();
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        letter.setTypeface(typeface);
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        // Detect a single-click and call my own handler.
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                    i1.putExtra("letter id", letterID);
                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                    startActivity(i1);
                    finish();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
}
