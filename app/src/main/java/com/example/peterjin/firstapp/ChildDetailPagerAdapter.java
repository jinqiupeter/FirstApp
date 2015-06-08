package com.example.peterjin.firstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by peter.jin on 2015/5/27.
 */
public class ChildDetailPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Child> children;
    public ChildDetailPagerAdapter (FragmentManager fm) {
        super(fm);
        children = new ArrayList<>();
    }
    public ChildDetailPagerAdapter (FragmentManager fm, ArrayList<Child> children) {
        super(fm);
        this.children = children;
    }

    @Override
    public Fragment getItem(int pos){
        // Create a new Fragment to be placed in the activity layout
        Fragment childFragment = new ChildDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Child.EXTRA_MESSAGE_CHILD, children.get(pos));
        childFragment.setArguments(bundle);
        return childFragment;
    }

    @Override
    public int getCount() {
        return children.size();
    }
}
