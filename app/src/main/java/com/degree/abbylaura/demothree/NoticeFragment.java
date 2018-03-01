package com.degree.abbylaura.demothree;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by abbylaura on 28/02/2018.
 */

public class NoticeFragment extends Fragment {

    public static NoticeFragment newInstance(String text, String clientID, String date) {

        NoticeFragment f = new NoticeFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putString("id", clientID);
        b.putString("date", date);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.notice_fragment, container, false);

        ((TextView) v.findViewById(R.id.noticeFragmentInputText)).setText(getArguments().getString("text"));
        ((TextView) v.findViewById(R.id.clientNameTextView)).setText(getArguments().getString("id"));
        ((TextView) v.findViewById(R.id.dateTextView)).setText(getArguments().getString("date"));


        return v;
    }
}
