package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 07/03/2018.
 */

public class MyStats extends Activity {

    LinearLayout scStat, scLog, gameStat, gameFeedback;
    ImageView scStatIV, scLogIV, gameStatIV, gameFeedbackIV;
    int iconSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_stats_activity);


        scStat = findViewById(R.id.scViewStats);
        scLog = findViewById(R.id.scLogStats);
        gameStat = findViewById(R.id.gameViewStats);
        gameFeedback = findViewById(R.id.feedbackLayout);

        scStatIV = findViewById(R.id.scViewStatsIV);
        scLogIV = findViewById(R.id.scLogStatsIV);
        gameStatIV = findViewById(R.id.gameViewStatsIV);
        gameFeedbackIV = findViewById(R.id.gameFeedbackIV);

        setLayout();

    }

    private void setLayout(){

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        iconSize = screenHeight/2;

        /*android.view.ViewGroup.LayoutParams layoutParams = scStat.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenHeight/3;
        scStat.setLayoutParams(layoutParams);
        scStat.setVisibility(View.VISIBLE);

        layoutParams = scLog.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenHeight/3;
        scLog.setLayoutParams(layoutParams);
        scLog.setVisibility(View.VISIBLE);

        layoutParams = gameStat.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenHeight/3;
        gameStat.setLayoutParams(layoutParams);
        gameStat.setVisibility(View.VISIBLE);

        layoutParams = gameFeedback.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenHeight/3;
        gameFeedback.setLayoutParams(layoutParams);
        gameFeedback.setVisibility(View.VISIBLE);*/

        iconSize = screenHeight/5;

        scStatIV.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.ic_trending_up_black_48dp);
        draw = resize(draw);
        scStatIV.setImageDrawable(draw);

        scLogIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_note_add_black_48dp);
        draw = resize(draw);
        scLogIV.setImageDrawable(draw);

        gameStatIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_trending_up_black_48dp);
        draw = resize(draw);
        gameStatIV.setImageDrawable(draw);

        gameFeedbackIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_feedback_black_48dp);
        draw = resize(draw);
        gameFeedbackIV.setImageDrawable(draw);


    }

    private Drawable resize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                iconSize, iconSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }


    public void goBack(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void onViewSC(View view) {
        Intent intent = new Intent(this, MySCStats.class);
        startActivity(intent);
    }

    public void onViewGameStats(View view) {
        Intent intent = new Intent(this, MyGameStats.class);
        startActivity(intent);
    }
}
