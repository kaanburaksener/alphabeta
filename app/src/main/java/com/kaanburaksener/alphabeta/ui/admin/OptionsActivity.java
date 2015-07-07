package com.kaanburaksener.alphabeta.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kaanburaksener.alphabeta.core.Letter;
import com.kaanburaksener.alphabeta.ui.MainMenuActivity;
import com.kaanburaksener.alphabeta.ui.adapter.LetterListAdapter;
import com.kaanburaksener.alphabeta.R;
import com.kaanburaksener.alphabeta.db.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class OptionsActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private List<Letter> letterList = new ArrayList<Letter>();
    private DBHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            Intent i1 = new Intent(getApplicationContext(),MainMenuActivity.class);
            startActivity(i1);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
