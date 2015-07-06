package com.kaanburaksener.alphabeta.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kaanburaksener.alphabeta.ui.LetterTab;
import com.kaanburaksener.alphabeta.ui.WordListTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 06.07.2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<String> titles = new ArrayList<String>(); // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int numbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    int letterID;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, List<String> mTitles, int mNumbOfTabsumb ,int mLetterID) {
        super(fm);

        this.titles = mTitles;
        this.numbOfTabs = mNumbOfTabsumb;
        this.letterID = mLetterID;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if(position == 0) { // if the position is 0 we are returning the First tab
            Bundle bundle = new Bundle();
            bundle.putInt("letter id", letterID);
            LetterTab letterTab = new LetterTab();
            letterTab.setArguments(bundle);
            return letterTab;
        } else {            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            Bundle bundle = new Bundle();
            bundle.putInt("letter id", letterID);
            WordListTab wordListTab = new WordListTab();
            wordListTab.setArguments(bundle);
            return wordListTab;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
