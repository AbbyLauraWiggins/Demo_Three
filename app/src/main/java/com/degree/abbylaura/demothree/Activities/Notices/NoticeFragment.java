package com.degree.abbylaura.demothree.Activities.Notices;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 28/02/2018.
 */

public class NoticeFragment extends Fragment {

    public static NoticeFragment newInstance(String text, String clientID, String date, int i) {

        NoticeFragment f = new NoticeFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putString("id", clientID);
        b.putString("date", date);

        b.putInt("colour", i);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.notice_fragment, container, false);

        int colour = getArguments().getInt("colour");
        if((colour%2)==0){
            v.setBackgroundColor(Color.LTGRAY);
        }

        ((TextView) v.findViewById(R.id.noticeFragmentInputText)).setText(getArguments().getString("text"));
        ((TextView) v.findViewById(R.id.clientNameTextView)).setText(getArguments().getString("id"));
        ((TextView) v.findViewById(R.id.dateTextView)).setText(getArguments().getString("date"));


        return v;
    }
}
