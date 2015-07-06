package com.kaanburaksener.alphabeta.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaanburaksener.alphabeta.R;
import com.kaanburaksener.alphabeta.core.Letter;
import com.kaanburaksener.alphabeta.db.DBHandler;

import java.util.Locale;

/**
 * Created by KAAN BURAK SENER on 06.07.2015.
 */
public class LetterTab extends Fragment {
    private DBHandler db;
    private Letter letter;
    private TextView uppercaseLetter;
    private TextView lowercaseLetter;
    private TextView pronunciation;
    private TextView backButton;
    private TextView forwardButton;
    private int letterID;
    private int nextID;
    private TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.letter_tab,container,false);

        letterID = getArguments().getInt("letter id");

        initializer(v);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);

                nextID = letterID - 1;

                if (nextID == 0) {
                    nextID = 33;
                }

                intent.putExtra("letter id", nextID);
                startActivity(intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.stack_push_out);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);

                nextID = letterID + 1;

                if(nextID == 34) {
                    nextID = 1;
                }

                intent.putExtra("letter id", nextID);
                startActivity(intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.stack_pop_in, R.anim.slide_out_to_right);
            }
        });

        return v;
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer(View v) {
        uppercaseLetter = (TextView)v.findViewById(R.id.uppercaseLetter);
        lowercaseLetter = (TextView)v.findViewById(R.id.lowercaseLetter);
        pronunciation = (TextView)v.findViewById(R.id.pronunciation);
        backButton = (TextView)v.findViewById(R.id.back);
        forwardButton = (TextView)v.findViewById(R.id.forward);

        db = new DBHandler(getActivity().getApplicationContext());
        letter = db.getLetter(letterID);

        uppercaseLetter.setText(letter.getUppercaseLetter());
        lowercaseLetter.setText(letter.getLowercaseLetter());
        pronunciation.setText(letter.getPronunciation());

        pronunciation();
    }

    /**
     * This function is used to text to speech for pronunciation
     */
    private boolean pronunciation() {
        tts = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(new Locale("ru", "RU"));
                }
            }
        });

        pronunciation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = letter.getLowercaseLetter();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return false;
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
