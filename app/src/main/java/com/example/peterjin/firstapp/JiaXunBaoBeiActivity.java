package com.example.peterjin.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.example.peterjin.firstapp.widets.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JiaXunBaoBeiActivity extends MyListActivity implements XListView.IXListViewListener{
    private static String TAG = "JiaXunBaoBeiActivity";
    private ArrayList<Child> children;
    private XListView listView = null;
    private ChildrenListAdapter adapter = null;

    @Override
    public void addChild(Child child, String url){
        children.add(child);
    }

    @Override
    public void dataFetched(String url){
        /*
        //add the footer before adding the adapter, else the footer will not load!
        View footerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loading_footer, null, false);
        listView.addFooterView(footerView);
        */
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime(getTime());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jia_xun_bao_bei);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            Intent intent = getIntent();
            children = (ArrayList<Child>)intent.getSerializableExtra(LauncherActivity.EXTRA_MESSAGE_LOST_CHILDREN);
            if (children == null) {
                Log.d(TAG, "no child passed from LauncherActivity");
                return;
            }

            adapter = new ChildrenListAdapter(this, children);
            listView = (XListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setPullRefreshEnable(true);
            listView.setPullLoadEnable(true);
            listView.setAutoLoadEnable(false);
            listView.setXListViewListener(this);
            listView.setRefreshTime(getTime());
            listView.setOnItemClickListener(adapter);

            listView.autoRefresh();
       }
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

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    //implement IXListViewListerner
    @Override
    public void onRefresh() {
        String url = getString(R.string.default_url);
        url += getString(R.string.lost_children_path);
        children.clear();
        //Get json string from url and populate children
        new JSonArrayGetter(this).execute(url);
    }

    @Override
    public void onLoadMore() {
        String url = getString(R.string.default_url);
        url += getString(R.string.lost_children_path);
        int skip = children.size();
        url += "?skip=";
        url += String.valueOf(skip);

        //Get json string from url and populate children
        new JSonArrayGetter(this).execute(url);
    }
}
