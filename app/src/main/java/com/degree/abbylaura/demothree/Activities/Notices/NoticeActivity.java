package com.degree.abbylaura.demothree.Activities.Notices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class NoticeActivity extends Activity {

    TextView usersMessage;
    RelativeLayout relBottom;
    ArrayList<String> noticeBuffer;

    int noticeIdNum;
    String winningNoticeId;


    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = findViewById(R.id.input_text_view);

        this.noticeIdNum = 0;
        this.winningNoticeId = "";

        noticeBuffer = new ArrayList<>();

        updateContent(null);


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


        /* Allows use to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);

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
        draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_person_black_48dp);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);

        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_note_add_black_48dp);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                barSize, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    public void onComposeNotice(View view) {
        //when this is clicked, we want to go to D2NoticeComposeActivity
        //should return with activity name and notice content

        Intent getReturnUserInput = new Intent(this, NoticeComposeActivity.class);


        startActivity(getReturnUserInput);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle text being sent back to from D2NoticeActivity
        String composeText = data.getStringExtra("User Input");
        System.out.println("return from compose: " + composeText);

        updateContent(composeText);
    }

    public void updateContent(String addition) {
        System.out.println("updateContent: " + addition);

        //add addition to Database Notice table
        NoticeRepo noticeRepo = new NoticeRepo();

        if(addition != null){
            Date date = Calendar.getInstance().getTime();

            noticeBuffer.add(MyClientID.myID + "4h4f" + addition + "4h4f" + date.toString());

        }

        sendBufferToServer();

    }

    private void sendBufferToServer(){
        System.out.println("sendBufferToServer: " + noticeBuffer.toString());

        System.out.println(noticeBuffer.toString());
        Intent intent = new Intent(this, NetworkService.class);

        NoticeRepo noticeRepo = new NoticeRepo();

        if(noticeBuffer.isEmpty()){
            System.out.println("sendBufferToServer: CODE:4698:EMPTYBUFFER");

            noticeBuffer.add("CODE:4698:EMPTYBUFFER"); //Unique string that user will not input
        }
        intent.putExtra("TABLESIZE", String.valueOf(noticeRepo.tableSize()));

        intent.putExtra("CLASS", noticeBuffer);
        intent.putExtra("typeSending", "NOTICE");
        this.startService(intent);

    }

    /*
     * Is alerted when the IntentService broadcasts TRANSACTION_DONE
     */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            //NoticeActivity.this.unregisterReceiver(this);
            System.out.println("onReceive BroadcastReceiver");

            emptyNoticeBuffer();



            Log.e("NetworkService", "Service Received");

            //updateDB(response);

            updateUI();


        }

    };


    public void updateUI(){
        System.out.println("updateUI");

        if(!noticeBuffer.isEmpty()){
            if(noticeBuffer.get(0).equals("CODE:4698:EMPTYBUFFER")){
                noticeBuffer = new ArrayList<>();
            }
        }

        //for all entries in Notice table not on UI, add to UI using updateTextView
        NoticeRepo noticeRepo = new NoticeRepo();
        ArrayList<ArrayList<String>> noticeArray = noticeRepo.getNotices();

        LinearLayout fragContainer = findViewById(R.id.fragmentContainer);
        if(fragContainer != null){
            fragContainer.removeAllViews();
        }

        int colour = 0;

        Collections.reverse(noticeArray);//because newer notices need to be added last
        String winningID;

        for(int i = 0; i < noticeArray.size(); i++){


            ArrayList<String> row = noticeArray.get(i);

            String memberName = row.get(0);
            String noticeContent = row.get(1);
            String noticeDate = row.get(2);
            if(Integer.parseInt(row.get(3)) > noticeIdNum){
                noticeIdNum = Integer.parseInt(row.get(3));
                winningID = row.get(3);
            }

            System.out.println(memberName + " o " + noticeContent + " o " + noticeDate);

            colour++;

            updateTextView(fragContainer, noticeContent, memberName, noticeDate, colour);
        }


    }

    /*
     * Called from updateUI;
     */
    @SuppressLint("ResourceType")
    protected void updateTextView(LinearLayout fragContainer, String notice, String memberName, String date, int i){
        System.out.print("in update text view");
        fragContainer.removeAllViews();
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setId(12345);

        FragmentTransaction ft =  getFragmentManager().beginTransaction();
        ft.add(layout.getId(), NoticeFragment.newInstance(notice, memberName, date, i), "someTag1").commit();

        fragContainer.addView(layout);
    }

    public void onBackButton(View view) {
        //go back to the activity that called it in the first place
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void emptyNoticeBuffer(){
        this.noticeBuffer = new ArrayList<>();
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
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);
    }

}
