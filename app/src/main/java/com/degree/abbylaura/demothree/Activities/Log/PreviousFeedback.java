package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.app.FragmentTransaction;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Members.MemberFragment;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.Selection;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FeedbackRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.Feedback;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class PreviousFeedback extends Activity {

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;
    ArrayList<String> playerNames, memberIDs, spinnerList, fixtureIDs;
    Spinner fixtureSpinner;
    String fixtureID, spinnerText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fixtureID = "";
        spinnerText = "";
        fixtureIDs = new ArrayList<>();
        playerNames = new ArrayList<>();
        memberIDs = new ArrayList<>();

        setContentView(R.layout.previous_feedback_activity);
        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        fixtureSpinner = findViewById(R.id.fixtureSpinner);
        setFixtureSpinner();

        setBottomBar();
    }

    private void setFixtureSpinner(){
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        final ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();
        spinnerList = new ArrayList<String>();
        final HashMap<String, String> spinnerItemAndFixtureID = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        spinnerList.add("All");

        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date

            //must be one my MyTeams games
            if(row.get(4).equals(MyClientID.myTeamID) || row.get(5).equals(MyClientID.myTeamID)){
                //must be a game that has already occured

                try {
                    Date fixtureDate = sdf.parse(row.get(2));
                    if (new Date().after(fixtureDate)) {
                        String item = row.get(0) + " vs " + row.get(1) + ": " + row.get(2);
                        String fixtureID = row.get(3);
                        fixtureIDs.add(fixtureID);
                        spinnerList.add(item);
                        spinnerItemAndFixtureID.put(item, fixtureID);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner fixtureSpinner = (Spinner) findViewById(R.id.fixtureSpinner);
        fixtureSpinner.setAdapter(adapter);

        fixtureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(i).toString();
                if(itemSelectedInSpinner.equals("All")){
                    fixtureID = "All";
                }else {
                    fixtureID = (spinnerItemAndFixtureID.get(itemSelectedInSpinner));
                }
                spinnerText = itemSelectedInSpinner;
                System.out.println(fixtureID + " " + spinnerItemAndFixtureID.get(itemSelectedInSpinner));

                showFeedback();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showFeedback() {
        if (playerNames.isEmpty()) {
            Toast.makeText(this, "Please select players.", Toast.LENGTH_SHORT).show();
        } else if (fixtureID.equals("")) {
            fixtureID = "All";
        }


        if (!fixtureID.equals("All")) {
            showFragment();
        } else{
            for(String f: fixtureIDs){
                fixtureID = f;
                showFragment();
            }
        }
    }

    private void showFragment(){
        FeedbackRepo feedbackRepo = new FeedbackRepo();
        LinearLayout fragContainer = findViewById(R.id.feedbackLL);


        for(int i = 0; i < memberIDs.size(); i++){
            System.out.println(memberIDs + " < member ids");

            String name, feedbackText, attackRate, defRate, effortRate, overallRate;
            ArrayList<String> feedbackLst = feedbackRepo.getFeedback(memberIDs.get(i), fixtureID);

            if(!feedbackLst.isEmpty()){
                System.out.println("feedback list not empty");
                name = playerNames.get(i);
                feedbackText = feedbackLst.get(0);
                attackRate = String.valueOf(feedbackLst.get(1));
                defRate = String.valueOf(feedbackLst.get(2));
                effortRate = String.valueOf(feedbackLst.get(3));
                overallRate = String.valueOf(feedbackLst.get(4));

                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setId(2);


                FragmentTransaction ft =  getFragmentManager().beginTransaction();

                ft.add(layout.getId(), FeedbackFragment.newInstance(
                        name, spinnerText, feedbackText, attackRate, defRate, effortRate, overallRate),
                        "tag").commit();

                fragContainer.addView(layout);

            }else{
                System.out.println("feedbackLst is empty");
                System.out.println("playerNames: " + playerNames);
                System.out.println("memberIDs: " + memberIDs);
                System.out.println("fixtureIds: " + fixtureIDs);

                String[][] table = feedbackRepo.getTable();
                for(int k = 0; k < table[0].length; k++){
                    for(int j = 0; j < 7; j++){
                        System.out.print(table[j][k] + " ");
                    }
                    System.out.println("");
                }


            }

        }

    }

    public void onSelectPlayers(View view) {
        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("players", true);
        showList.putExtra("indicator", "");
        startActivityForResult(showList, 1);
        showFeedback();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String returnType = data.getStringExtra("indicator");
        System.out.println("return type: " + returnType);

        if(returnType.equals("")){
            playerNames = data.getStringArrayListExtra("selected");
            memberIDs = data.getStringArrayListExtra("memberIDs");
        }

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
