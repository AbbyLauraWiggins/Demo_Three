package com.degree.abbylaura.demothree.Activities.Statistics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

import java.util.List;

/**
 * Created by abbylaura on 12/03/2018.
 */
class Choices{
    String name;
    boolean selected = false;

    public Choices(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

public class MyListAdapter extends ArrayAdapter<Choices> {

    private List<Choices> choicesList;
    private Context context;

    public MyListAdapter(List<Choices> choicesList, Context context) {
        super(context, R.layout.list_layout_one, choicesList);
        this.choicesList = choicesList;
        this.context = context;
    }

    private static class ChoiceHolder{
        public TextView choiceName;
        public CheckBox checkBox;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
        View v = convertView;

        ChoiceHolder holder = new ChoiceHolder();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_layout_one, null);

            holder.choiceName = v.findViewById(R.id.checkBoxTextView);
            holder.checkBox = v.findViewById(R.id.checkBox);

            holder.checkBox.setOnCheckedChangeListener((Selection) context);

            v.setTag(holder);
        } else {
            holder = (ChoiceHolder) v.getTag();
        }

        Choices choice = choicesList.get(pos);
        //System.out.println(choicesList.get(pos));
        holder.choiceName.setText(choice.getName());
        holder.checkBox.setChecked(choice.isSelected());

        return v;
    }
}
