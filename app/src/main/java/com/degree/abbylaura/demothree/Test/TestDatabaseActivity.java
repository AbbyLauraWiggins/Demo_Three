package com.degree.abbylaura.demothree.Test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.SCsessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.SCsession;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TestDatabaseActivity extends Activity {

    Button showMembersButton, showTeamsButton, showSessionsButton;

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_database_activity);

        showMembersButton = (Button) findViewById(R.id.showMembersButton);
        showSessionsButton = (Button) findViewById(R.id.showSessionsButton);
        showTeamsButton = (Button) findViewById(R.id.showTeamsButton);

        editText = (EditText) findViewById(R.id.editText);

        insertTestData();



    }


    private void insertTestData(){
        FixtureRepo fixtureRepo = new FixtureRepo();
        MemberRepo memberRepo   = new MemberRepo();
        SCsessionRepo sCsessionRepo = new SCsessionRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();


        fixtureRepo.delete();
        memberRepo.delete();
        sCsessionRepo.delete();
        strengthAndConditioningRepo.delete();
        teamFixturesRepo.delete();
        teamRepo.delete();

        Member member = new Member();

        member.setMemberId("1");
        member.setName("Abby Wiggins");
        member.setEmail("axw@email.com");
        member.setPassword("0001");
        member.setDOB("20/12/96");
        member.setPositions("1");
        member.setResponsibilities("Development officer");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("2");
        member.setName("Billie Smith");
        member.setEmail("bxs@email.com");
        member.setPassword("0002");
        member.setDOB("10/02/96");
        member.setPositions("4, 5");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("3");
        member.setName("Cathy Terry");
        member.setEmail("cxt@email.com");
        member.setPassword("0003");
        member.setDOB("01/01/97");
        member.setPositions("2");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("4");
        member.setName("Den Florent");
        member.setEmail("dxf@email.com");
        member.setPassword("0004");
        member.setDOB("05/07/94");
        member.setPositions("12, 13");
        member.setResponsibilities("Social Secretary");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);



        Team team = new Team();

        team.setTeamId("UOBWRFC_1");
        team.setTeamName("UOBWRFC");
        team.setTeamLocation("B29 2TT");
        team.setTeamCurPoints("149");
        teamRepo.insert(team);


        StrengthAndConditioning sc = new StrengthAndConditioning();

        sc.setSessionID("1");
        sc.setSessionDate("04/03/18");
        sc.setSessionTime("1615");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("2");
        sc.setSessionDate("07/03/18");
        sc.setSessionTime("1715");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("3");
        sc.setSessionDate("10/03/18");
        sc.setSessionTime("1700");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("4");
        sc.setSessionDate("13/03/18");
        sc.setSessionTime("1600");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("5");
        sc.setSessionDate("20/03/18");
        sc.setSessionTime("1615");
        strengthAndConditioningRepo.insert(sc);







    }

    public void showSessions(View view) {
    }

    public void showTeams(View view) {
    }

    public void showMembers(View view) {
        MemberRepo memberRepo = new MemberRepo();

        String[][] allMembers = memberRepo.getMembers();

        String output = "";

        TableLayout tl = new TableLayout(this);

        for(int row = 0; row < allMembers[0].length; row++) {
            TableRow tr = new TableRow(this);

            for(int col = 0; col < 8; col++){
                System.out.println(String.valueOf(row) + " " +
                        String.valueOf(col) + " " + allMembers[col][row]);
                TextView label = new TextView(this);
                label.setText(allMembers[col][row]);
                tr.addView(label);
            }

            tl.addView(tr);
        }

        LinearLayout tableContainer = (LinearLayout) findViewById(R.id.tableContainer);

        tableContainer.addView(tl);
    }
}
