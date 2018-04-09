package com.degree.abbylaura.demothree.Activities.SignIn;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTeamFix extends Activity {
    EditText dateET, locET, oppoET;
    String strDate;
    ArrayList<String> toInsert;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_team_fix_activity);

        dateET = findViewById(R.id.dateEditText);
        locET = findViewById(R.id.locationET);
        oppoET = findViewById(R.id.oppositionID);
        strDate = "";

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_SCADD);
        registerReceiver(clientReceiver, intentFilter);
    }

    public void onChooseDate(View view) {
        Calendar mcurrentDate = Calendar.getInstance();
        int yearNow = mcurrentDate.get(Calendar.YEAR);
        int monthNow = mcurrentDate.get(Calendar.MONTH);
        int dayNow = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final int[] yearSetArr = new int[1];
        final int[] monthSetArr = new int[1];
        final int[] daySetArr = new int[1];

        DatePickerDialog datePicker;
        datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                System.out.println(selectedday + "/" + selectedmonth + "/" + selectedyear);
                String strDay = String.valueOf(selectedday);
                String strMonth = String.valueOf(selectedmonth);
                String strYear = String.valueOf(selectedyear);

                if(strDay.length() < 2){
                    strDay = "0" + strDay;
                }
                if(strMonth.length() < 2){
                    strMonth = "0" + strMonth;
                }

                strDate = strDay + "/" + strMonth + "/" + strYear;

                System.out.println("strdate = " + strDate);

                dateET.setText(strDate);
            }
        }, yearNow, monthNow, dayNow);
        datePicker.setTitle("Select date");
        datePicker.show();
    }

    public void doneClick(View view) {
        toInsert = new ArrayList<>();
        toInsert.add(strDate);
        toInsert.add(locET.getText().toString());
        toInsert.add(oppoET.getText().toString());

        ArrayList<String> toSend = new ArrayList<>();
        toSend.add(strDate + "4h4f" + locET.getText().toString() + "4h4f"
                + oppoET.getText().toString() + "4h4f" + MyClientID.myTeamID);

        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("typeSending", "TEAMFIXTURE");
        intent.putExtra("CLASS", toSend);
        this.startService(intent);
    }

    /*
    * Is alerted when the IntentService broadcasts TRANSACTION_DONE_SCADD
    */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Server database updated.", Toast.LENGTH_SHORT).show();

            insert();
        }

    };

    public void insert(){
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();
        TeamFixtures tf = new TeamFixtures();
        tf.setFixtureDate(toInsert.get(0));
        tf.setFixtureLocation(toInsert.get(1));
        tf.setAwayTeam(toInsert.get(2));
        tf.setHomeTeam(MyClientID.myTeamID);

        tfRepo.insert(tf);

        Intent intent = new Intent(this, CreateNewTeam.class);
        startActivity(intent);
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
}
