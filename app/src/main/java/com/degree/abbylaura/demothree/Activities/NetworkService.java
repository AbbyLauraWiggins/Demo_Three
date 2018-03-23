package com.degree.abbylaura.demothree.Activities;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.degree.abbylaura.demothree.Client.MyClientID;
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
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.Database.Schema.Session;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abbylaura on 25/02/2018.
 */

public class NetworkService extends IntentService {

    // Used to identify when the IntentService finishes
    public static final String TRANSACTION_DONE_NOTICE = "TRANSACTION_DONE_NOTICE";
    public static final String TRANSACTION_DONE_VALID = "TRANSACTION_DONE_VALID";
    public static final String TRANSACTION_VOID = "TRANSACTION_VOID";


    // Validates resource references inside Android XML files
    public NetworkService() {
        super(NetworkService.class.getName());
    }

    public NetworkService(String name) {
        super(name);
    }



    //Runs when called from an activity
    @Override
    protected void onHandleIntent(Intent intent) {


        //so send response back to activity that requested it
        System.out.println("JUST BEFORE INTENT");

        Intent i = new Intent(TRANSACTION_VOID);

        Log.e("NetworkService", "Service Started");

        ArrayList<String> passedList = intent.getStringArrayListExtra("CLASS");

        if(passedList == null){
            String typeSending = intent.getStringExtra("typeSending");

            if(typeSending.equals("SESSION")){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>in network service SESSION");
                String permission = intent.getStringExtra("PERMISSION");

                updateScSession(permission);
                i = new Intent(TRANSACTION_DONE_VALID);
                String valid = "updated";
                i.putExtra("VALIDATION", valid);

            }else if(typeSending.equals("KPI")){
                String permission = intent.getStringExtra("PERMISSION");

                updateKPI(permission);
                i = new Intent(TRANSACTION_DONE_VALID);

                String valid = "updated";
                i.putExtra("VALIDATION", valid);
            }
            else if(typeSending.equals("SCADD")){
                //first item in this array will be SC details, all other for session setup
                ArrayList<String> sessionInsert = intent.getStringArrayListExtra("SESSIONinsert");

                insertSession(sessionInsert);
            }
            else{

                i = new Intent(TRANSACTION_VOID);

                NetworkService.this.sendBroadcast(i);
            }

        }else{

            String typeSending = intent.getStringExtra("typeSending");
            int size = Integer.parseInt(intent.getStringExtra("TABLESIZE"));


            if(typeSending.equals("NOTICE")){
                ArrayList<String> response = serviceRequest(passedList, typeSending, size);

                updateNotices(response);

                i = new Intent(TRANSACTION_DONE_NOTICE);

            }
            else if(typeSending.equals("LOGIN")){
                //refresh all necessary tables when log in
                startUpGetFixtures();
                startUpGetTeamFixtures();
                startUpGetTeams();
                startUpGetSC();

                ArrayList<String> response = serviceRequest(passedList, typeSending, size);

                i = new Intent(TRANSACTION_DONE_VALID);
                String valid = updateMembers(response, passedList);
                i.putExtra("VALIDATION", valid);
            }


            System.out.println("JUST AFTER INTENT");

            NetworkService.this.sendBroadcast(i);

            System.out.println("after send broadcast");

        }


    }

    protected ArrayList<String> serviceRequest(ArrayList<String> passedRequest, String typeSending, int size) {
        //start a client, connect to server, send it request and object
        NestedClient nClient = new NestedClient();

        return nClient.talkToServer(passedRequest, typeSending, size);
    }



    /*
     * Will return an ArrayList<String> in format that each string = table row: "col1||col2||col3..."
     */
    public class NestedClient{
        ObjectOutputStream outToServer;
        ObjectInputStream inFromServer;

        Socket socket;

        public NestedClient(){
            super();
            System.out.println("4: Nested Client constructor");

        }

