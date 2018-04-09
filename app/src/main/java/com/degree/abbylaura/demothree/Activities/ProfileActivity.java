package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 02/03/2018.
 *
 * In the Intent that called this activity, you must put the MEMBERID of the member whose profile you wish to see
 */

public class ProfileActivity extends Activity{

    TextView name, teamName, email, dob, positionsPlayed, responsibilities;
    LinearLayout  teamNameLL, emailLL, dobLL, positionsLL, responsibilitiesLL;
    ImageView profilePicture, addResponsibility, addPosition;
    private String thisID;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;



    Button back, addPos, addRes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        name = findViewById(R.id.member_name);
        teamName = findViewById(R.id.team_name);
        email = findViewById(R.id.email);
        positionsPlayed = findViewById(R.id.positions_played);
        responsibilities = findViewById(R.id.responsibilities);
        dob = findViewById(R.id.dob);
        teamNameLL = findViewById(R.id.teamNameLL);
        emailLL = findViewById(R.id.emailLL);
        positionsLL = findViewById(R.id.positionsLL);
        responsibilitiesLL = findViewById(R.id.responsibilitiesLL);
        addPosition = findViewById(R.id.addPositionIV);
        addResponsibility = findViewById(R.id.addResponsibilty);
        dobLL = findViewById(R.id.dobLL);

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
        setLayout();

        Intent activityThatCalled = getIntent();
        thisID = MyClientID.myID;//(String) activityThatCalled.getSerializableExtra("MemberID");



        setProfile();

        //TODO set it so only if this profile is YOUR profile, show the add new buttons
        //TODO add new positions and responsibilities to database

    }

    private void setLayout(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams;

        layoutParams = teamNameLL.getLayoutParams();
        layoutParams.width = screenWidth - 10;
        layoutParams.height = screenHeight/10;
        teamNameLL.setLayoutParams(layoutParams);

        layoutParams = emailLL.getLayoutParams();
        layoutParams.width = screenWidth - 10;
        layoutParams.height = screenHeight/10;
        emailLL.setLayoutParams(layoutParams);

        layoutParams = dobLL.getLayoutParams();
        layoutParams.width = screenWidth - 10;
        layoutParams.height = screenHeight/10;
        dobLL.setLayoutParams(layoutParams);

        layoutParams = responsibilitiesLL.getLayoutParams();
        layoutParams.width = screenWidth - 10;
        layoutParams.height = screenHeight/10;
        responsibilitiesLL.setLayoutParams(layoutParams);

        layoutParams = positionsLL.getLayoutParams();
        layoutParams.width = screenWidth - 10;
        layoutParams.height = screenHeight/10;
        positionsPlayed.setLayoutParams(layoutParams);




        barSize = screenHeight/15;

        addResponsibility.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.addbtn);
        draw = barresize(draw);
        addResponsibility.setImageDrawable(draw);

        addPosition.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.addbtn);
        draw = barresize(draw);
        addPosition.setImageDrawable(draw);


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



    public void setProfile(){
        Member member = new Member();
        MemberRepo memberRepo = new MemberRepo();

        String where = " WHERE " + Member.TABLE + "." + Member.KEY_MemberId + "='" + thisID + "'";

        memberRepo.setWhereClause(where);

        String[][] profileDetails = memberRepo.getMembers();

        if(profileDetails[1][0]!= null){
            name.setText(profileDetails[1][0]);
        }
        if(profileDetails[2][0]!= null){
            email.setText("Email: " + profileDetails[2][0]);
        }
        if(profileDetails[4][0]!= null){
            dob.setText("Date of birth: " + profileDetails[2][0]);
        }
        if(profileDetails[7][0]!= null) {
            TeamRepo teamRepo = new TeamRepo();
            teamName.setText("Team: " + teamRepo.getTeam(profileDetails[7][0]).get(0));
        }
        if(profileDetails[5][0]!= null) {
            positionsPlayed.setText("Positions: " + profileDetails[5][0]);
        }
        if(profileDetails[6][0]!= null && !profileDetails[6][0].equals("None")) {
            responsibilities.setText("Responsibilities: " + profileDetails[6][0]);
        }


    }




    public void addPosition(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Add New Position");
        alert.setMessage("Add the position number and click 'done'.");

        final EditText input = new EditText(this);
        alert.setView(input);



        alert.setPositiveButton("Done", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                positionsPlayed.append(input.getText().toString() + " \n ");
                MemberRepo memberRepo = new MemberRepo();

                String allPositions = positionsPlayed.getText().toString().substring(10);

                memberRepo.updatePosition(MyClientID.myID, allPositions);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();

    }

    public void addResponsibility(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Add New Responsibility");
        alert.setMessage("Input your responsibility or role and click 'done'.");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                responsibilities.append(input.getText().toString() + "\n");
                MemberRepo memberRepo = new MemberRepo();

                String allRes = responsibilities.getText().toString().substring(18);

                memberRepo.updateResponsibility(MyClientID.myID, allRes);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();



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
