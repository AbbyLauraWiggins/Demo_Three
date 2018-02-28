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

    public static NoticeFragment newInstance(String text) {

        NoticeFragment f = new NoticeFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.notice_fragment, container, false);

        ((TextView) v.findViewById(R.id.noticeFragmentInputText)).setText(getArguments().getString("text"));
        return v;
    }
}