        public ArrayList<String> talkToServer(ArrayList<String> passedRequest, String typeSending, int size) {
            ArrayList<String> response = new ArrayList<>();

            try{
                System.out.println("5: Try block of TalkToServer NestedClient");

                socket = new Socket("192.168.0.18", 9002); //192.168.0.18

                outToServer = new ObjectOutputStream(socket.getOutputStream());
                inFromServer = new ObjectInputStream(socket.getInputStream());



                outToServer.writeObject(typeSending);   //DATABASE TABLE TYPE
                outToServer.writeObject(size);          //SIZE OF CLIENT TABLE
                outToServer.writeObject(passedRequest); //WHAT WE WANT TO ADD TO TABLE


                response = (ArrayList<String>) inFromServer.readObject(); //CONTENTS OF TABLE TO UPDATE

                System.out.println("nClient try response: " + response);


                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return response;
        }

    }



    private void updateNotices(ArrayList<String> response){

        if(!response.isEmpty()){
            if(!(response.get(0).equals("CODE:4698:EMPTYBUFFER"))){
                System.out.println("updateNotices: " + response.toString());

                NoticeRepo noticeRepo = new NoticeRepo();

                for(String al: response){
                    String[] splitter = al.split("4h4f");
                    Notice notice = new Notice();
                    notice.setNoticeId((String) splitter[0]);
                    notice.setMemberId((String) splitter[1]);
                    notice.setContents((String) splitter[2]);
                    notice.setDate((String) splitter[3]);

                    noticeRepo.insert(notice);
                }
            }

        }

        System.out.println(">>>>>>>>>>>>>>>> notices updated");
    }

    private String updateMembers(ArrayList<String> response, ArrayList<String> logInDetails){
        String email = logInDetails.get(0);
        String password = logInDetails.get(1);
        String validation = "false";
        String id = "";
        String team = "";

        if(!response.isEmpty()){
            if(!response.get(0).equals("CODE:4699:NOMEMBERS")){
                System.out.println("updateMembers: " + response.toString());

                MemberRepo memberRepo = new MemberRepo();
                memberRepo.delete();

                for(String al: response){
                    String[] splitter = al.split("4h4f");

                    Member member = new Member();
                    member.setMemberId((String) splitter[0]);
                    member.setName((String) splitter[1]);
                    member.setEmail((String) splitter[2]);
                    member.setPassword((String) splitter[3]);
                    member.setDOB((String) splitter[4]);
                    member.setPositions((String) splitter[5]);
                    member.setResponsibilities((String) splitter[6]);
                    member.setTeamId((String) splitter[7]);
                    member.setPermissions((String) splitter[8]);

                    if(splitter[2].equals(email) && splitter[3].equals(password)){
                        validation="true";
                        id = (splitter[0]);
                        team = String.valueOf(splitter[7]);
                    }

                    memberRepo.insert(member);

                }
            }
        }

        System.out.println(">>>>>>>>>>>>>>>> members updated");


        if(validation.equals("false")){
            return validation;

        }else{
            return id + "4h4f" + team;
        }
    }

    private void startUpGetFixtures(){
        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4800:UPDATEFIXTURES");

        FixtureRepo fixtureRepo = new FixtureRepo();

        ArrayList<String> fixtures = serviceRequest(request, "FIXTURES", fixtureRepo.getTableSize());
        if(!fixtures.isEmpty()){
            if(!(fixtures.get(0).equals("CODE:4702:NOFIXTURES"))){
                for(String f: fixtures){
                    String[] splitter = f.split("4h4f");
                    Fixture fixture = new Fixture();
                    fixture.setTeamId(splitter[1]);
                    fixture.setFixtureId(splitter[2]);
                    fixture.setFixturePoints(splitter[3]);
                    fixture.setForward(splitter[4]);
                    fixture.setBack(splitter[5]);
                    fixture.setPlayer(splitter[6]);
                    fixture.setTriesScored(splitter[7]);
                    fixture.setTriesSucceeded(splitter[8]);
                    fixture.setConversions(splitter[9]);
                    fixture.setConversionsSucceeded(splitter[10]);
                    fixture.setScrumsWon(splitter[11]);
                    fixture.setScrumsLost(splitter[12]);
                    fixture.setMaulsWon(splitter[13]);
                    fixture.setMaulsLost(splitter[14]);
                    fixture.setLineOutsWon(splitter[15]);
                    fixture.setLineOutsLost(splitter[16]);
                    fixture.setDropGoals(splitter[17]);
                    fixture.setPenaltyKicks(splitter[18]);

                    fixtureRepo.insert(fixture);
                }

            }
        }

        System.out.println(">>>>>>>>>>>>>>>> fixtures updated");


    }

