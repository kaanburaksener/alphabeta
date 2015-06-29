package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        Intent i = getIntent();

        Bundle extras = getIntent().getExtras();

        firstLetterId = extras.getInt("letter id");
        System.out.println(firstLetterId);

        initializer();
        db = new DBHandler(this);
    }

    private void initializer() {
        saveButton = (TextView)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(router);
        heading = (TextView)findViewById(R.id.heading);
        inRussianET = (EditText)findViewById(R.id.inRussian);
        inTurkishET = (EditText)findViewById(R.id.inTurkish);
        pronunciationET = (EditText)findViewById(R.id.pronunciation);
        setFont();
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        heading.setTypeface(typeface);
    }

    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.saveButton:
                    inRussian = inRussianET.getText().toString();
                    inTurkish = inTurkishET.getText().toString();
                    pronunciation = pronunciationET.getText().toString();

                    db.addWord(inRussian, inTurkish, pronunciation, firstLetterId);

                    Intent i1 = new Intent(getApplicationContext(),OptionsWordListActivity.class);

                    i1.putExtra("letter id", firstLetterId);

                    startActivity(i1);
                    finish();
                    break;
            }
        }
    };
}
