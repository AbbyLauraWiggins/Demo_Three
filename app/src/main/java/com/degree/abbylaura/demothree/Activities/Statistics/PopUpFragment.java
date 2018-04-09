package com.degree.abbylaura.demothree.Activities.Statistics;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;

import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 03/02/2018.
 *
 * Going to be a listView so will extend listFragment
 */

public class PopUpFragment extends ListFragment {

    ArrayList<Integer> selected;

    public static PopUpFragment newInstance(String[] items) {

        PopUpFragment f = new PopUpFragment();

        Bundle b = new Bundle();
        b.putStringArray("items", items);
        f.setArguments(b);
        return f;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] items = getArguments().getStringArray("items");

        ArrayAdapter<String> connectArrayToListView = new
                ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                items);

        //connect listview to adapter
        setListAdapter(connectArrayToListView);

        Button done = new Button(getActivity());
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(getActivity(), AnalyseStats.class);

                intent.putExtra("list", selected);

                startActivity(intent);
            }
        });

    }




    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //add that item to returning array
        selected.add(position);
    }


}