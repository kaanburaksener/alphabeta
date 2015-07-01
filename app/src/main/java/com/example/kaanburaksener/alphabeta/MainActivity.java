package com.example.kaanburaksener.alphabeta;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.core.Letter;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

public class MainActivity extends Activity {
    private DBHandler db ;
    private Letter letter;
    private TextView uppercaseLetter;
    private TextView lowercaseLetter;
    private TextView pronunciation;
    private TextView backButton;
    private TextView forwardButton;
    private int letterID;
    private int nextID;
    private LinearLayout letterTab;
    private LinearLayout navigation;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Getting attached intent data
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        letterID = extras.getInt("letter id");

        initializer();

        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        letterTab.setOnTouchListener(gestureListener);
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        uppercaseLetter = (TextView)findViewById(R.id.uppercaseLetter);
        lowercaseLetter = (TextView)findViewById(R.id.lowercaseLetter);
        pronunciation = (TextView)findViewById(R.id.pronunciation);
        backButton = (TextView)findViewById(R.id.back);
        forwardButton = (TextView)findViewById(R.id.forward);
        letterTab = (LinearLayout) findViewById(R.id.letterTab);
        navigation = (LinearLayout) findViewById(R.id.navigation);

        setFont();

        db = new DBHandler(this);
        letter = db.getLetter(letterID);

        uppercaseLetter.setText(letter.getUppercaseLetter());
        lowercaseLetter.setText(letter.getLowercaseLetter());
        pronunciation.setText("(" + letter.getPronunciation() + ")");

        backButton.setOnClickListener(router);
        forwardButton.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        uppercaseLetter.setTypeface(typeface);
        lowercaseLetter.setTypeface(typeface);
        pronunciation.setTypeface(typeface);
        backButton.setTypeface(typeface);
        forwardButton.setTypeface(typeface);

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
            int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
            }
        };

        uppercaseLetter.setOutlineProvider(viewOutlineProvider);
        lowercaseLetter.setOutlineProvider(viewOutlineProvider);
        pronunciation.setOutlineProvider(viewOutlineProvider);
        navigation.setOutlineProvider(viewOutlineProvider);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }

    /**
     * This function is used to create navigation between the activities
     */
    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
        Intent i1 = new Intent(getApplicationContext(),MainActivity.class);

        switch(v.getId()) {
            case R.id.back:
                nextID = letterID - 1;

                if(nextID == 0) {
                    nextID = 33;
                }

                i1.putExtra("letter id", nextID);
                startActivity(i1);
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                break;
            case R.id.forward:
                nextID = letterID + 1;

                if(nextID == 34) {
                    nextID = 1;
                }

                i1.putExtra("letter id", nextID);
                startActivity(i1);
                finish();
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
        }

        }
    };

    /**
     * This class is used to detect gesture and routing between activities
     */
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
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
            }

            if (e1.getY() > e2.getY()) {
                // Swipe from down to up
                Intent i1 = new Intent(getApplicationContext(),LetterWordListActivity.class);
                i1.putExtra("letter id", letterID);
                startActivity(i1);
                finish();
                overridePendingTransition(R.anim.slide_in_from_up, R.anim.slide_out_to_bottom );
            }

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
}
