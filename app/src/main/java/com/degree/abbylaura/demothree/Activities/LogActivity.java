package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class LogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_activity);

    }


    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }
}