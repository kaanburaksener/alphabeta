package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by KAAN BURAK SENER on 29.06.2015.
 */
public class LetterWordListActivity extends Activity {
    private DBHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_word_list_activity);
        db = new DBHandler(this);


    }

}
