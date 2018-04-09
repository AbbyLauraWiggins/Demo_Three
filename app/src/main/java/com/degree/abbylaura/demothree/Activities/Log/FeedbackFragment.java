package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Schema.Feedback;
import com.degree.abbylaura.demothree.R;


public class FeedbackFragment extends Fragment {

    public static FeedbackFragment newInstance(
            String name, String fixture, String text, String attack, String def, String effort, String overall) {


        FeedbackFragment f = new FeedbackFragment();

        Bundle b = new Bundle();
        b.putString("name", name);
        b.putString("fixture", fixture);
        b.putString("text", text);
        b.putString("attack", attack);
        b.putString("def", def);
        b.putString("effort", effort);
        b.putString("overall", overall);


        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.feedback_fragment, container, false);

        ((TextView) v.findViewById(R.id.playerName)).setText(getArguments().getString("name"));
        ((TextView) v.findViewById(R.id.fixtureDate)).setText(getArguments().getString("fixture"));
        ((TextView) v.findViewById(R.id.feedbackTextTV)).setText(getArguments().getString("text"));
        ((TextView) v.findViewById(R.id.attack)).setText("Attack: " + getArguments().getString("attack"));
        ((TextView) v.findViewById(R.id.defence)).setText("Defence: " + getArguments().getString("def"));
        ((TextView) v.findViewById(R.id.effort)).setText("Effort: " + getArguments().getString("effort"));
        ((TextView) v.findViewById(R.id.overall)).setText("Overall: " + getArguments().getString("overall"));

        return v;
    }
}
