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
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 02/03/2018.
 *
 * In the Intent that called this activity, you must put the MEMBERID of the member whose profile you wish to see
 */

public class ProfileActivity extends AppCompatActivity {

    TextView name, teamName, email, positionsPlayed, responsibilities;
    ImageView profilePicture;
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

        profilePicture = findViewById(R.id.imageView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = profilePicture.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/4;
        profilePicture.setLayoutParams(layoutParams);
        profilePicture.setVisibility(View.VISIBLE);

        back = findViewById(R.id.back_button);
        addPos = findViewById(R.id.addPosition);
        addRes = findViewById(R.id.addResponsibilty);

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

        Intent activityThatCalled = getIntent();
        thisID = (String) activityThatCalled.getSerializableExtra("MemberID");



        setProfile();

        //TODO set it so only if this profile is YOUR profile, show the add new buttons
        //TODO add new positions and responsibilities to database

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
            email.setText(profileDetails[2][0]);
        }
        if(profileDetails[7][0]!= null) {
            teamName.setText(profileDetails[7][0]);
        }
        if(profileDetails[5][0]!= null) {
            positionsPlayed.setText(profileDetails[5][0]);
        }
        if(profileDetails[6][0]!= null && !profileDetails[6][0].equals("None")) {
            responsibilities.setText(profileDetails[6][0]);
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

                String allPositions = positionsPlayed.getText().toString();

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

                String allRes = responsibilities.getText().toString();

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
