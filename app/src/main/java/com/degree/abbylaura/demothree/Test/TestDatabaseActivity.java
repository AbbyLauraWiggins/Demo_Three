package com.degree.abbylaura.demothree.Test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.SCsessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
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

    Button showMembersButton, showTeamsButton, showSessionsButton, back;

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_database_activity);

        showMembersButton = (Button) findViewById(R.id.showMembersButton);
        showSessionsButton = (Button) findViewById(R.id.showSessionsButton);
        showTeamsButton = (Button) findViewById(R.id.showTeamsButton);
        back = (Button) findViewById(R.id.back);

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
        member.setDOB("20/12/1996");
        member.setPositions("1");
        member.setResponsibilities("Development officer");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("2");
        member.setName("Billie Smith");
        member.setEmail("bxs@email.com");
        member.setPassword("0002");
        member.setDOB("10/02/1996");
        member.setPositions("4, 5");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("3");
        member.setName("Cathy Terry");
        member.setEmail("cxt@email.com");
        member.setPassword("0003");
        member.setDOB("01/01/1997");
        member.setPositions("2");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        memberRepo.insert(member);

        member.setMemberId("4");
        member.setName("Den Florent");
        member.setEmail("dxf@email.com");
        member.setPassword("0004");
        member.setDOB("05/07/1994");
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


        team.setTeamId("LURFC_1");
        team.setTeamName("LURFC");
        team.setTeamLocation("LB1 56T");
        team.setTeamCurPoints("131");
        teamRepo.insert(team);

        team.setTeamId("AUWRFC_1");
        team.setTeamName("AUWRFC");
        team.setTeamLocation("B1 8LM");
        team.setTeamCurPoints("109");
        teamRepo.insert(team);

        StrengthAndConditioning sc = new StrengthAndConditioning();

        sc.setSessionID("1");
        sc.setSessionDate("04/03/2018");
        sc.setSessionTime("1615");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("2");
        sc.setSessionDate("07/03/2018");
        sc.setSessionTime("1715");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("3");
        sc.setSessionDate("10/03/2018");
        sc.setSessionTime("1700");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("4");
        sc.setSessionDate("13/03/2018");
        sc.setSessionTime("1600");
        strengthAndConditioningRepo.insert(sc);

        sc.setSessionID("5");
        sc.setSessionDate("20/03/2018");
        sc.setSessionTime("1615");
        strengthAndConditioningRepo.insert(sc);


        TeamFixtures tf = new TeamFixtures();

        tf.setFixtureId("1");
        tf.setFixtureDate("02/01/2018");
        tf.setFixtureLocation("B29 2TT");

        tf.setFixtureId("2");
        tf.setFixtureDate("12/01/2018");
        tf.setFixtureLocation("NG34 0RJ");

        tf.setFixtureId("3");
        tf.setFixtureDate("22/02/2018");
        tf.setFixtureLocation("B29 2TT");

        tf.setFixtureId("4");
        tf.setFixtureDate("08/03/2018");
        tf.setFixtureLocation("LN4 4AE");

        tf.setFixtureId("5");
        tf.setFixtureDate("18/02/2018");
        tf.setFixtureLocation("B15 2QW");

        tf.setFixtureId("6");
        tf.setFixtureDate("02/04/2018");
        tf.setFixtureLocation("B15 2QW");


        Fixture fixtures = new Fixture();

        fixtures.setFixtureId("1"); //Fixture 1
        fixtures.setTeamId("1");    //Team UOBWRFC
        fixtures.setFixturePoints("22"); //Points scored by UOBWRFC

        fixtures.setFixtureId("1"); //Fixture 1
        fixtures.setTeamId("2");    //Team LURFC
        fixtures.setFixturePoints("20"); //Points scored by LURFC

        fixtures.setFixtureId("2"); //Fixture 2
        fixtures.setTeamId("1");    //Team UOBWRFC
        fixtures.setFixturePoints("87"); //Points scored by UOBWRFC

        fixtures.setFixtureId("2"); //Fixture 2
        fixtures.setTeamId("3");    //Team AURFC
        fixtures.setFixturePoints("17"); //Points scored by AURFC

        fixtures.setFixtureId("3"); //Fixture 3
        fixtures.setTeamId("2");    //Team LURFC
        fixtures.setFixturePoints("44"); //Points scored by LURFC

        fixtures.setFixtureId("3"); //Fixture 3
        fixtures.setTeamId("3");    //Team AURFC
        fixtures.setFixturePoints("20"); //Points scored by AURFC

        fixtures.setFixtureId("4"); //Fixture 4
        fixtures.setTeamId("1");    //Team UOBWRFC
        fixtures.setFixturePoints(null); //null points as game not occured yet

        fixtures.setFixtureId("4"); //Fixture 4
        fixtures.setTeamId("2");    //Team LURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet

        fixtures.setFixtureId("5"); //Fixture 5
        fixtures.setTeamId("3");    //Team AURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet

        fixtures.setFixtureId("5"); //Fixture 5
        fixtures.setTeamId("1");    //Team UOBWRFC
        fixtures.setFixturePoints(null); //null points as game not occured yet

        fixtures.setFixtureId("6"); //Fixture 6
        fixtures.setTeamId("3");    //Team AURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet

        fixtures.setFixtureId("6"); //Fixture 1
        fixtures.setTeamId("2");    //Team LURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet






    }

    public void showSessions(View view) {
    }

    public void showTeams(View view) {
    }

    @SuppressLint("SetTextI18n")
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
                label.setText(" | " + allMembers[col][row]);
                tr.addView(label);
            }

            tl.addView(tr);
        }

        LinearLayout tableContainer = (LinearLayout) findViewById(R.id.tableContainer);

        tableContainer.addView(tl);
    }

    public void goback(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void showAll(View view) {
        showMembers(view);

        FixtureRepo fixtureRepo = new FixtureRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();

        showTable(view, fixtureRepo.getTableData());
        showTable(view, strengthAndConditioningRepo.getTableData());
        showTable(view, teamFixturesRepo.getTableData());
        showTable(view, teamRepo.getTableData());

    }

    public void showTable(View view, String[][] tableData) {


        String output = "";

        TableLayout tl = new TableLayout(this);

        for(int row = 0; row < tableData[0].length; row++) {
            TableRow tr = new TableRow(this);

            for(int col = 0; col < 8; col++){
                System.out.println(String.valueOf(row) + " " +
                        String.valueOf(col) + " " + tableData[col][row]);
                TextView label = new TextView(this);
                label.setText(" | " + tableData[col][row]);
                tr.addView(label);
            }

            tl.addView(tr);
        }

        LinearLayout tableContainer = (LinearLayout) findViewById(R.id.tableContainer);

        tableContainer.addView(tl);
    }
}