    private void startUpGetTeamFixtures(){
        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4801:UPDATETEAMFIXTURES");

        TeamFixturesRepo tfRepo = new TeamFixturesRepo();
        //tfRepo.delete();

        ArrayList<String> fixtures = serviceRequest(request, "TEAMFIXTURES", tfRepo.getTableSize());
        if(!fixtures.isEmpty()){
            if(!(fixtures.get(0).equals("CODE:4702:NOFIXTURES"))){
                for(String f: fixtures){
                    String[] splitter = f.split("4h4f");

                    TeamFixtures tf = new TeamFixtures();
                    tf.setFixtureId(splitter[0]);
                    tf.setFixtureDate(splitter[1]);
                    tf.setFixtureLocation(splitter[2]);
                    tf.setHomeTeam(splitter[3]);
                    tf.setAwayTeam(splitter[4]);
                    tfRepo.insert(tf);
                }

            }
        }

        System.out.println(">>>>>>>>>>>>>>>> teamfixtures updated");

    }

    private void startUpGetTeams(){
        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4802:UPDATETEAMS");

        TeamRepo teamRepo = new TeamRepo();
        //tfRepo.delete();

        ArrayList<String> teams = serviceRequest(request, "TEAMS", teamRepo.getTableSize());
        if(!teams.isEmpty()){
            if(!(teams.get(0).equals("CODE:4700:NOTEAMS"))){
                for(String t: teams){
                    String[] splitter = t.split("4h4f");

                    Team team = new Team();
                    team.setTeamId(splitter[0]);
                    team.setTeamName(splitter[1]);
                    team.setTeamLocation(splitter[2]);
                    team.setTeamCurPoints(splitter[3]);
                    teamRepo.insert(team);
                }

            }
        }

        System.out.println(">>>>>>>>>>>>>>>> teams updated");

    }

    private void startUpGetSC(){
        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4804:UPDATESC");

        StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
        //tfRepo.delete();

        ArrayList<String> scList = serviceRequest(request, "SC", scRepo.getTableSize());
        if(!scList.isEmpty()){
            if(!(scList.get(0).equals("CODE:4701:NOSESSIONS"))){
                for(String t: scList){
                    String[] splitter = t.split("4h4f");

                    StrengthAndConditioning sc = new StrengthAndConditioning();
                    sc.setSessionID(splitter[0]);
                    sc.setSessionDate(splitter[1]);
                    sc.setSessionTime(splitter[2]);
                    scRepo.insert(sc);
                }

            }
        }

        System.out.println(">>>>>>>>>>>>>>>> SC updated");

    }

    private void updateScSession(String permission){
        SessionRepo sessionRepo = new SessionRepo();

        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4805:UPDATESESSION");
        request.add(permission);


        if(Integer.valueOf(permission) < 2){
            sessionRepo.delete(); //will delete all data as cannot only pass new data based on table size as we are filtering
            sessionRepo.createTable();
            request.add(MyClientID.myID);
        }

        ArrayList<String> sessionData = serviceRequest(request, "SESSION", sessionRepo.getTableSize());

        if(!sessionData.isEmpty()){
            if(!sessionData.get(0).equals("CODE:4701:NOSESSIONS")){
                for(String s: sessionData){
                    String[] splitter = s.split("4h4f");
                    Session scs = new Session();

                    scs.setSessionID(splitter[0]);
                    scs.setMemberID(splitter[1]);
                    scs.setDeadlifts(splitter[1]);
                    scs.setDeadliftJumps(splitter[3]);
                    scs.setBackSquat(splitter[4]);
                    scs.setBackSquatJumps(splitter[5]);
                    scs.setGobletSquat(splitter[6]);
                    scs.setBenchPress(splitter[7]);
                    scs.setMilitaryPress(splitter[8]);
                    scs.setSupineRow(splitter[9]);
                    scs.setChinUps(splitter[10]);
                    scs.setTrunk(splitter[11]);
                    scs.setRdl(splitter[12]);
                    scs.setSplitSquat(splitter[13]);
                    scs.setFourWayArms(splitter[14]);
                    sessionRepo.insert(scs);
                }
            }
        }

        System.out.println(">>>>>>>>>>>>>>>> session updated");

    }

