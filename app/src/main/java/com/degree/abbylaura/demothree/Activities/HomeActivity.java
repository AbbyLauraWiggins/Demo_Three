package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;

import com.degree.abbylaura.demothree.Activities.Members.MembersActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

    }

    public void onGoToNotices(View view) {
        Intent goToNotices = new Intent(this, NoticeActivity.class);
        startActivity(goToNotices);
    }

    public void onGoToStats(View view) {
        Intent goToStats = new Intent(this, StatisticsActivity.class);
        startActivity(goToStats);
    }

    public void onGoToLog(View view) {
        Intent goToLog = new Intent(this, LogActivity.class);
        startActivity(goToLog);
    }

    public void onGoToCalendar(View view) {
        Intent goToCalendar = new Intent(this, CalendarActivity.class);
        startActivity(goToCalendar);
    }

    public void onGoToGallery(View view) {
        Intent goToGallery = new Intent(this, GalleryActivity.class);
        startActivity(goToGallery);
    }

    public void onGoToMembers(View view) {
        Intent goToMembers = new Intent(this, MembersActivity.class);
        startActivity(goToMembers);
    }

    public void onGoToProfile(View view) {
        Intent goToProfile = new Intent(this, ProfileActivity.class);
        goToProfile.putExtra("MemberID", MyClientID.myID); //send my own ID as this button is to view own profile
        startActivity(goToProfile);
    }
}
