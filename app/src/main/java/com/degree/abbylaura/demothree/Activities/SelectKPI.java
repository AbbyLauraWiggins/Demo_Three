package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 13/03/2018.
 */

public class SelectKPI extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_kpi_activity);

        setButtons();
    }

    private void setButtons(){
        ArrayList<Button> buttons = new ArrayList<Button>();

        Button buttonsTackles = findViewById(R.id.buttonsTackles);
        buttons.add(buttonsTackles);
        Button buttonuTackles = findViewById(R.id.buttonuTackles);
        buttons.add(buttonuTackles);
        Button buttonsCarries = findViewById(R.id.buttonsCarries);
        buttons.add(buttonsCarries);
        Button buttonuCarries = findViewById(R.id.buttonuCarries);
        buttons.add(buttonuCarries);
        Button buttonsPasses = findViewById(R.id.buttonsPasses);
        buttons.add(buttonsPasses);
        Button buttonuPasses = findViewById(R.id.buttonuPasses);
        buttons.add(buttonuPasses);
        Button buttonHandlingErrors = findViewById(R.id.buttonHandlingErrors);
        buttons.add(buttonHandlingErrors);
        Button buttonPenalties = findViewById(R.id.buttonPenalties);
        buttons.add(buttonPenalties);
        Button buttonYellowCards = findViewById(R.id.buttonYellowCards);
        buttons.add(buttonYellowCards);
        Button buttonTriesScored = findViewById(R.id.buttonTriesScored);
        buttons.add(buttonTriesScored);
        Button buttonTurnoversWon = findViewById(R.id.buttonTurnoversWon);
        buttons.add(buttonTurnoversWon);
        Button buttonsThrowIns = findViewById(R.id.buttonsThrowIns);
        buttons.add(buttonsThrowIns);
        Button buttonuThrowIns = findViewById(R.id.buttonuThrowIns);
        buttons.add(buttonuThrowIns);
        Button buttonsLineOutTakes = findViewById(R.id.buttonsLineOutTakes);
        buttons.add(buttonsLineOutTakes);
        Button buttonuLineOutTakes = findViewById(R.id.buttonuLineOutTakes);
        buttons.add(buttonuLineOutTakes);
        Button buttonsKicks = findViewById(R.id.buttonsKicks);
        buttons.add(buttonsKicks);
        Button buttonuKicks = findViewById(R.id.buttonuKicks);
        buttons.add(buttonuKicks);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


        for(Button b: buttons){
            android.view.ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
            layoutParams.width = screenWidth/2;
            layoutParams.height = screenHeight/10;
            b.setLayoutParams(layoutParams);
            b.setVisibility(View.VISIBLE);
            //setListener(b);
        }

        RelativeLayout textViewContainer = findViewById(R.id.textviewContainer);
        android.view.ViewGroup.LayoutParams layoutParams = textViewContainer.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenHeight/10;
        textViewContainer.setLayoutParams(layoutParams);

    }
}
