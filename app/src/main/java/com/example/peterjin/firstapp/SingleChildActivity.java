package com.example.peterjin.firstapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class SingleChildActivity extends ActionBarActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_child);

        /*
        // Create a new Fragment to be placed in the activity layout
        Fragment childFragment = new ChildDetailFragment();
        childFragment.setArguments(getIntent().getExtras());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, childFragment, null).commit();
        */

        // Instantiate a ViewPager and a PagerAdapter.
        Intent intent = getIntent();
        int pos = intent.getIntExtra(ChildrenListAdapter.EXTRA_MESSAGE_CURRENT_ITEM, 0);
        ArrayList<Child> children = (ArrayList<Child>)intent.getSerializableExtra(ChildrenListAdapter.EXTRA_MESSAGE_CHILDREN);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ChildDetailPagerAdapter(getSupportFragmentManager(), children);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(pos);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_child, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
