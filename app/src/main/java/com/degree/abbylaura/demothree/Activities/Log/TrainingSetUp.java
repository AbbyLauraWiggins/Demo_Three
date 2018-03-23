package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by abbylaura on 22/03/2018.
 */

public class TrainingSetUp extends Activity{

    Button addButton;
    EditText dateET, timeET, setsET, repsET;
    Spinner exerciseSpinner;

    String strDate, strTime, selectedExercise;
    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    TextView tableText;

    ArrayList<String> exercisesSelected, repsNsets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_setup_activity);

        exercisesSelected = new ArrayList<>();
        repsNsets = new ArrayList<>();

        tableText = findViewById(R.id.tableLayout);
        addButton = findViewById(R.id.addButton);
        dateET = findViewById(R.id.dateEditText);
        timeET = findViewById(R.id.timeEditText);
        setsET = findViewById(R.id.setsEditText);
        repsET = findViewById(R.id.repsEditText);
        exerciseSpinner = findViewById(R.id.exerciseSpinner);

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        setLayout();
        setBottomBar();
        setSpinner();
    }

    private void setSpinner(){
        String[] scExercises = new String[13];
        scExercises[0] = "Deadlifts";
        scExercises[1] = "DeadliftJumps";
        scExercises[2] = "BackSquat";
        scExercises[3] = "BackSquatJumps";
        scExercises[4] = "GobletSquat";
        scExercises[5] = "BenchPress";
        scExercises[6] = "MilitaryPress";
        scExercises[7] = "SupineRow";
        scExercises[8] = "ChinUps";
        scExercises[9] = "Trunk";
        scExercises[10] = "RDL";
        scExercises[11] = "SplitSquat";
        scExercises[12] = "FourWayArms";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, scExercises);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(adapter);

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedExercise = (exerciseSpinner.getSelectedItem().toString());
                System.out.println("spinner selected: " + selectedExercise);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    public void setLayout(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenWidth = screenWidth - (screenWidth/30);
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        RelativeLayout rl = findViewById(R.id.relativeLayoutTop);
        android.view.ViewGroup.LayoutParams layoutParams = rl.getLayoutParams();
        layoutParams.height = (int) (screenHeight/5);
        rl.setLayoutParams(layoutParams);
        rl.setVisibility(View.VISIBLE);

        barSize = screenHeight/20;

        layoutParams = setsET.getLayoutParams();
        layoutParams.width = (int) (screenWidth/2.2);
        setsET.setLayoutParams(layoutParams);
        setsET.setVisibility(View.VISIBLE);

        layoutParams = repsET.getLayoutParams();
        layoutParams.width = (int) (screenWidth/2.2);
        repsET.setLayoutParams(layoutParams);
        repsET.setVisibility(View.VISIBLE);

        layoutParams = dateET.getLayoutParams();
        layoutParams.width = (int) (screenWidth/2.2);
        dateET.setLayoutParams(layoutParams);
        dateET.setVisibility(View.VISIBLE);

        layoutParams = timeET.getLayoutParams();
        layoutParams.width = (int) (screenWidth/2.2);
        timeET.setLayoutParams(layoutParams);
        timeET.setVisibility(View.VISIBLE);


        layoutParams = exerciseSpinner.getLayoutParams();
        layoutParams.width = (int) (screenWidth/1.6);
        exerciseSpinner.setLayoutParams(layoutParams);
        exerciseSpinner.setVisibility(View.VISIBLE);
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
                yearSetArr[0] = selectedyear;
                monthSetArr[0] = selectedmonth;
                daySetArr[0] = selectedday;
            }
        }, yearNow, monthNow, dayNow);
        datePicker.setTitle("Select date");
        datePicker.show();

        String strDay = String.valueOf(daySetArr[0]);
        String strMonth = String.valueOf(monthSetArr[0]);
        String strYear = String.valueOf(yearSetArr[0]);

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

    public void onChooseTime(View view) {
        Calendar curTime = Calendar.getInstance();
        final int hour = curTime.get(Calendar.HOUR_OF_DAY);
        int minute = curTime.get(Calendar.MINUTE);

        final int[] setHour = new int[1];
        final int[] setMin = new int[1];


        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tim, int hourOfDay, int minute) {
                System.out.println(hourOfDay + ":" + minute);
                setHour[0] = hourOfDay;
                setMin[0] = minute;
            }
        }, hour, minute, true);
        timePicker.setTitle("Select Time");
        timePicker.show();

        System.out.print("set hour: set min" + setHour[0] + ": " + setMin[0]);

        strTime = String.valueOf(setHour[0]) + ":" + String.valueOf(setMin[0]);

        System.out.println("strTime = " + strTime);

        timeET.setText(strTime);
    }

    public void onAddClick(View view) {



        if(dateET.getText().equals("Choose date")){
            Toast.makeText(this, "Please select valid date.", Toast.LENGTH_SHORT).show();
        } else if(timeET.getText().equals("Choose date")){
            Toast.makeText(this, "Please select valid time.", Toast.LENGTH_SHORT).show();
        } else if(setsET.getText().equals("Number of sets")){
            Toast.makeText(this, "Please select valid number of sets.", Toast.LENGTH_SHORT).show();
        } else if(repsET.getText().equals("Number of reps")){
            Toast.makeText(this, "Please select valid number of reps.", Toast.LENGTH_SHORT).show();
        } else{
            exercisesSelected.add(selectedExercise);

            String strSets = String.valueOf(setsET.getText());
            int sets = Integer.valueOf(strSets);
            String strReps = String.valueOf(repsET.getText());

            String toAdd = "";
            for(int i = 0; i < sets; i ++){
                toAdd = toAdd + strReps + ":/,";
            }

            repsNsets.add(toAdd);

            String toShow = selectedExercise + ": " + strSets + " x " + strReps;
            tableText.append(toShow + "\n");

            selectedExercise = "";

        }


    }
    public void onSubmitClick(View view) {
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
