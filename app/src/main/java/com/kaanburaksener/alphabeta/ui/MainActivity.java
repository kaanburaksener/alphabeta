package com.kaanburaksener.alphabeta.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kaanburaksener.alphabeta.R;
import com.kaanburaksener.alphabeta.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private List<String> titles;
    private int numberOftabs = 2;
    private int letterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Getting attached intent data
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        letterID = extras.getInt("letter id");

        // Initializing Tabs' Titles
        titles = new ArrayList<String>();
        titles.add("Harf");
        titles.add("Sözcükler");

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),titles, numberOftabs, letterID);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
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
