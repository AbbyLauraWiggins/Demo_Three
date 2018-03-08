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
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Repo.SCsessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.KPI;
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

        showMembersButton = findViewById(R.id.showMembersButton);
        showSessionsButton = findViewById(R.id.showSessionsButton);
        showTeamsButton = findViewById(R.id.showTeamsButton);
        back = findViewById(R.id.back);

        editText = findViewById(R.id.editText);

        insertTestData();

    }

    private void insertTestData(){
        FixtureRepo fixtureRepo = new FixtureRepo();
        MemberRepo memberRepo   = new MemberRepo();
        SCsessionRepo sCsessionRepo = new SCsessionRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();
        KPIRepo kpiRepo = new KPIRepo();
        NoticeRepo noticeRepo = new NoticeRepo();


        fixtureRepo.delete();
        memberRepo.delete();
        sCsessionRepo.delete();
        strengthAndConditioningRepo.delete();
        teamFixturesRepo.delete();
        teamRepo.delete();
        kpiRepo.delete();
        noticeRepo.delete();

        Member member = new Member();

        member.setMemberId("1");
        member.setName("Abby Wiggins");
        member.setEmail("axw@email.com");
        member.setPassword("0001");
        member.setDOB("20/12/1996");
        member.setPositions("1");
        member.setResponsibilities("Development officer");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("LEADER");
        memberRepo.insert(member);


        member.setMemberId("2");
        member.setName("Billie Smith");
        member.setEmail("bxs@email.com");
        member.setPassword("0002");
        member.setDOB("10/02/1996");
        member.setPositions("4, 5");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("3");
        member.setName("Cathy Terry");
        member.setEmail("cxt@email.com");
        member.setPassword("0003");
        member.setDOB("01/01/1997");
        member.setPositions("2");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("ADMIN");
        memberRepo.insert(member);

        member.setMemberId("4");
        member.setName("Den Florent");
        member.setEmail("dxf@email.com");
        member.setPassword("0004");
        member.setDOB("05/07/1994");
        member.setPositions("12, 13");
        member.setResponsibilities("Social Secretary");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("TOP");
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

        team.setTeamId("AURFC_1");
        team.setTeamName("AURFC");
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
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("2");
        tf.setFixtureDate("12/01/2018");
        tf.setFixtureLocation("NG34 0RJ");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("3");
        tf.setFixtureDate("22/02/2018");
        tf.setFixtureLocation("B29 2TT");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("4");
        tf.setFixtureDate("08/03/2018");
        tf.setFixtureLocation("LN4 4AE");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("5");
        tf.setFixtureDate("18/02/2018");
        tf.setFixtureLocation("B15 2QW");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("6");
        tf.setFixtureDate("02/04/2018");
        tf.setFixtureLocation("B15 2QW");
        teamFixturesRepo.insert(tf);


        Fixture fixtures = new Fixture();

        fixtures.setFixtureId("1"); //Fixture 1
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints("22"); //Points scored by UOBWRFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("1"); //Fixture 1
        fixtures.setTeamId("LURFC_1");    //Team LURFC
        fixtures.setFixturePoints("20"); //Points scored by LURFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("2"); //Fixture 2
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints("87"); //Points scored by UOBWRFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("2"); //Fixture 2
        fixtures.setTeamId("AURFC_1");    //Team AURFC
        fixtures.setFixturePoints("17"); //Points scored by AURFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("3"); //Fixture 3
        fixtures.setTeamId("LURFC_1");    //Team LURFC
        fixtures.setFixturePoints("44"); //Points scored by LURFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("3"); //Fixture 3
        fixtures.setTeamId("AURFC_1");    //Team AURFC
        fixtures.setFixturePoints("20"); //Points scored by AURFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("4"); //Fixture 4
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("4"); //Fixture 4
        fixtures.setTeamId("LURFC_!");    //Team LURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("5"); //Fixture 5
        fixtures.setTeamId("AURFC_1");    //Team AURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("5"); //Fixture 5
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("6"); //Fixture 6
        fixtures.setTeamId("AURFC_1");    //Team AURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("6"); //Fixture 1
        fixtures.setTeamId("LURFC_1");    //Team LURFC
        fixtures.setFixturePoints(null); //null points as game not occured yet
        fixtureRepo.insert(fixtures);


       /* SCsession scSession = new SCsession();

        scSession.setMemberID("1");
        scSession.setSessionID("1");
        scSession.setDeadlifts("5:50, 5:50, 5:55:, 5:60");  //4 sets of 5 reps
        scSession.setDeadliftJumps("4:40, 4:40:, 4:45, 4:45");
        scSession.setBackSquat();
        */


        KPI kpi = new KPI();  //fix id 1 2 4 5

        //MEMBER ID 1, FIXTURE ID 1
        kpi.setMemberID("1");
        kpi.setFixtureID("1");
        kpi.setsTackles("0");
        kpi.setuTackles("8");
        kpi.setsCarries("10");
        kpi.setuCarries("0");
        kpi.setsPasses("10");
        kpi.setuPasses("5");
        kpi.setHandlingErrors("7");
        kpi.setPenalties("13");
        kpi.setYellowCards("13");
        kpi.setTriesScored("0");
        kpi.setTurnoversWon("5");
        kpi.setsThrowIns("10");
        kpi.setuThrowIns("0");
        kpi.setsLineOutTakes("12");
        kpi.setuLineOutTakes("13");
        kpi.setsKicks("10");
        kpi.setuKicks("3");
        kpiRepo.insert(kpi);

        //MEMBER ID 2, FIXTURE ID 1
        kpi.setMemberID("2");
        kpi.setFixtureID("1");
        kpi.setsTackles("5");
        kpi.setuTackles("6");
        kpi.setsCarries("13");
        kpi.setuCarries("2");
        kpi.setsPasses("12");
        kpi.setuPasses("13");
        kpi.setHandlingErrors("5");
        kpi.setPenalties("7");
        kpi.setYellowCards("9");
        kpi.setTriesScored("13");
        kpi.setTurnoversWon("10");
        kpi.setsThrowIns("5");
        kpi.setuThrowIns("9");
        kpi.setsLineOutTakes("0");
        kpi.setuLineOutTakes("0");
        kpi.setsKicks("9");
        kpi.setuKicks("3");
        kpiRepo.insert(kpi);

        //MEMBER ID 3, FIXTURE ID 1
        kpi.setMemberID("3");
        kpi.setFixtureID("1");
        kpi.setsTackles("14");
        kpi.setuTackles("0");
        kpi.setsCarries("10");
        kpi.setuCarries("0");
        kpi.setsPasses("7");
        kpi.setuPasses("0");
        kpi.setHandlingErrors("4");
        kpi.setPenalties("5");
        kpi.setYellowCards("2");
        kpi.setTriesScored("12");
        kpi.setTurnoversWon("0");
        kpi.setsThrowIns("1");
        kpi.setuThrowIns("13");
        kpi.setsLineOutTakes("1");
        kpi.setuLineOutTakes("10");
        kpi.setsKicks("3");
        kpi.setuKicks("12");
        kpiRepo.insert(kpi);

        //MEMBER ID 4, FIXTURE ID 1
        kpi.setMemberID("4");
        kpi.setFixtureID("1");
        kpi.setsTackles("0");
        kpi.setuTackles("10");
        kpi.setsCarries("4");
        kpi.setuCarries("6");
        kpi.setsPasses("2");
        kpi.setuPasses("1");
        kpi.setHandlingErrors("13");
        kpi.setPenalties("10");
        kpi.setYellowCards("4");
        kpi.setTriesScored("5");
        kpi.setTurnoversWon("12");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("13");
        kpi.setsLineOutTakes("6");
        kpi.setuLineOutTakes("10");
        kpi.setsKicks("8");
        kpi.setuKicks("2");
        kpiRepo.insert(kpi);

        //MEMBER IDs iterate for FIXTURE ID 2

        kpi.setMemberID("1");
        kpi.setFixtureID("2");
        kpi.setsTackles("7");
        kpi.setuTackles("5");
        kpi.setsCarries("2");
        kpi.setuCarries("13");
        kpi.setsPasses("0");
        kpi.setuPasses("0");
        kpi.setHandlingErrors("11");
        kpi.setPenalties("12");
        kpi.setYellowCards("13");
        kpi.setTriesScored("9");
        kpi.setTurnoversWon("0");
        kpi.setsThrowIns("1");
        kpi.setuThrowIns("5");
        kpi.setsLineOutTakes("0");
        kpi.setuLineOutTakes("8");
        kpi.setsKicks("11");
        kpi.setuKicks("2");
        kpiRepo.insert(kpi);

        kpi.setMemberID("2");
        kpi.setFixtureID("2");
        kpi.setsTackles("12");
        kpi.setuTackles("7");
        kpi.setsCarries("3");
        kpi.setuCarries("10");
        kpi.setsPasses("12");
        kpi.setuPasses("7");
        kpi.setHandlingErrors("11");
        kpi.setPenalties("4");
        kpi.setYellowCards("2");
        kpi.setTriesScored("0");
        kpi.setTurnoversWon("12");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("10");
        kpi.setsLineOutTakes("12");
        kpi.setuLineOutTakes("9");
        kpi.setsKicks("11");
        kpi.setuKicks("10");
        kpiRepo.insert(kpi);

        kpi.setMemberID("3");
        kpi.setFixtureID("2");
        kpi.setsTackles("6");
        kpi.setuTackles("5");
        kpi.setsCarries("10");
        kpi.setuCarries("3");
        kpi.setsPasses("4");
        kpi.setuPasses("14");
        kpi.setHandlingErrors("3");
        kpi.setPenalties("3");
        kpi.setYellowCards("0");
        kpi.setTriesScored("5");
        kpi.setTurnoversWon("11");
        kpi.setsThrowIns("1");
        kpi.setuThrowIns("12");
        kpi.setsLineOutTakes("11");
        kpi.setuLineOutTakes("9");
        kpi.setsKicks("3");
        kpi.setuKicks("4");
        kpiRepo.insert(kpi);

        kpi.setMemberID("4");
        kpi.setFixtureID("2");
        kpi.setsTackles("4");
        kpi.setuTackles("2");
        kpi.setsCarries("3");
        kpi.setuCarries("0");
        kpi.setsPasses("3");
        kpi.setuPasses("0");
        kpi.setHandlingErrors("3");
        kpi.setPenalties("7");
        kpi.setYellowCards("0");
        kpi.setTriesScored("4");
        kpi.setTurnoversWon("9");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("13");
        kpi.setsLineOutTakes("8");
        kpi.setuLineOutTakes("6");
        kpi.setsKicks("14");
        kpi.setuKicks("2");
        kpiRepo.insert(kpi);

        //fixture 4
        kpi.setMemberID("1");
        kpi.setFixtureID("4");
        kpi.setsTackles("2");
        kpi.setuTackles("6");
        kpi.setsCarries("9");
        kpi.setuCarries("9");
        kpi.setsPasses("12");
        kpi.setuPasses("12");
        kpi.setHandlingErrors("12");
        kpi.setPenalties("6");
        kpi.setYellowCards("9");
        kpi.setTriesScored("2");
        kpi.setTurnoversWon("11");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("0");
        kpi.setsLineOutTakes("9");
        kpi.setuLineOutTakes("14");
        kpi.setsKicks("7");
        kpi.setuKicks("4");
        kpiRepo.insert(kpi);

        kpi.setMemberID("2");
        kpi.setFixtureID("4");
        kpi.setsTackles("12");
        kpi.setuTackles("9");
        kpi.setsCarries("6");
        kpi.setuCarries("1");
        kpi.setsPasses("12");
        kpi.setuPasses("5");
        kpi.setHandlingErrors("0");
        kpi.setPenalties("1");
        kpi.setYellowCards("6");
        kpi.setTriesScored("10");
        kpi.setTurnoversWon("1");
        kpi.setsThrowIns("5");
        kpi.setuThrowIns("11");
        kpi.setsLineOutTakes("11");
        kpi.setuLineOutTakes("3");
        kpi.setsKicks("1");
        kpi.setuKicks("5");
        kpiRepo.insert(kpi);

        kpi.setMemberID("3");
        kpi.setFixtureID("4");
        kpi.setsTackles("2");
        kpi.setuTackles("12");
        kpi.setsCarries("5");
        kpi.setuCarries("10");
        kpi.setsPasses("3");
        kpi.setuPasses("11");
        kpi.setHandlingErrors("9");
        kpi.setPenalties("8");
        kpi.setYellowCards("12");
        kpi.setTriesScored("9");
        kpi.setTurnoversWon("7");
        kpi.setsThrowIns("5");
        kpi.setuThrowIns("5");
        kpi.setsLineOutTakes("5");
        kpi.setuLineOutTakes("4");
        kpi.setsKicks("13");
        kpi.setuKicks("1");
        kpiRepo.insert(kpi);

        kpi.setMemberID("4");
        kpi.setFixtureID("4");
        kpi.setsTackles("1");
        kpi.setuTackles("10");
        kpi.setsCarries("0");
        kpi.setuCarries("1");
        kpi.setsPasses("11");
        kpi.setuPasses("1");
        kpi.setHandlingErrors("5");
        kpi.setPenalties("7");
        kpi.setYellowCards("14");
        kpi.setTriesScored("1");
        kpi.setTurnoversWon("4");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("3");
        kpi.setsLineOutTakes("8");
        kpi.setuLineOutTakes("7");
        kpi.setsKicks("11");
        kpi.setuKicks("4");
        kpiRepo.insert(kpi);

        //fixture 5
        kpi.setMemberID("1");
        kpi.setFixtureID("5");
        kpi.setsTackles("0");
        kpi.setuTackles("11");
        kpi.setsCarries("3");
        kpi.setuCarries("1");
        kpi.setsPasses("9");
        kpi.setuPasses("3");
        kpi.setHandlingErrors("0");
        kpi.setPenalties("6");
        kpi.setYellowCards("0");
        kpi.setTriesScored("14");
        kpi.setTurnoversWon("8");
        kpi.setsThrowIns("9");
        kpi.setuThrowIns("2");
        kpi.setsLineOutTakes("5");
        kpi.setuLineOutTakes("1");
        kpi.setsKicks("3");
        kpi.setuKicks("1");
        kpiRepo.insert(kpi);

        kpi.setMemberID("2");
        kpi.setFixtureID("5");
        kpi.setsTackles("10");
        kpi.setuTackles("11");
        kpi.setsCarries("1");
        kpi.setuCarries("11");
        kpi.setsPasses("7");
        kpi.setuPasses("14");
        kpi.setHandlingErrors("12");
        kpi.setPenalties("13");
        kpi.setYellowCards("7");
        kpi.setTriesScored("9");
        kpi.setTurnoversWon("12");
        kpi.setsThrowIns("2");
        kpi.setuThrowIns("5");
        kpi.setsLineOutTakes("10");
        kpi.setuLineOutTakes("14");
        kpi.setsKicks("9");
        kpi.setuKicks("5");
        kpiRepo.insert(kpi);

        kpi.setMemberID("3");
        kpi.setFixtureID("5");
        kpi.setsTackles("12");
        kpi.setuTackles("6");
        kpi.setsCarries("3");
        kpi.setuCarries("10");
        kpi.setsPasses("0");
        kpi.setuPasses("0");
        kpi.setHandlingErrors("0");
        kpi.setPenalties("14");
        kpi.setYellowCards("6");
        kpi.setTriesScored("7");
        kpi.setTurnoversWon("14");
        kpi.setsThrowIns("11");
        kpi.setuThrowIns("0");
        kpi.setsLineOutTakes("9");
        kpi.setuLineOutTakes("8");
        kpi.setsKicks("5");
        kpi.setuKicks("14");
        kpiRepo.insert(kpi);

        kpi.setMemberID("4");
        kpi.setFixtureID("5");
        kpi.setsTackles("7");
        kpi.setuTackles("1");
        kpi.setsCarries("13");
        kpi.setuCarries("12");
        kpi.setsPasses("1");
        kpi.setuPasses("13");
        kpi.setHandlingErrors("13");
        kpi.setPenalties("7");
        kpi.setYellowCards("1");
        kpi.setTriesScored("12");
        kpi.setTurnoversWon("2");
        kpi.setsThrowIns("7");
        kpi.setuThrowIns("9");
        kpi.setsLineOutTakes("5");
        kpi.setuLineOutTakes("11");
        kpi.setsKicks("3");
        kpi.setuKicks("14");

        kpiRepo.insert(kpi);
    }

    public void showSessions(View view) {
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();

        showTable(view, "S and C", strengthAndConditioningRepo.getTableData(), 3);

    }

    public void showTeams(View view) {
        TeamRepo teamRepo = new TeamRepo();
        showTable(view, "Teams", teamRepo.getTableData(), 4);
    }

    @SuppressLint("SetTextI18n")
    public void showMembers(View view) {
        MemberRepo memberRepo = new MemberRepo();

        String[][] allMembers = memberRepo.getMembers();

        String output = "";

        TableLayout tl = new TableLayout(this);

        for(int row = 0; row < allMembers[0].length; row++) {
            TableRow tr = new TableRow(this);

            for(int col = 0; col < 9; col++){
                System.out.println(String.valueOf(row) + " " +
                        String.valueOf(col) + " " + allMembers[col][row]);
                TextView label = new TextView(this);
                label.setText(" | " + allMembers[col][row]);
                tr.addView(label);
            }

            tl.addView(tr);
        }

        LinearLayout tableContainer = findViewById(R.id.tableContainer);

        tableContainer.addView(tl);
    }

    public void goback(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void showKPIs(View view) {
        KPIRepo kpiRepo = new KPIRepo();
        showTable(view, "KPI", kpiRepo.getTableData(), 20);

    }

    public void showAll(View view) {
        //showMembers(view);
        MemberRepo memberRepo = new MemberRepo();
        FixtureRepo fixtureRepo = new FixtureRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();
        NoticeRepo noticeRepo = new NoticeRepo();
        KPIRepo kpiRepo = new KPIRepo();

        showTable(view, "Members", memberRepo.getMembers(), 9);
        showTable(view, "Fixtures:", fixtureRepo.getTableData(), 3);
        showTable(view, "S and C", strengthAndConditioningRepo.getTableData(), 3);
        showTable(view, "Team Fixtures", teamFixturesRepo.getTableData(), 3);
        showTable(view, "Teams", teamRepo.getTableData(), 4);
        showTable(view, "Notices", noticeRepo.getTableData(), 4);
        showTable(view, "KPI", kpiRepo.getTableData(), 20);

    }

    public void showTable(View view, String tableName, String[][] tableData, int cols) {

        TableLayout tl = new TableLayout(this);

        for(int row = 0; row < tableData[0].length; row++) {
            TableRow tr = new TableRow(this);

            for(int col = 0; col < cols; col++){
                TextView label = new TextView(this);
                label.setText(" | " + tableData[col][row]);
                System.out.print(label.getText());
                tr.addView(label);
            }

            System.out.println("");

            tl.addView(tr);
        }

        LinearLayout tableContainer = findViewById(R.id.tableContainer);

        //TextView tableTitle = null;
        //tableTitle.setText(tableName);
        //tableContainer.addView(tableTitle);
        tableContainer.addView(tl);
    }

}
