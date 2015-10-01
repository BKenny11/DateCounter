package com.brendan_and_eric.datecounter;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    CDCardAdapter CDAdapter = new CDCardAdapter();
    CUCardAdapter CUAdapter = new CUCardAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolBarText);

        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Setup the Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // By using this method the tabs will be populated according to viewPager's count and
        // with the name from the pagerAdapter getPageTitle()
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
        tabLayout.setupWithViewPager(viewPager);


    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return PageFragment.newInstance();
                case 1:
                    return PageFragment2.newInstance();
                default:
                    return PageFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            if (position == 0)
                title = "Countdowns";
            if (position == 1)
                title = "Countups";
            return title;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
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
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        if (id == R.id.addButton) {
            Intent intent = new Intent(this, AddActivity.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE) != null) {

            String message = intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE);
            String type = intent.getStringExtra(AddActivity.EXTRA_EVENT_TYPE);

            Log.d("LOG",type);
            if (type.contains("false")) {
//                    String message = intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE);
//                    Log.d("LOG",message);
//                    CDAdapter.addItem(message);
                CDAdapter.addItem(message);
            }else if (type.contains("true")){
                CUAdapter.addItem(message);
            }
            //CDAdapter.addItem(message);
//                String type = AddActivity.EXTRA_EVENT_TYPE;
//                if (type == "true") {
//                    String message = intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE);
//                    Log.d("LOG",message);
//                    CDAdapter.addItem(message);
//                } else if (type == "false") {
//                    String message = intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE);
//                    Log.d("LOG",message);
//                    CDAdapter.addItem(message);
//                }

        }
    }
}
