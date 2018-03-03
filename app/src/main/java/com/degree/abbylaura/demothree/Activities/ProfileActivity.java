package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;
import com.degree.abbylaura.demothree.Test.TestDatabaseActivity;

/**
 * Created by abbylaura on 02/03/2018.
 *
 * In the Intent that called this activity, you must put the MEMBERID of the member whose profile you wish to see
 */

public class ProfileActivity extends AppCompatActivity {

    TextView name, teamName, email, positionsPlayed, responsibilities;
    ImageView profilePicture;
    private String thisID;


    Button back, addPos, addRes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        name = (TextView) findViewById(R.id.member_name);
        teamName = (TextView) findViewById(R.id.team_name);
        email = (TextView) findViewById(R.id.email);
        positionsPlayed = (TextView) findViewById(R.id.positions_played);
        responsibilities = (TextView) findViewById(R.id.responsibilities);

        profilePicture = (ImageView) findViewById(R.id.imageView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = profilePicture.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/4;
        profilePicture.setLayoutParams(layoutParams);
        profilePicture.setVisibility(View.VISIBLE);

        back = (Button) findViewById(R.id.back_button);
        addPos = (Button) findViewById(R.id.addPosition);
        addRes = (Button) findViewById(R.id.addResponsibilty);

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

    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }
}
