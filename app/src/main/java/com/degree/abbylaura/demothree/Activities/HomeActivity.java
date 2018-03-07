package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.Members.MembersActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class HomeActivity extends Activity {

    TextView nextGameDate, nextGameMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        nextGameDate = findViewById(R.id.next_game_home_tv);
        nextGameMonth = findViewById(R.id.next_game_month_home_tv);

    }

    public void setNextGame() throws ParseException {
        FixtureRepo fixtureRepo = new FixtureRepo();
        fixtureRepo.setWhereClause(" WHERE " + Fixture.KEY_TeamId + " = " + MyClientID.myTeamID);
        String[][] fixtures = fixtureRepo.getTableData(); //Get list of all fixtures where myTeam is playing

        TeamFixturesRepo tfRepo = new TeamFixturesRepo();
        ArrayList<String> fixtureDates = new ArrayList<>();
        for(int i = 0; i < fixtures[0].length; i++){
            String fixtureID = fixtures[0][i];  //List of fixtureIDs for myTeam so to filter

            tfRepo.setWhereClause(" WHERE " + TeamFixtures.KEY_FixtureId + " = " + fixtureID);

            fixtureDates.add(tfRepo.getTableData()[1][0]); //List of dates of fixtures where myTeam is playing
        }



        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todaysDate = simpleDate.parse(String.valueOf(date));

        Date closestDate = simpleDate.parse("01/01/3000"); //no fixtures will be later than this


        //FIND THE CLOSEST DATE TO TODAY
        for(int i = 0; i < fixtureDates.size(); i++){
            String strDate = fixtureDates.get(i);
            Date fixtureDate = simpleDate.parse(strDate);

            if(fixtureDate.after(todaysDate)){
                if(fixtureDate.before(closestDate)){
                    closestDate = fixtureDate;
                }
            }
        }

        nextGameDate.setText(String.valueOf(closestDate).substring(0,2));

        String month = String.valueOf(closestDate).substring(4,5);
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
