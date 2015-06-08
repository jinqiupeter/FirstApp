package com.example.peterjin.firstapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by peter.jin on 2015/5/24.
 */
public class ChildrenListAdapter extends ArrayAdapter implements ListView.OnItemClickListener{

    public static String EXTRA_MESSAGE_CHILDREN = "ChildrenListAdapter_children";
    public static String EXTRA_MESSAGE_CURRENT_ITEM = "ChildrenListAdapter_current_item";
    private final Activity activity;
    private final ArrayList<Child> values;
    public ChildrenListAdapter(Activity ctx, ArrayList<Child> children){
        super(ctx, -1, children);
        this.activity = ctx;
        values = children;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.fragment_child_summary, parent, false);
        Child child = values.get(pos);

        // Create a new Fragment to be placed in the activity layout
        Fragment childFragment = new ChildSummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Child.EXTRA_MESSAGE_CHILD, child);
        childFragment.setArguments(bundle);

        return childFragment.onCreateView(inflater, parent, bundle);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Child child = values.get((int)id);
        Intent intent = new Intent(activity, SingleChildActivity.class);
        intent.putExtra(EXTRA_MESSAGE_CHILDREN, values);
        intent.putExtra(EXTRA_MESSAGE_CURRENT_ITEM, (int)id);
        // intent.putExtra(Child.EXTRA_MESSAGE_CHILD, child);
        activity.startActivity(intent);
    }
}
