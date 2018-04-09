package com.degree.abbylaura.demothree.Activities.SignIn;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;


public class RegisterHelp extends Activity{

    TextView prompts;
    ImageView imageView;
    String[] promptStr;
    int promptNum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_help_activity);

        prompts = findViewById(R.id.prompts);
        imageView = findViewById(R.id.iv);
        promptNum = 0;
        promptStr = new String[6];

       // imageView.setImageResource(0);
       // Drawable draw = getResources().getDrawable(R.drawable.empty);
       // draw = barresize(draw);
       // imageView.setImageDrawable(draw);


        promptStr[0] = ("Fill in the text boxes with your personal details.");

        promptStr[1] =("Use a valid email address and choose a password.");

        promptStr[2] =("Enter your full name and date of birth.");

        promptStr[3] =("The Team ID is required for you to join an existing team. You must be given this by a member of this team.");

        promptStr[4] =("To create a new team, click \"Create New Team\"");

        promptStr[5] =("Here is an example for you");


        prompts.setText(promptStr[0]);

    }


    private Drawable barresize(Drawable image) {


        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                (int) width, (int) height, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }



    public void next(View view) {
        promptNum++;

        if(promptNum == 6){
            imageView.setImageResource(0);
            Drawable draw = getResources().getDrawable(R.drawable.full);
            draw = barresize(draw);
            imageView.setImageDrawable(draw);

            Button next = findViewById(R.id.next_button);
            next.setText("Done");
        }else if(promptNum == 7){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }else{
            prompts.setText(promptStr[promptNum]);
        }
    }
}
