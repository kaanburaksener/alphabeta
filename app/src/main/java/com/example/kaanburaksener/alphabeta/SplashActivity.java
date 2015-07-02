package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class SplashActivity extends Activity {
    ImageView splashBackground;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initializer();

        Thread mSplashThread;
        mSplashThread = new Thread(){
            @Override public void run(){
            try {
                synchronized(this){
                    wait(SPLASH_DISPLAY_LENGTH);
                }
            }

            catch(InterruptedException ex){

            }

            finally {
                Intent i = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(i);
                finish();
            }
            }
        };
        mSplashThread.start();
    }

    /**
     * This function is used to initialize the layout elements
     */
    private void initializer() {
        splashBackground = (ImageView) findViewById(R.id.bg);
        splashBackground.setImageResource(R.mipmap.bg);
    }
}