    private void updateKPI(String permission){
        KPIRepo kpiRepo = new KPIRepo();

        ArrayList<String> request = new ArrayList<>();
        request.add("CODE:4808:UPDATEKPI");
        request.add(permission);

        kpiRepo.delete(); //will delete all data as cannot only pass new data based on table size as we are filtering
        kpiRepo.createtable();

        if(Integer.valueOf(permission) < 2){
            //kpiRepo.delete(); //will delete all data as cannot only pass new data based on table size as we are filtering
           // kpiRepo.createtable();
            request.add(MyClientID.myID);
        }

        ArrayList<String> sessionData = serviceRequest(request, "KPI", kpiRepo.getTableSize());

        if(!sessionData.isEmpty()){
            if(!sessionData.get(0).equals("CODE:4809:NOKPIS")){
                for(String s: sessionData){
                    String[] splitter = s.split("4h4f");
                    KPI kpi = new KPI();
                    kpi.setMemberID(splitter[1]);
                    kpi.setFixtureID(splitter[2]);
                    kpi.setsTackles(splitter[3]);
                    kpi.setuTackles(splitter[4]);
                    kpi.setsCarries(splitter[5]);
                    kpi.setuCarries(splitter[6]);
                    kpi.setsPasses(splitter[7]);
                    kpi.setuPasses(splitter[8]);
                    kpi.setHandlingErrors(splitter[9]);
                    kpi.setPenalties(splitter[10]);
                    kpi.setYellowCards(splitter[11]);
                    kpi.setTriesScored(splitter[12]);
                    kpi.setTurnoversWon(splitter[13]);
                    kpi.setsThrowIns(splitter[14]);
                    kpi.setuThrowIns(splitter[15]);
                    kpi.setsLineOutTakes(splitter[16]);
                    kpi.setuLineOutTakes(splitter[17]);
                    kpi.setsKicks(splitter[18]);
                    kpi.setuKicks(splitter[19]);
                    kpiRepo.insert(kpi);
                }
            }
        }

        System.out.println(">>>>>>>>>>>>>>>> kpi updated");

    }

    private void insertSession(ArrayList<String> insert){
        ArrayList<String> toUpdate = serviceRequest(insert, "SCADD", 0);

        StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
        scRepo.delete();

        SessionRepo sessionRepo = new SessionRepo();
        sessionRepo.delete();

        int seen = 0;
        for(String s: toUpdate){
            if(!s.equals("session")){
                seen = 1;
            }
            if(seen == 0){
                String[] splitter = s.split("4h4f");
                StrengthAndConditioning sc = new StrengthAndConditioning();
                sc.setSessionID(splitter[0]);
                sc.setSessionDate(splitter[1]);
                sc.setSessionTime(splitter[2]);
                scRepo.insert(sc);
            }
            else{
                String[] splitter = s.split("4h4f");
                Session scs = new Session();

                scs.setSessionID(splitter[0]);
                scs.setMemberID(splitter[1]);
                scs.setDeadlifts(splitter[1]);
                scs.setDeadliftJumps(splitter[3]);
                scs.setBackSquat(splitter[4]);
                scs.setBackSquatJumps(splitter[5]);
                scs.setGobletSquat(splitter[6]);
                scs.setBenchPress(splitter[7]);
                scs.setMilitaryPress(splitter[8]);
                scs.setSupineRow(splitter[9]);
                scs.setChinUps(splitter[10]);
                scs.setTrunk(splitter[11]);
                scs.setRdl(splitter[12]);
                scs.setSplitSquat(splitter[13]);
                scs.setFourWayArms(splitter[14]);
                sessionRepo.insert(scs);
            }
        }
    }

}
