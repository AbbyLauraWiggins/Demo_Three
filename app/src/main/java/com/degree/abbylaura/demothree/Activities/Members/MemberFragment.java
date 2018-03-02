package com.degree.abbylaura.demothree.Activities.Members;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class MemberFragment extends Fragment{
    public static MemberFragment newInstance(String name, String position, String responsibilities, int colour) {

        MemberFragment f = new MemberFragment();

        Bundle b = new Bundle();
        b.putString("name", name);
        b.putString("pos", position);
        b.putString("res", responsibilities);

        if((colour%2) == 0){
            b.putString("colour", "even");
        }else{
            b.putString("colour", "false");
        }

        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.member_fragment, container, false);

        if(getArguments().getString("colour").equals("even")){
            v.setBackgroundColor(Color.LTGRAY);
        }

        ((TextView) v.findViewById(R.id.memberName)).setText(getArguments().getString("name"));
        ((TextView) v.findViewById(R.id.members_positions)).setText("Positions: " + getArguments().getString("pos"));
        ((TextView) v.findViewById(R.id.members_responsibilities)).setText(getArguments().getString("res"));



        return v;
    }
}
