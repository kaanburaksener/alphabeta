package com.example.kaanburaksener.alphabeta;

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
import java.util.Locale;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class LetterWordListDetailActivity extends Activity {
    private TextView inRussian;
    private TextView inTurkish;
    private TextView pronunciation;
    private int letterId;
    private LinearLayout pronunciationTrigger;
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
        pronunciation = (TextView)findViewById(R.id.pronunciation);
        pronunciationTrigger = (LinearLayout) findViewById(R.id.pronunciationTrigger);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();

        letterId = extras.getInt("letter id");
        inRussian.setText(extras.getString("word inRussian"));
        inTurkish.setText(extras.getString("word inTurkish"));
        pronunciation.setText(extras.getString("word pronunciation"));

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
        pronunciation.setTypeface(typeface);

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
            }
        };

        inRussian.setOutlineProvider(viewOutlineProvider);
        inTurkish.setOutlineProvider(viewOutlineProvider);
        pronunciationTrigger.setOutlineProvider(viewOutlineProvider);
    }

    /**
     * This function is used to text to speech for pronunciation
     */
    private void pronunciation(final String pronunciation) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(new Locale("ru", "RU"));
                }
            }
        });

        pronunciationTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = pronunciation;
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
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
