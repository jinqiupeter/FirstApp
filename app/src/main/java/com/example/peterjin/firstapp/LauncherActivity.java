package com.example.peterjin.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.peterjin.firstapp.widets.XListView;

import java.util.ArrayList;

public class LauncherActivity extends MyListActivity {
    public final static String EXTRA_MESSAGE_LOST_CHILDREN = "LauncherActivity_EXTRA_MESSAGE_LOST_CHILDREN";
    public final static String EXTRA_MESSAGE_HOMELESS_CHILDREN = "LauncherActivity_EXTRA_MESSAGE_HOMELESS_CHILDREN";
    private ArrayList<Child> lost_children;
    private ArrayList<Child> homeless_children;

    @Override
    public void addChild(Child child, String url) {
        if (url.contains("lost_children")){
            lost_children.add(child);
        }else if (url.contains("homeless_children")) {
            homeless_children.add(child);
        }
    }

    @Override
    public void dataFetched(String url){
        if (url.contains("lost_children")){
            addLostChildrenFrags();
        }else if (url.contains("homeless_children")) {
            addHomelessChildrenFrags();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (savedInstanceState != null) {
            return;
        }

        lost_children = new ArrayList<>();
        String url1 = getString(R.string.default_url);
        url1 += getString(R.string.lost_children_path);
        new JSonArrayGetter(this, true).execute(url1);

        /*
        homeless_children = new ArrayList<>();
        String url2 = getString(R.string.default_url);
        url2 += getString(R.string.homeless_children_path);
        new JSonArrayGetter(this, true).execute(url2);
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

    // When user clicks button, calls AsyncTask.
    // Before attempting to fetch the URL, makes sure that there is a network connection.
    public void all_lost_children(View view) {
        Intent intent = new Intent(this, JiaXunBaoBeiActivity.class);
        intent.putExtra(EXTRA_MESSAGE_LOST_CHILDREN, lost_children);
        startActivity(intent);
    }

    //initiate ChildSummaryFragment and add to lost_children
    private void addLostChildrenFrags() {
        ArrayList<Child> children_with_image = new ArrayList<>();
        for (int i = 0; i < lost_children.size(); i ++) {
            if (!lost_children.get(i).getValue(Child.IMAGE_IDX).isEmpty()) {
                children_with_image.add(lost_children.get(i));
            }
        }
        ChildrenListAdapter adapter = new ChildrenListAdapter(this, children_with_image);
        ListView listView = (ListView) findViewById(R.id.lost_children);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    //initiate ChildSummaryFragment and add to homeless_children
    private void addHomelessChildrenFrags(){

    }
}
