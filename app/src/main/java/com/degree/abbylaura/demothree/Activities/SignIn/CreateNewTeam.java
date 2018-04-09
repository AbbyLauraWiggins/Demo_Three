package com.degree.abbylaura.demothree.Activities.SignIn;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamRepo;
import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;


public class CreateNewTeam extends Activity{

    Button members, fixtures, create;
    EditText teamname, teamlocation;
    TextView teamid;
    ArrayList<String> permissions;
    int newTeam;
    String tempid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_new_team_activity);

        members = findViewById(R.id.setMemPerm);
        fixtures = findViewById(R.id.addTeamFixturesButton);
        teamname = findViewById(R.id.teamNameET);
        teamlocation = findViewById(R.id.teamLocationET);
        teamid = findViewById(R.id.teamID);
        newTeam = 0;
        create = findViewById(R.id.createbutton);
        permissions = new ArrayList<>();
        tempid = "";

        Intent activityThatCalled = getIntent();
        String preceding = activityThatCalled.getStringExtra("PRECEDING");
        if(preceding.equals("new")){
            members.setVisibility(View.INVISIBLE);
            fixtures.setVisibility(View.INVISIBLE);
            newTeam = 1;
            create.setVisibility(View.VISIBLE);
        }else{
            create.setVisibility(View.GONE);
            fillET();
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_ADDTEAM);
        registerReceiver(clientReceiver, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(NetworkService.TRANSACTION_DONE_PERMISSIONS);
        registerReceiver(clientReceiver2, intentFilter2);
    }

    private void setNewID(){
        String teamName = teamname.getText().toString();

        TeamRepo teamRepo = new TeamRepo();

        String[][] teamData = teamRepo.getTableData();

        int quantity = 1;
        for(int i=0; i < teamData[0].length; i++){
            if(teamData[1][i].equals(teamName)){
                quantity++;
            }
        }

        String teamId = teamName + "_" + String.valueOf(quantity);

        teamid.setText("Team ID: " + teamId);

        Team team = new Team();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        team.setTeamLocation(teamlocation.getText().toString());
        team.setTeamCurPoints("0");

        teamRepo.insert(team);

        Intent intent = new Intent(this, NetworkService.class);

        ArrayList<String> newTeamLst = new ArrayList<>();
        newTeamLst.add(teamId);
        newTeamLst.add(teamName);
        newTeamLst.add(teamlocation.getText().toString());
        newTeamLst.add("0");

        intent.putExtra("TABLESIZE", "0");
        intent.putExtra("CLASS", newTeamLst);
        intent.putExtra("typeSending", "ADDTEAM");
        this.startService(intent);



    }

    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive BroadcastReceiver");
            makeToast();
        }

    };

    public void fillET(){
        String teamID = MyClientID.myTeamID;

        teamid.setText("Team ID: " + teamID);

        TeamRepo teamRepo = new TeamRepo();

        ArrayList<String> teamInfo = teamRepo.getTeam(teamID);

        teamname.setText(teamInfo.get(0));
        teamlocation.setText(teamInfo.get(1));
    }

    public void makeToast(){
        Toast.makeText(this, "New team created.", Toast.LENGTH_SHORT).show();
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

    public void createTeam(View view) {
        setNewID();
    }

    public void onSetMemberPermissions(View view) {
        MemberRepo memberRepo = new MemberRepo();
        TableLayout tl = findViewById(R.id.memberTableLayout);
        ArrayList<ArrayList<String>> members = memberRepo.getMyTeam(MyClientID.myTeamID);

        TableRow title = new TableRow(this);
        TextView t = new TextView(this);
        t.setText("Use the spinner to change the permissions.");
        title.addView(t);
        tl.addView(title);

        System.out.println("members: " + members);

        for(ArrayList mem: members){
            String id = (String) mem.get(0);
            String name = (String) mem.get(1);

            TableRow tr = new TableRow(this);

            TextView label = new TextView(this);
            label.setText(name);

            Spinner spinner = new Spinner(this);
            setSpinner(spinner, id);

            tr.addView(label);
            tr.addView(spinner);

            tl.addView(tr);
        }

        Button submit = new Button(this);
        submit.setText("Submit changes");
        LinearLayout ll = findViewById(R.id.ll);

        ll.addView(submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDatabase();
            }
        });
    }

    public void updateDatabase(){
        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("TABLESIZE", "0");
        intent.putExtra("CLASS", permissions);
        intent.putExtra("typeSending", "PERMISSIONS");
        this.startService(intent);

    }

    private void setSpinner(Spinner spinner, String id){
        ArrayList<String> spinnerList = new ArrayList<>();

        spinnerList.add("Basic");
        spinnerList.add("Admin");
        spinnerList.add("Leader");
        spinnerList.add("CAL");
        spinnerList.add("PAL");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //final String[] permission = {"Basic"};
        tempid = id;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changePerm(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void changePerm(String perm){
        permissions.add(tempid + "4h4f" + perm);

    }

    private BroadcastReceiver clientReceiver2 = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive BroadcastReceiver");
            makeUpdateToast();
        }

    };

    private void makeUpdateToast(){
        Toast.makeText(this, "Permissions Updated.", Toast.LENGTH_SHORT).show();
    }

    public void addTeamFix(View view) {
        Intent intent = new Intent(this, AddTeamFix.class);
        startActivity(intent);
    }
}

