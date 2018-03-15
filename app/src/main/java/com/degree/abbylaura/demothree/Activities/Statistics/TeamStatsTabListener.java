package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.degree.abbylaura.demothree.R;


/**
 * Created by abbylaura on 07/03/2018.
 */

public class TeamStatsTabListener implements ActionBar.TabListener {

    Fragment fragment;

    public TeamStatsTabListener(Fragment fragmentToChangeTo) {
        this.fragment = fragmentToChangeTo;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Replace the current fragment with the fragmentToChangeTo
        System.out.println("onTabSelected");

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Get rid of fragment when unselected
        ft.remove(fragment);

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //TODO
    }


}