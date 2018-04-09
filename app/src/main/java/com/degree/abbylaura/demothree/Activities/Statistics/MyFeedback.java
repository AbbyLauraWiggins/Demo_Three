package com.degree.abbylaura.demothree.Activities.Statistics;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.FeedbackFragment;
import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FeedbackRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

public class MyFeedback extends Activity {

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int barSize;
    ButtonBarLayout bbl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_feedback_activity);

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
        updateFromServer();
        showFeedback();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_UPDATEFEEDBACK);
        registerReceiver(clientReceiver, intentFilter);

    }

    private void showFeedback(){
        FeedbackRepo feedbackRepo = new FeedbackRepo();
        LinearLayout fragContainer = findViewById(R.id.feedbackLL);
        String name, feedbackText, attackRate, defRate, effortRate, overallRate;
        ArrayList<ArrayList<String>> feedback = feedbackRepo.getMyFeedback(MyClientID.myID);

        TeamFixturesRepo tfRepo = new TeamFixturesRepo();


        if(!feedback.isEmpty()){
            System.out.println("feedback list not empty");

            for(ArrayList<String> feedbackLst : feedback){
                MemberRepo memberRepo = new MemberRepo();
                ArrayList<String> getmyname = new ArrayList<>();
                getmyname.add(MyClientID.myID);
                name = memberRepo.getNames(getmyname).get(0);


                feedbackText = feedbackLst.get(0);
                attackRate = String.valueOf(feedbackLst.get(1));
                defRate = String.valueOf(feedbackLst.get(2));
                effortRate = String.valueOf(feedbackLst.get(3));
                overallRate = String.valueOf(feedbackLst.get(4));

                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setId(3);

                String spinnerText = tfRepo.getFixText(feedbackLst.get(5));

                FragmentTransaction ft =  getFragmentManager().beginTransaction();

                ft.add(layout.getId(), FeedbackFragment.newInstance(
                        name, spinnerText, feedbackText, attackRate, defRate, effortRate, overallRate),
                        "tag").commit();

                fragContainer.addView(layout);

                System.out.println(name + " | " + spinnerText + " | " + feedbackText + " | " +
                        attackRate + " | " + defRate + " | " + effortRate + " | " + overallRate);
            }
        }else{
            System.out.println("feedback list empty");
        }
    }

    private void updateFromServer(){
        FeedbackRepo feedbackRepo = new FeedbackRepo();
        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("TABLESIZE", String.valueOf(feedbackRepo.getTableSize()));

        if(MyClientID.myPermissions < 2){ //THEN I ONLY GET MY FEEDBACK
            intent.putExtra("typeSending", "updateBasicFEEDBACK");
            this.startService(intent);
        }else{ //I GET EVERYONES FEEDBACK
            intent.putExtra("typeSending", "updateAdvancedFEEDBACK");
            this.startService(intent);
        }

    }

    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive BroadcastReceiver: feedback updated");
        }

    };

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

    protected void onPause() {
        super.onPause();
        System.out.println("onPause BroadcastReceiver");

        unregisterReceiver(clientReceiver);
    }

    protected void onResume() {
        super.onResume();
        System.out.println("onResume BroadcastReceiver");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_NOTICE);
        registerReceiver(clientReceiver, intentFilter);
    }
}
