package com.example.kaanburaksener.alphabeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class OptionsActivity extends Activity {
    private List<Letter> letterList = new ArrayList<Letter>();
    private DBHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        db = new DBHandler(this);

        letterList = db.getLetters();

        final ListView letterListView = (ListView)findViewById(R.id.letterList);
        final LetterListAdapter adapter = new LetterListAdapter(this, letterList);
        letterListView.setAdapter(adapter);

        letterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                Letter letter = adapter.getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(OptionsActivity.this, OptionsWordListActivity.class);

                // sending data to new activity
                i.putExtra("letter id", letter.getId());
                i.putExtra("letter uppercase", letter.getUppercaseLetter());
                i.putExtra("letter lowercase", letter.getLowercaseLetter());
                i.putExtra("letter pronunciation", letter.getPronunciation());

                startActivity(i);
            }
        });
    }
}
