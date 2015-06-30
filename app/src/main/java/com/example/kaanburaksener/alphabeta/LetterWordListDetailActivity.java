package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class LetterWordListDetailActivity extends Activity {
    private TextView inRussian;
    private TextView inTurkish;
    private TextView pronunciation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_word_list_detail);

        initializer();
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        inRussian = (TextView)findViewById(R.id.inRussian);
        inTurkish = (TextView)findViewById(R.id.inTurkish);
        pronunciation = (TextView)findViewById(R.id.pronunciation);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();

        inRussian.setText(extras.getString("word inRussian"));
        inTurkish.setText(extras.getString("word inTurkish"));
        pronunciation.setText(extras.getString("word pronunciation"));

        setFont();
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        inRussian.setTypeface(typeface);
        inTurkish.setTypeface(typeface);
        pronunciation.setTypeface(typeface);
    }
}
