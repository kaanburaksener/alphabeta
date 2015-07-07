package com.kaanburaksener.alphabeta.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;

import com.kaanburaksener.alphabeta.R;

import java.util.Locale;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class WordListTabDetailActivity extends Activity {
    private TextView inRussian;
    private TextView inTurkish;
    private TextView pronunciationTV;
    private int letterID;
    private TextToSpeech tts;

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
        pronunciationTV = (TextView)findViewById(R.id.pronunciation);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();

        letterID = extras.getInt("letter id");
        inRussian.setText(extras.getString("word inRussian"));
        inTurkish.setText(extras.getString("word inTurkish"));
        pronunciationTV.setText(extras.getString("word pronunciation"));

        setFont();

        pronunciation(extras.getString("word inRussian"));
    }

    /**
     * This function is used to set font to the layout elements
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        inRussian.setTypeface(typeface);
        inTurkish.setTypeface(typeface);
        pronunciationTV.setTypeface(typeface);
    }

    /**
     * This function is used to text to speech for pronunciation
     */
    private boolean pronunciation(final String pronunciation) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(new Locale("ru", "RU"));
                }
            }
        });

        pronunciationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = pronunciation;
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return false;
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
