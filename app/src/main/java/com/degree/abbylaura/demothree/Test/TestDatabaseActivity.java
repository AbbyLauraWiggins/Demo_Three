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

import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.KPI;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.Session;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

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
        SessionRepo sessionRepo = new SessionRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();
        KPIRepo kpiRepo = new KPIRepo();
        NoticeRepo noticeRepo = new NoticeRepo();


        fixtureRepo.delete();
        memberRepo.delete();
        sessionRepo.delete();
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
        tf.setFixtureDate("12/02/2018");
        tf.setFixtureLocation("NG34 0RJ");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("3");
        tf.setFixtureDate("22/03/2018");
        tf.setFixtureLocation("B29 2TT");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("4");
        tf.setFixtureDate("08/04/2018");
        tf.setFixtureLocation("LN4 4AE");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("5");
        tf.setFixtureDate("18/04/2018");
        tf.setFixtureLocation("B15 2QW");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("6");
        tf.setFixtureDate("02/05/2018");
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
        fixtures.setTeamId("LURFC_1");    //Team LURFC
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

        //fixture 3

        kpi.setMemberID("1");
        kpi.setFixtureID("3");
        kpi.setsTackles("5");
        kpi.setuTackles("6");
        kpi.setsCarries("6");
        kpi.setuCarries("1");
        kpi.setsPasses("2");
        kpi.setuPasses("0");
        kpi.setHandlingErrors("7");
        kpi.setPenalties("1");
        kpi.setYellowCards("0");
        kpi.setTriesScored("2");
        kpi.setTurnoversWon("3");
        kpi.setsThrowIns("0");
        kpi.setuThrowIns("0");
        kpi.setsLineOutTakes("0");
        kpi.setuLineOutTakes("3");
        kpi.setsKicks("4");
        kpi.setuKicks("2");
        kpiRepo.insert(kpi);

        kpi.setMemberID("2");
        kpi.setFixtureID("3");
        kpi.setsTackles("1");
        kpi.setuTackles("2");
        kpi.setsCarries("5");
        kpi.setuCarries("1");
        kpi.setsPasses("3");
        kpi.setuPasses("1");
        kpi.setHandlingErrors("1");
        kpi.setPenalties("1");
        kpi.setYellowCards("0");
        kpi.setTriesScored("6");
        kpi.setTurnoversWon("4");
        kpi.setsThrowIns("5");
        kpi.setuThrowIns("3");
        kpi.setsLineOutTakes("1");
        kpi.setuLineOutTakes("1");
        kpi.setsKicks("4");
        kpi.setuKicks("1");
        kpiRepo.insert(kpi);

        kpi.setMemberID("3");
        kpi.setFixtureID("3");
        kpi.setsTackles("6");
        kpi.setuTackles("4");
        kpi.setsCarries("8");
        kpi.setuCarries("10");
        kpi.setsPasses("13");
        kpi.setuPasses("3");
        kpi.setHandlingErrors("6");
        kpi.setPenalties("2");
        kpi.setYellowCards("0");
        kpi.setTriesScored("1");
        kpi.setTurnoversWon("0");
        kpi.setsThrowIns("0");
        kpi.setuThrowIns("3");
        kpi.setsLineOutTakes("4");
        kpi.setuLineOutTakes("3");
        kpi.setsKicks("1");
        kpi.setuKicks("2");
        kpiRepo.insert(kpi);

        kpi.setMemberID("4");
        kpi.setFixtureID("3");
        kpi.setsTackles("14");
        kpi.setuTackles("4");
        kpi.setsCarries("7");
        kpi.setuCarries("5");
        kpi.setsPasses("21");
        kpi.setuPasses("3");
        kpi.setHandlingErrors("2");
        kpi.setPenalties("0");
        kpi.setYellowCards("0");
        kpi.setTriesScored("2");
        kpi.setTurnoversWon("3");
        kpi.setsThrowIns("1");
        kpi.setuThrowIns("1");
        kpi.setsLineOutTakes("3");
        kpi.setuLineOutTakes("0");
        kpi.setsKicks("1");
        kpi.setuKicks("0");
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

        Session scs = new Session();

        // >>>>> adding lots of data for member 1 to test graph <<<<

       // scs.setAuto("1");
        scs.setSessionID("1");
        scs.setMemberID("1");
        scs.setDeadlifts("70, 65, 70, 75");
        scs.setDeadliftJumps("50, 50, 55, 55");
        scs.setBackSquat("50, 55, 55");
        scs.setBackSquatJumps("40, 40, 40");
        scs.setGobletSquat("22, 20, 22");
        scs.setBenchPress("30, 35, 35, 35");
        scs.setMilitaryPress("20, 20, 20, 20");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, BY, Y");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("20, 20, 25, 25, 30");
        scs.setSplitSquat("30, 30, 35, 40, 40");
        scs.setFourWayArms("4, 3, 4");
        sessionRepo.insert(scs);

        scs.setSessionID("2");
        scs.setMemberID("1");
        scs.setDeadlifts("70, 75, 70, 75");
        scs.setDeadliftJumps("55, 55, 55, 60");
        scs.setBackSquat("55, 55, 55");
        scs.setBackSquatJumps("40, 45, 45");
        scs.setGobletSquat("22, 24, 22");
        scs.setBenchPress("30, 35, 40, 40");
        scs.setMilitaryPress("25, 25, 20, 20");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, BY, BY");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("20, 20, 25, 30, 30");
        scs.setSplitSquat("30, 30, 35, 40, 40");
        scs.setFourWayArms("4, 3, 4");
        sessionRepo.insert(scs);

        scs.setSessionID("3");
        scs.setMemberID("1");
        scs.setDeadlifts("75, 75, 70, 75");
        scs.setDeadliftJumps("55, 55, 60, 60");
        scs.setBackSquat("55, 55, 55");
        scs.setBackSquatJumps("45, 45, 45");
        scs.setGobletSquat("24, 24, 24");
        scs.setBenchPress("35, 35, 40, 40");
        scs.setMilitaryPress("25, 25, 25, 20");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, BY, BY");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("20, 20, 20, 30, 30");
        scs.setSplitSquat("30, 35, 35, 40, 40");
        scs.setFourWayArms("4, 4, 4");
        sessionRepo.insert(scs);

        scs.setSessionID("4");
        scs.setMemberID("1");
        scs.setDeadlifts("80, 75, 80, 75");
        scs.setDeadliftJumps("55, 55, 60, 60");
        scs.setBackSquat("55, 60, 55");
        scs.setBackSquatJumps("45, 45, 50");
        scs.setGobletSquat("26, 24, 26");
        scs.setBenchPress("35, 40, 40, 40");
        scs.setMilitaryPress("25, 25, 25, 30");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, BY, BY");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("25, 30, 30, 30, 30");
        scs.setSplitSquat("35, 35, 40, 40, 40");
        scs.setFourWayArms("4, 4, 4");
        sessionRepo.insert(scs);

        scs.setSessionID("5");
        scs.setMemberID("1");
        scs.setDeadlifts("80, 80, 80, 75");
        scs.setDeadliftJumps("55, 60, 60, 60");
        scs.setBackSquat("60, 60, 65");
        scs.setBackSquatJumps("45, 50, 50");
        scs.setGobletSquat("26, 26, 26");
        scs.setBenchPress("40, 40, 45, 40");
        scs.setMilitaryPress("25, 25, 30, 30");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, B, B");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("30, 30, 30, 30, 30");
        scs.setSplitSquat("40, 40, 40, 40, 40");
        scs.setFourWayArms("4, 4, 5");
        sessionRepo.insert(scs);

        //scs.setAuto("1");
        scs.setSessionID("1");
        scs.setMemberID("2");
        scs.setDeadlifts("75, 75, 75, 75");
        scs.setDeadliftJumps("50, 55, 65, 65");
        scs.setBackSquat("50, 50, 45");
        scs.setBackSquatJumps("45, 45, 40");
        scs.setGobletSquat("22, 20, 20");
        scs.setBenchPress("30, 30, 30, 35");
        scs.setMilitaryPress("20, 20, 20, 20");
        scs.setSupineRow("10, 10");
        scs.setChinUps("B, B, B, B");
        scs.setTrunk("3, 3, 3");
        scs.setRdl("25, 25, 25, 25, 30");
        scs.setSplitSquat("35, 35, 35, 45, 40");
        scs.setFourWayArms("2, 2, 2");

        sessionRepo.insert(scs);

    }

    public void showSessions(View view) {
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();

        showTable(view, "S and C", strengthAndConditioningRepo.getTableData(), 3);

        SessionRepo sessionRepo = new SessionRepo();

        showTable(view, "Session", sessionRepo.getTable(), 15);
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

    public void onTLbutton(View view) {
        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> leaderboard = kpiRepo.getKPISeasonLeaderboard();

        TableLayout tl = new TableLayout(this);

        for(int i = 2; i < leaderboard.size(); i++) { //start at 2 because we dont want Member and fixture ID

            TableRow tr = new TableRow(this);


            ArrayList<String> list = leaderboard.get(i);
            for (int j = 0; j < 3; j++) {
                TextView label = new TextView(this);
                label.setText(" | " + list.get(j));
                tr.addView(label);
            }


            tl.addView(tr);
        }

        LinearLayout tableContainer = findViewById(R.id.tableContainer);


        tableContainer.addView(tl);
    }

    public void insertRealTestData(){
        FixtureRepo fixtureRepo = new FixtureRepo();
        MemberRepo memberRepo   = new MemberRepo();
        SessionRepo sessionRepo = new SessionRepo();
        StrengthAndConditioningRepo strengthAndConditioningRepo = new StrengthAndConditioningRepo();
        TeamFixturesRepo teamFixturesRepo   = new TeamFixturesRepo();
        TeamRepo teamRepo = new TeamRepo();
        KPIRepo kpiRepo = new KPIRepo();
        NoticeRepo noticeRepo = new NoticeRepo();


        fixtureRepo.delete();
        memberRepo.delete();
        sessionRepo.delete();
        strengthAndConditioningRepo.delete();
        teamFixturesRepo.delete();
        teamRepo.delete();
        kpiRepo.delete();
        noticeRepo.delete();

        //INSERT ALL THE MEMBERS -> UOBWRFC USERS WHO HAVE AGREED TO BE TEST SUBJECTS
        Member member = new Member();
        insertMembers(member, memberRepo);

        //Create a list of four "teams", including our UOBWRFC_1 test team
        Team team = new Team();
        insertTeams(team, teamRepo);

        //INSERT TEAM FIXTURES (for UOBWRFC) and then create fixtures table
        //League calendar is (4 teams, each play each other home and away, so 12 fixtures, as follows
        //1: UOBWRFC vs LURFC   10/01/2018
        //2: LURFC vs UOBWRFC   17/01/2018
        //3: AURFC vs LURFC     24/01/2018
        //4: OUWRFC vs UOBWRFC  31/01/2018
        //5: UOBWRFC vs AURFC   7/02/2018
        //6: LURFC vs AURFC     14/02/2018
        //7: AURFC vs UOBWRFC   21/02/2018
        //8: OUWRFC vs LURFC    28/02/2018
        //9: OUWRFC vs AURFC  07/03/2018
        //10: LURFC vs OUWRFC   14/03/2018
        //11: AURFC vs OUWRFC   21/03/2017
        //12: UOBWRFC vs OUWRFC   28/03/2018
        TeamFixtures teamFixtures = new TeamFixtures();
        insertTeamFixtures(teamFixtures, teamFixturesRepo);

        Fixture fixture = new Fixture();
        insertFixtures(fixture, fixtureRepo);


        //Create a list of Strength and Conditioning sessions, then fill Session with REAL data
        StrengthAndConditioning sc = new StrengthAndConditioning();
        insertSC(sc, strengthAndConditioningRepo);

        Session session = new Session();
        insertSession(session, sessionRepo);

    }

    public void insertMembers(Member member, MemberRepo memberRepo){
        member.setMemberId("0001");
        member.setName("Michelle Ezigbo");
        member.setEmail("m.ezigbo@outlook.com");
        member.setPassword("0001");  //for testing, set password to memberID
        member.setDOB("14/12/1997");
        member.setPositions("5, 6, 8");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0002");
        member.setName("Beth Stacey");
        member.setEmail("b.Stacey@outlook.com");
        member.setPassword("0002");  //for testing, set password to memberID
        member.setDOB("22/02/1997");
        member.setPositions("6, 7");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0003");
        member.setName("Lizzie Nuttall");
        member.setEmail("l.Nuttall@outlook.com");
        member.setPassword("0003");  //for testing, set password to memberID
        member.setDOB("10/08/1995");
        member.setPositions("2");
        member.setResponsibilities("Team Captain");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("LEADER");
        memberRepo.insert(member);

        member.setMemberId("0004");
        member.setName("Abbygayle Wiggins");
        member.setEmail("a.Wiggins@outlook.com");
        member.setPassword("0004");  //for testing, set password to memberID
        member.setDOB("20/12/1996");
        member.setPositions("1");
        member.setResponsibilities("Development Officer");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("LEADER");
        memberRepo.insert(member);

        member.setMemberId("0005");
        member.setName("Jessica Ball");
        member.setEmail("j.ball@outlook.com");
        member.setPassword("0005");  //for testing, set password to memberID
        member.setDOB("01/02/1997");
        member.setPositions("9, 10");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0006");
        member.setName("Autumn Rose");
        member.setEmail("a.rose@outlook.com");
        member.setPassword("0006");  //for testing, set password to memberID
        member.setDOB("01/01/1996");
        member.setPositions("4, 5");
        member.setResponsibilities("Social Media Sec");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("ADMIN");
        memberRepo.insert(member);

        member.setMemberId("0007");
        member.setName("Lou Rooker");
        member.setEmail("l.Rooker@outlook.com");
        member.setPassword("0007");  //for testing, set password to memberID
        member.setDOB("18/05/1998");
        member.setPositions("1, 3");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0008");
        member.setName("Kathryn Hyland");
        member.setEmail("kathyh@outlook.com");
        member.setPassword("0008");  //for testing, set password to memberID
        member.setDOB("10/12/1997");
        member.setPositions("4, 5");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0009");
        member.setName("Louise Gavin");
        member.setEmail("lou.gavin@outlook.com");
        member.setPassword("0009");  //for testing, set password to memberID
        member.setDOB("19/07/1997");
        member.setPositions("2");
        member.setResponsibilities("None");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("BASIC");
        memberRepo.insert(member);

        member.setMemberId("0010");
        member.setName("Katie Purcell");
        member.setEmail("kp@outlook.com");
        member.setPassword("0010");  //for testing, set password to memberID
        member.setDOB("17/09/1997");
        member.setPositions("6, 7");
        member.setResponsibilities("Club Captain");
        member.setTeamId("UOBWRFC_1");
        member.setPermissions("ADMIN");
        memberRepo.insert(member);


    }

    public void insertTeams(Team team, TeamRepo teamRepo){
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

        team.setTeamId("OUWRFC_1");
        team.setTeamName("OUWRFC");
        team.setTeamLocation("B1 8LM");
        team.setTeamCurPoints("109");
        teamRepo.insert(team);


    }

    public void insertTeamFixtures(TeamFixtures tf, TeamFixturesRepo teamFixturesRepo){
        tf.setFixtureId("0001");
        tf.setFixtureDate("10/01/2018");
        tf.setFixtureLocation("B29 2TT");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("00012");
        tf.setFixtureDate("17/01/2018");
        tf.setFixtureLocation("LE12 8WD");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0003");
        tf.setFixtureDate("124/01/2018");
        tf.setFixtureLocation("B1 7ST");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0004");
        tf.setFixtureDate("31/01/2018");
        tf.setFixtureLocation("OX 9LN");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0005");
        tf.setFixtureDate("07/02/2018");
        tf.setFixtureLocation("B29 2TT");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0006");
        tf.setFixtureDate("14/02/2018");
        tf.setFixtureLocation("LE11 6NE");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0007");
        tf.setFixtureDate("21/02/2018");
        tf.setFixtureLocation("B1 7ST");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0008");
        tf.setFixtureDate("28/02/2018");
        tf.setFixtureLocation("OX 6ET");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0009");
        tf.setFixtureDate("07/03/2018");
        tf.setFixtureLocation("OX 9LN");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0010");
        tf.setFixtureDate("14/03/2018");
        tf.setFixtureLocation("LE12 8WD");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0011");
        tf.setFixtureDate("21/02/2018");
        tf.setFixtureLocation("B2 6DV");
        teamFixturesRepo.insert(tf);

        tf.setFixtureId("0012");
        tf.setFixtureDate("28/03/2018");
        tf.setFixtureLocation("B15 2QW");
        teamFixturesRepo.insert(tf);
    }

    public void insertFixtures(Fixture fixtures, FixtureRepo fixtureRepo){
        fixtures.setFixtureId("0001"); //Fixture 1
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints("22"); //Points scored by UOBWRFC in fixture 0001
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0001"); //Fixture 1
        fixtures.setTeamId("LURFC_1"); //Team LURFC
        fixtures.setFixturePoints("20"); //Points scored by LURFC in fixture 0001
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0002");
        fixtures.setTeamId("LURFC_1");
        fixtures.setFixturePoints("12");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0002"); //Fixture 1
        fixtures.setTeamId("UOBWRFC_1");    //Team UOBWRFC
        fixtures.setFixturePoints("37"); //Points scored by UOBWRFC
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0003");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints("32");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0003");
        fixtures.setTeamId("LURFC_1");
        fixtures.setFixturePoints("112");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0004");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints("67");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0004");
        fixtures.setTeamId("UOBWRFC_1");
        fixtures.setFixturePoints("55");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0005");
        fixtures.setTeamId("UOBWRFC_1");
        fixtures.setFixturePoints("15");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0005");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints("0");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0006");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints("10");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0006");
        fixtures.setTeamId("LURFC_1");
        fixtures.setFixturePoints("10");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0007");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints("7");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0007");
        fixtures.setTeamId("UOBWRFC_1");
        fixtures.setFixturePoints("12");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0008");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints("90");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0008");
        fixtures.setTeamId("LURFC_1");
        fixtures.setFixturePoints("64");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0009");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints("87");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0009");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints("44");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0010");
        fixtures.setTeamId("LURFC_1");
        fixtures.setFixturePoints("24");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0010");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints("10");
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0011");
        fixtures.setTeamId("AURFC_1");
        fixtures.setFixturePoints(null); //null as hasn't occured yet
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0011");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints(null);
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0012");
        fixtures.setTeamId("UOBWRFC_1");
        fixtures.setFixturePoints(null);
        fixtureRepo.insert(fixtures);

        fixtures.setFixtureId("0012");
        fixtures.setTeamId("OUWRFC_1");
        fixtures.setFixturePoints(null);
        fixtureRepo.insert(fixtures);


    }

    public void insertSC(StrengthAndConditioning sc, StrengthAndConditioningRepo scRepo){
        sc.setSessionID("0");
        sc.setSessionDate("25/09/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("1");
        sc.setSessionDate("29/09/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("2");
        sc.setSessionDate("02/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("3");
        sc.setSessionDate("06/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("4");
        sc.setSessionDate("09/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("5");
        sc.setSessionDate("13/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("6");
        sc.setSessionDate("16/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("7");
        sc.setSessionDate("20/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("8");
        sc.setSessionDate("23/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("9");
        sc.setSessionDate("27/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("10");
        sc.setSessionDate("30/10/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("11");
        sc.setSessionDate("03/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("12");
        sc.setSessionDate("06/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("13");
        sc.setSessionDate("10/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("14");
        sc.setSessionDate("13/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("15");
        sc.setSessionDate("17/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("16");
        sc.setSessionDate("20/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("17");
        sc.setSessionDate("24/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("18");
        sc.setSessionDate("27/11/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("19");
        sc.setSessionDate("01/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("20");
        sc.setSessionDate("04/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("21");
        sc.setSessionDate("08/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("22");
        sc.setSessionDate("11/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("23");
        sc.setSessionDate("15/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("24");
        sc.setSessionDate("18/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("25");
        sc.setSessionDate("22/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("26");
        sc.setSessionDate("25/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("27");
        sc.setSessionDate("29/12/2017");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("28");
        sc.setSessionDate("01/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("29");
        sc.setSessionDate("05/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("30");
        sc.setSessionDate("08/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("31");
        sc.setSessionDate("12/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("32");
        sc.setSessionDate("15/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("33");
        sc.setSessionDate("19/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("34");
        sc.setSessionDate("22/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("35");
        sc.setSessionDate("26/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("36");
        sc.setSessionDate("29/01/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("37");
        sc.setSessionDate("02/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("38");
        sc.setSessionDate("05/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("39");
        sc.setSessionDate("09/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("40");
        sc.setSessionDate("12/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("41");
        sc.setSessionDate("16/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("42");
        sc.setSessionDate("19/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("43");
        sc.setSessionDate("23/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("44");
        sc.setSessionDate("26/02/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("45");
        sc.setSessionDate("02/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("46");
        sc.setSessionDate("05/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("47");
        sc.setSessionDate("09/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("48");
        sc.setSessionDate("12/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("49");
        sc.setSessionDate("16/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("50");
        sc.setSessionDate("19/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("51");
        sc.setSessionDate("23/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);

        sc.setSessionID("52");
        sc.setSessionDate("26/03/2018");
        sc.setSessionTime("1615");
        scRepo.insert(sc);
    }

    public void insertSession(Session scs, SessionRepo sessionRepo){
        //MEMBER 0001 = michelle
        scs.setSessionID("17");
        scs.setMemberID("0001");
        scs.setDeadlifts("5:60, 5:65, 5:70, 5:75: 5:80"); //5:60 means 5 reps of 60kg
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:25, 5:30, 5:35");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("10:25, 10:25, 10:30, 10:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("22");
        scs.setMemberID("0001");
        scs.setDeadlifts("5:65, 5:70, 5:80, 5:85: 5:90");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:25, 5:30, 5:35");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        //MEMBER 0002 Beth Stacey
        scs.setSessionID("1");
        scs.setMemberID("0002");
        scs.setDeadlifts("6:40, 6:40, 6:50, 6:50, 6:60");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:20, 10:20, 10:20");
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("3");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("6:15, 6:40, 6:40, 6:42.5, 6:45");
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress("10:15, 10:15, 10:20, 10:20, 10:25");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("8:15, 8:25, 8:25");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("4");
        scs.setMemberID("0002");
        scs.setDeadlifts("6:50, 6:50, 6:60, 6:60, 6:60");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:15, 10:15, 10:15, 10:25, 10:25");
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("5");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("6:40, 6:40, 6:40, 6:40, 6:45");
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress("10:15, 10:20, 10:20, 10:25, 10:25");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("7");
        scs.setMemberID("0002");
        scs.setDeadlifts("6:40, 6:42.5, 6:42.5, 6:45, 6:42.5");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("9");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:35, 8:25, 8:25, 8:25, 8:25");
        scs.setBackSquatJumps("5:15, 5:15, 5:15");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:15, 8:25, 8:25");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:15, 5:25, 5:25");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("10");
        scs.setMemberID("0002");
        scs.setDeadlifts("4:60, 4:70, 4:70, 4:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:20, 5:25, 5:25");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("11");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:40, 8:40, 8:40, 8:40, 8:50");
        scs.setBackSquatJumps("5:20, 5:25, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:20, 8:25, 8:25");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:20, 5:25, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("12");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps("4:60, 4:70, 4:70, 4:70, 4:70");
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:20, 10:20, 10:20");
        scs.setSplitSquat("5:20, 5:25, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("13");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:40, 8:40, 8:40, 8:40, 8:40");
        scs.setBackSquatJumps("5:25, 5:25, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress("6:20, 6:20, 6:25");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("17");
        scs.setMemberID("0002");
        scs.setDeadlifts("5:60, 5:60, 5:70, 5:70, 5:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:20, 5:30, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow("10:/, 10:/, 10:/, 10:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("10:25, 10:25, 10:25");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("18");
        scs.setMemberID("0002");
        scs.setDeadlifts("5:60, 5:60, 5:60, 5:70, 5:72.5");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:25, 5:30, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("19");
        scs.setMemberID("0002");
        scs.setDeadlifts("5:60, 5:60, 5:60, 5:60, 5:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:20, 5:30, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("21");
        scs.setMemberID("0002");
        scs.setDeadlifts("5:60, 5:60, 5:60, 5:60, 5:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:20, 5:30, 5:40");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("32");
        scs.setMemberID("0002");
        scs.setDeadlifts("5:60, 5:60, 5:60, 5:60, 5:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat("4:40, 4:40, 4:40, 4:40");
        scs.setBackSquatJumps("5:20, 5:25, 5:25, 5:25");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("39");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("4:40, 4:40, 4:40, 4:40");
        scs.setBackSquatJumps("5:20, 5:25, 5:30, 5:30");
        scs.setGobletSquat("4:16, 4:16, 4:16");
        scs.setBenchPress("4:22.5, 4:25, 4:30, 4:30");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("43");
        scs.setMemberID("0002");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("4:40, 4:40, 4:40, 4:40");
        scs.setBackSquatJumps("5:30, 5:30, 5:30, 5:30");
        scs.setGobletSquat("4:18, 4:18, 4:18");
        scs.setBenchPress("4:25, 4:25, 4:30, 4:30");
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        //MEMBER 0003 LIZZIE NUTTALL
        scs.setSessionID("9");
        scs.setMemberID("0003");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:45, 8:45, 8:50, 8:52.5, 8:52.5");
        scs.setBackSquatJumps("5:15, 5:15, 5:15");
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow(null);
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat(null);
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("10");
        scs.setMemberID("0003");
        scs.setDeadlifts("4:80, 4:92.5, 4:100, 4:90");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:35, 10:35, 10:40");
        scs.setSplitSquat("5:30, 5:30, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("11");
        scs.setMemberID("0003");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:45, 8:47.5, 8:50, 8:50, 8:52.5");
        scs.setBackSquatJumps("5:20, 5:20, 5:25");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:25, 8:30, 8:35");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:30, 5:30, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("12");
        scs.setMemberID("0003");
        scs.setDeadlifts("4:80, 4:85, 4:85, 4:85");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:30, 10:30, 10:30");
        scs.setSplitSquat("5:30, 5:30, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("13");
        scs.setMemberID("0003");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:25, 5:25, 5:25");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:25, 8:30, 8:35, 8:37.5");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:40, 5,40, 5,40");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("14");
        scs.setMemberID("0003");
        scs.setDeadlifts("4:80, 4:80, 4:80, 4:80");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:30, 10:30, 10:30");
        scs.setSplitSquat("5:30, 5:30, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("15");
        scs.setMemberID("0003");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps("5:25, 5:25, 5:25");
        scs.setGobletSquat(null);
        scs.setBenchPress("6:27.5, 6:35, 6:37.5, 6:40");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:37.5, 5,40, 5,40");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("16");
        scs.setMemberID("0003");
        scs.setDeadlifts("4:80, 4:90, 4:95, 4:95");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress("8:25, 8:30, 8:35, 8:37.5");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:40, 10:40, 10:40");
        scs.setSplitSquat("5:40, 5,40, 5,40");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        //MEMBER 0004 Abbygayle Wiggins
        scs.setSessionID("9");
        scs.setMemberID("0004");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:45, 8:45, 8:45, 8:50, 8:50");
        scs.setBackSquatJumps("5:15, 5:15, 5:25");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:20, 8:25, 8:30, 8:30");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:40, 5:40, 5:40");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("10");
        scs.setMemberID("0004");
        scs.setDeadlifts("4:65, 4:70, 4:75, 4:70");
        scs.setDeadliftJumps(null);
        scs.setBackSquat(null);
        scs.setBackSquatJumps(null);
        scs.setGobletSquat(null);
        scs.setBenchPress(null);
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl("10:35, 10:35, 10:35");
        scs.setSplitSquat("5:30, 5:35, 5:35");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);

        scs.setSessionID("11");
        scs.setMemberID("0004");
        scs.setDeadlifts(null);
        scs.setDeadliftJumps(null);
        scs.setBackSquat("8:45, 8:45, 8:47.5, 8:50, 8:52.5");
        scs.setBackSquatJumps("5:25, 5:25, 5:30");
        scs.setGobletSquat(null);
        scs.setBenchPress("8:35, 8:30, 8:30");
        scs.setMilitaryPress(null);
        scs.setSupineRow("12:/, 12:/, 12:/");
        scs.setChinUps(null);
        scs.setTrunk(null);
        scs.setRdl(null);
        scs.setSplitSquat("5:30, 5:30, 5:30");
        scs.setFourWayArms(null);
        sessionRepo.insert(scs);
    }
}
