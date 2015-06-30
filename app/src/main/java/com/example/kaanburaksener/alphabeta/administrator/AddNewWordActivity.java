package com.example.kaanburaksener.alphabeta.administrator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kaanburaksener.alphabeta.R;
import com.example.kaanburaksener.alphabeta.administrator.OptionsWordListActivity;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */

public class AddNewWordActivity extends Activity {
    private int firstLetterId;
    private String inRussian;
    private String inTurkish;
    private String pronunciation;
    private TextView saveButton;
    private TextView heading;
    private EditText inRussianET;
    private EditText inTurkishET;
    private EditText pronunciationET;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        // Getting data from incoming activity
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        firstLetterId = extras.getInt("letter id");

        initializer();
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        saveButton = (TextView)findViewById(R.id.saveButton);
        heading = (TextView)findViewById(R.id.heading);
        inRussianET = (EditText)findViewById(R.id.inRussian);
        inTurkishET = (EditText)findViewById(R.id.inTurkish);
        pronunciationET = (EditText)findViewById(R.id.pronunciation);

        setFont();

        db = new DBHandler(this);

        saveButton.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        heading.setTypeface(typeface);
        inRussianET.setTypeface(typeface);
        inTurkishET.setTypeface(typeface);
        pronunciationET.setTypeface(typeface);
    }

    /**
     * This function is used to create navigation between the activities
     */
    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
        switch(v.getId()) {
            case R.id.saveButton:
                // Get data from the new word form
                inRussian = inRussianET.getText().toString();
                inTurkish = inTurkishET.getText().toString();
                pronunciation = pronunciationET.getText().toString();

                // Add the new word to the database
                db.addWord(inRussian, inTurkish, pronunciation, firstLetterId);

                // Go to the word list activity
                Intent i1 = new Intent(getApplicationContext(),OptionsWordListActivity.class);
                i1.putExtra("letter id", firstLetterId);
                startActivity(i1);
                finish();
                break;
        }
        }
    };
}
