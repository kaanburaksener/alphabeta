package com.example.kaanburaksener.alphabeta.administrator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaanburaksener.alphabeta.core.Letter;
import com.example.kaanburaksener.alphabeta.adapter.LetterListAdapter;
import com.example.kaanburaksener.alphabeta.R;
import com.example.kaanburaksener.alphabeta.helper.DBHandler;

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

        initializer();

        final ListView letterListView = (ListView)findViewById(R.id.letterList);
        final LetterListAdapter adapter = new LetterListAdapter(this, letterList);
        letterListView.setAdapter(adapter);

        letterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Selected item
                Letter letter = adapter.getItem(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(OptionsActivity.this, OptionsWordListActivity.class);
                i.putExtra("letter id", letter.getId());
                startActivity(i);
            }
        });
    }

    /**
     * This function is used to initialize the layout elements and the attributes of the class
     */
    private void initializer() {
        db = new DBHandler(this);

        // Get letters from the database
        letterList = db.getLetters();
    }
}
