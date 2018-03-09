package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.Members.MembersActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class HomeActivity extends Activity {

    TextView nextGameDate, nextGameMonth;
    Button notice, stats, log, calendar, members, gallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        nextGameDate = findViewById(R.id.next_game_home_tv);
        nextGameMonth = findViewById(R.id.next_game_month_home_tv);

        setButtons();




    }

    public void setButtons(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        notice = findViewById(R.id.notice_home_button);
        stats = findViewById(R.id.stats_home_button);
        log = findViewById(R.id.log_home_button);
        calendar = findViewById(R.id.calendar_home_button);
        gallery = findViewById(R.id.gallery_home_button);
        members = findViewById(R.id.members_home_button);

        android.view.ViewGroup.LayoutParams layoutParams = notice.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        notice.setLayoutParams(layoutParams);
        notice.setVisibility(View.VISIBLE);

        layoutParams = stats.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        stats.setLayoutParams(layoutParams);
        stats.setVisibility(View.VISIBLE);

        layoutParams = log.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        log.setLayoutParams(layoutParams);
        log.setVisibility(View.VISIBLE);

        layoutParams = calendar.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        calendar.setLayoutParams(layoutParams);
        calendar.setVisibility(View.VISIBLE);

        layoutParams = gallery.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        gallery.setLayoutParams(layoutParams);
        gallery.setVisibility(View.VISIBLE);

        layoutParams = members.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        members.setLayoutParams(layoutParams);
        members.setVisibility(View.VISIBLE);
    }

    public void setNextGame() throws ParseException {
        TeamFixturesRepo teamFixturesRepo = new TeamFixturesRepo();
        ArrayList<String> fixtureDates = teamFixturesRepo.getMyFixtureDates();

        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date now = new Date();


        NavigableSet<Date> dates = new TreeSet<Date>();
        for(String day : fixtureDates){
            Date fixtureDate = df.parse(day);
            if(fixtureDate.after(now)){
                System.out.println(day);
                dates.add(fixtureDate);
            }
        }

        System.out.println("now DATE  " + df.format(now));

        Date nearest = dates.first(); //returns lowest date thats above now

        System.out.println("NEAREST DATE + " + df.format(nearest));
        //nextGameDate.setText(df.format(nearest));//.substring(0,2));

        //String month = String.valueOf(closestDate).substring(4,5);*/
        //TODO show month




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
