package com.example.kaanburaksener.alphabeta.administrator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.R;
import com.example.kaanburaksener.alphabeta.core.Word;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

import java.util.Locale;

/**
 * Created by KAAN BURAK SENER on 02.07.2015.
 */
public class OptionsWordListDetailActivity extends Activity {
    private TextView inRussianTV;
    private TextView inTurkishTV;
    private TextView pronunciationTV;
    private TextView deleteButtonText;
    private String inRussian;
    private String inTurkish;
    private String pronunciation;
    private int firstLetterId;
    private LinearLayout pronunciationTrigger;
    private LinearLayout deleteButton;
    private TextToSpeech tts;
    private DBHandler db;
    private Word currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_word_list_detail);

        initializer();
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        inRussianTV = (TextView)findViewById(R.id.inRussian);
        inTurkishTV = (TextView)findViewById(R.id.inTurkish);
        pronunciationTV = (TextView)findViewById(R.id.pronunciation);
        pronunciationTrigger = (LinearLayout) findViewById(R.id.pronunciationTrigger);
        deleteButton = (LinearLayout) findViewById(R.id.deleteButton);
        deleteButtonText = (TextView) findViewById(R.id.deleteButtonText);

        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();

        firstLetterId = extras.getInt("letter id");
        inRussian = extras.getString("word inRussian");
        inTurkish = extras.getString("word inTurkish");
        pronunciation = extras.getString("word pronunciation");

        currentWord = new Word(inRussian, inTurkish, pronunciation, firstLetterId);

        inRussianTV.setText(inRussian);
        inTurkishTV.setText(inTurkish);
        pronunciationTV.setText(pronunciation);

        db = new DBHandler(this);

        setFont();

        pronunciation(extras.getString("word inRussian"));

        deleteButton.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        inRussianTV.setTypeface(typeface);
        inTurkishTV.setTypeface(typeface);
        pronunciationTV.setTypeface(typeface);
        deleteButtonText.setTypeface(typeface);

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
            }
        };

        inRussianTV.setOutlineProvider(viewOutlineProvider);
        inTurkishTV.setOutlineProvider(viewOutlineProvider);
        pronunciationTrigger.setOutlineProvider(viewOutlineProvider);
    }

    /**
     * This function is used to create navigation between the activities
     */
    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
        switch(v.getId()) {
            case R.id.deleteButton:
                // Delete the word from the database
                db.deleteWord(currentWord);

                // Go to the word list activity
                Intent i1 = new Intent(getApplicationContext(),OptionsWordListActivity.class);
                i1.putExtra("letter id", firstLetterId);
                startActivity(i1);
                finish();
                break;
        }
        }
    };

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
