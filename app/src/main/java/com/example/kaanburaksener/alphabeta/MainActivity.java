package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHandler(this);

        // getting attached intent data
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        letterID = extras.getInt("letter id");
        letter = db.getLetter(letterID);

        initializer();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    /**
     * This function is used to initialize the layout elements
     */
    private void initializer() {
        uppercaseLetter = (TextView)findViewById(R.id.uppercaseLetter);
        lowercaseLetter = (TextView)findViewById(R.id.lowercaseLetter);
        pronunciation = (TextView)findViewById(R.id.pronunciation);
        backButton = (TextView)findViewById(R.id.back);
        forwardButton = (TextView)findViewById(R.id.forward);

        setFont();

        uppercaseLetter.setText(letter.getUppercaseLetter());
        lowercaseLetter.setText(letter.getLowercaseLetter());
        pronunciation.setText("(" + letter.getPronunciation() + ")");

        backButton.setOnClickListener(router);
        forwardButton.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        uppercaseLetter.setTypeface(typeface);
        lowercaseLetter.setTypeface(typeface);
        pronunciation.setTypeface(typeface);
        backButton.setTypeface(typeface);
        forwardButton.setTypeface(typeface);
    }

    /**
     * This function is used to make the layout elements clickable
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
                    break;
                case R.id.forward:
                    nextID = letterID + 1;

                    if(nextID == 34) {
                        nextID = 1;
                    }

                    i1.putExtra("letter id", nextID);
                    break;
            }

            startActivity(i1);
            finish();
        }
    };
}
