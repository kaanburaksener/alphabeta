package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class MainMenuActivity extends Activity{
    TextView startAlphabet;
    TextView options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initializer();
    }

    /**
     * This function is used to initialize the layout elements
     */
    private void initializer() {
        startAlphabet = (TextView)findViewById(R.id.startAlphabet);
        options = (TextView)findViewById(R.id.options);
        setFont();
        startAlphabet.setOnClickListener(router);
        options.setOnClickListener(router);
    }

    /**
     * This function is used to set font to the layout elements
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        startAlphabet.setTypeface(typeface);
        options.setTypeface(typeface);
    }

    /**
     * This function is used to make the layout elements clickable
     */
    View.OnClickListener router = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.startAlphabet:
                    Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i1);
                    finish();
                    break;
                case R.id.options:
                    Intent i2 = new Intent(getApplicationContext(), OptionsActivity.class);
                    startActivity(i2);
                    finish();
                    break;
            }
        }
    };
}