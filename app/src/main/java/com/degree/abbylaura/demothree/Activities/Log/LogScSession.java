package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 23/03/2018.
 */

public class LogScSession extends Activity {

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;
    Spinner sessionSpinner;
    String selectedDate, selectedID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_sc_session);
        selectedDate = "";
        selectedID = "";
        sessionSpinner = findViewById(R.id.sessionSpinner);
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
        setSpinner();
        //setPermLayout();
    }

    private void setPermLayout(){
        LinearLayout platform = findViewById(R.id.platform);


    }

    private void setLayout(){

        String[] scExercises = new String[13];
        scExercises[0] = "Deadlifts";
        scExercises[1] = "Deadlift Jumps";
        scExercises[2] = "Back Squat";
        scExercises[3] = "Back Squat Jumps";
        scExercises[4] = "Goblet Squat";
        scExercises[5] = "Bench Press";
        scExercises[6] = "Military Press";
        scExercises[7] = "Supine Row";
        scExercises[8] = "Chin Ups";
        scExercises[9] = "Trunk";
        scExercises[10] = "RDL";
        scExercises[11] = "Split Squat";
        scExercises[12] = "Four Way Arms";

        LinearLayout platform = findViewById(R.id.platform);
        platform.removeAllViews();

        SessionRepo sessionRepo = new SessionRepo();
        ArrayList<String> sessionFormat = sessionRepo.getSessionExercises(selectedID);


        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;


        for(int i = 0; i < sessionFormat.size(); i++){
            if(!sessionFormat.get(i).equals("null")){
                String[] splitter = sessionFormat.get(i).split(":");
                String amount = splitter[0] + "x" + String.valueOf(splitter.length);
                if(amount.equals("14h4fx1") || amount.equals("null4h4fx1")){
                    System.out.println(amount);
                }
                else if(!(amount.contains("null")) || !(amount.contains("4h4f")) || !(amount.contains("null4h4f")) || !(amount.contains("14h4f")) || splitter.length < 2){


                    LinearLayout exerciseBox = new LinearLayout(this);
                    exerciseBox.setOrientation(LinearLayout.HORIZONTAL);
                    TextView tv = new TextView(this);
                    tv.setText(scExercises[i] + ": " + amount);
                    exerciseBox.addView(tv);

                    for(int j = 0; j < splitter.length; j++){
                        EditText input = new EditText(this);
                        input.setWidth(screenWidth/10);
                        exerciseBox.addView(input);
                    }

                    platform.addView(exerciseBox);
                }
            }
        }

    }


    private void setSpinner(){
        StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
        String[][] scData = scRepo.getTableData();
        ArrayList<String> sessionDates = new ArrayList<>();

        for(int i = 0; i < scData[0].length; i++){
            sessionDates.add(scData[0][i] + ": " + scData[1][i]);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, sessionDates);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sessionSpinner.setAdapter(adapter);


        sessionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate = (sessionSpinner.getSelectedItem().toString());
                System.out.println("spinner selected: " + selectedDate);
                String[] splitter = selectedDate.split(":");
                selectedID = splitter[0];
                setLayout();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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
