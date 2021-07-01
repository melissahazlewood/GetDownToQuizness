package com.example.getdowntoquizness;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends BaseExpandableListAdapter {
    ArrayList<String> gradesList;
    private HashMap<String, ArrayList<String>> subList;

    public MainAdapter(ArrayList<String> gradesList, HashMap<String, ArrayList<String>> subList){
        this.gradesList = gradesList;
        this.subList = subList;
    }

    @Override
    public int getGroupCount() {
        return gradesList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return subList.get(gradesList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gradesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return subList.get(gradesList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //initialize view
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);

        // Initialize and assign variable
        TextView textView = convertView.findViewById(android.R.id.text1);
        // Initialize string
        String sGroup = String.valueOf(getGroup(groupPosition));
        // Set text on text view
        textView.setText(sGroup);
        // Set text style bold
        textView.setTypeface(null, Typeface.BOLD);
        // Set text color
        textView.setTextColor(Color.BLUE);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_selectable_list_item, parent, false);

        // Initialize and assign variable
        TextView textView = convertView.findViewById(android.R.id.text1);
//      Initialize string
        String sChild = String.valueOf(getChild(groupPosition, childPosition));
//      Set text on text view
        textView.setText(sChild);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), sChild, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
