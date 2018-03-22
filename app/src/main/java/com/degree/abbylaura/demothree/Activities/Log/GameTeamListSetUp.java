package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class GameTeamListSetUp extends Activity {

    Button upload, create, createAndLog;
    HashMap<Integer, String> playerAssignment;
    TextView fixtureDate;
    String fixtureID;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gam_team_list_setup_activity);

        upload = findViewById(R.id.uploadTeamSheet);
        create = findViewById(R.id.createTeamSheet);
        createAndLog = findViewById(R.id.createAndLog);
        fixtureDate = findViewById(R.id.nextFixtureDate);

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        setBottomBar();

        try {
            getFixtureDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        playerAssignment = new HashMap<>();

    }


    private void setBottomBar(){

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        android.view.ViewGroup.LayoutParams layoutParams = bbl.getLayoutParams();
        layoutParams.width = screenWidth + 30;
        layoutParams.height = screenHeight/10;
        bbl.setLayoutParams(layoutParams);

        layoutParams = homebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        homebbll.setLayoutParams(layoutParams);

        layoutParams = noticebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        noticebbll.setLayoutParams(layoutParams);

        layoutParams = profilebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        profilebbll.setLayoutParams(layoutParams);

        layoutParams = logbbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        logbbll.setLayoutParams(layoutParams);

        barSize = screenHeight/12;
        barNotice.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barNotice.setImageDrawable(draw);

        barHome.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_home_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.profileiconempty);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);

        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.trend_arrow);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

    }


    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        float scaleFactor = width/height;
        int setwidth = (int) (barSize * scaleFactor);
        System.out.println(height + " " + width + " " + setwidth);
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                setwidth, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    public void onCreateClick(View view) {
        upload.setVisibility(View.INVISIBLE);

        LinearLayout ll = findViewById(R.id.ll);
        ll.removeAllViews();

        TableLayout tl = new TableLayout(this);

        for (int i = 1; i < 23; i++) {
            TableRow tr = new TableRow(this);
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(i));
            tv.setTextSize(20);

            tr.addView(tv);

            Spinner memberSpinner = new Spinner(this);
            setSpinnerContents(memberSpinner, i);

            tr.addView(memberSpinner);

            tl.addView(tr);

        }

        ll.addView(tl);

        createAndLog.setVisibility(View.VISIBLE);

    }

    private void setSpinnerContents(Spinner memberSpinner, final int pos){
        MemberRepo memberRepo = new MemberRepo();
        String[][] memberTable = memberRepo.getMembers();

        ArrayList<String> memberList = new ArrayList<>();
        HashMap<String, String> memberHash = new HashMap<>();

        for(int i = 0; i < memberTable[0].length; i++){
            memberList.add(memberTable[1][i]);
            memberHash.put(memberTable[1][i], memberTable[0][i]); //HashMap<MemberName, MemberId>
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, memberList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberSpinner.setAdapter(adapter);

        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                playerAssignment.put(pos, adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onCreateAndLogClick(View view) {
        Intent intent = new Intent(getApplicationContext(), LogGameStats.class);
        intent.putExtra("PLAYERS", playerAssignment);
        intent.putExtra("FIXTUREID", fixtureID);
        startActivity(intent);
    }

    public void getFixtureDate() throws ParseException {
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date farAway = sdf.parse("01/01/3018");

        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date

            //must be one my MyTeams games
            if(row.get(4).equals(MyClientID.myTeamID) || row.get(5).equals(MyClientID.myTeamID)){
                //must be a game that has NOT occured

                try {
                    Date fixtureDate = sdf.parse(row.get(2));
                    if (new Date().before(fixtureDate)) { //if NOW is before fixtureDate
                        if(fixtureDate.before(farAway)){ //if it closer to now than current closest date
                            farAway = fixtureDate;
                            fixtureID = row.get(0);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

        String fixtureDateToPass = sdf.format(farAway);
        fixtureDate.setText(fixtureDateToPass);
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onNoticeButtonClick(View view) {
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }

    public void onProfileButtonClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onLogButtonClick(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }


}
