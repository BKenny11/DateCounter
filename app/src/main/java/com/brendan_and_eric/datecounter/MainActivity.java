package com.brendan_and_eric.datecounter;

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

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CDCardAdapter CDAdapter = new CDCardAdapter();
    CUCardAdapter CUAdapter = new CUCardAdapter();

    int mCounter;
    public static ArrayList<Countdown> mData = new ArrayList();
    public static ArrayList<Countup> mData2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore dataStore = DataStore.get(this);

        mCounter = dataStore.getNumTimesRun();
        mCounter++;
        mData = dataStore.getCountdowns();
        mData2 = dataStore.getCountups();
        //Both Creates and updates the event cards when created
        if (CDAdapter.getItemCount() ==0) {

            for (int i = 1; i < mData.size(); i++) {


                Date now = Calendar.getInstance().getTime();

                Date date2 = Calendar.getInstance().getTime();
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    date2 = format.parse(mData.get(i).getDate());
                    Log.e("this",date2.toString());
                }catch (Exception exception){
                    Log.e("this","didnt work");
                }
                DateTime dater1 = new DateTime(now);
                DateTime dater2 = new DateTime(date2);
                int DaysBetween = Days.daysBetween(dater1, dater2).getDays();

                String DaysBetweenString = String.valueOf(Math.abs(DaysBetween+1));



                CDAdapter.addItem(mData.get(i).getEvent(), mData.get(i).getDate(), DaysBetweenString);
            }
        }
        if (CUAdapter.getItemCount() ==0) {
            for (int i = 1; i < mData2.size(); i++) {

                Date now = Calendar.getInstance().getTime();

                Date date2 = Calendar.getInstance().getTime();
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    date2 = format.parse(mData2.get(i).getDate());
                    Log.e("this",date2.toString());
                }catch (Exception exception){
                    Log.e("this","didnt work");
                }
                DateTime dater1 = new DateTime(now);
                DateTime dater2 = new DateTime(date2);
                int DaysBetween = Days.daysBetween(dater1, dater2).getDays();

                String DaysBetweenString = String.valueOf(Math.abs(DaysBetween));


                CUAdapter.addItem(mData2.get(i).getEvent(), mData2.get(i).getDate(), DaysBetweenString);
            }
        }
        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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




        t.start();
    }

    //If app is open for 24 hours, it will update the data to show it.
    Thread t = new Thread() {
        @Override
        public void run() {
            while(true) {
                try {

                    Thread.sleep(1000*60*60*24);
                    for(int i = 1; i < mData.size(); i++){
                        int NewDays = Integer.valueOf(mData.get(i).getDaysLeft());
                        NewDays--;
                        mData.get(i).setDaysLeft(String.valueOf(NewDays));
                    }
                    for(int i = 1; i < mData2.size(); i++){
                        int NewDays = Integer.valueOf(mData2.get(i).getDaysAgo());
                        NewDays++;
                        mData2.get(i).setDaysAgo(String.valueOf(NewDays));
                    }
                } catch (InterruptedException ie) {
                }
            }
        }
    };
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
        }else{
            Intent intent = new Intent(this, InstructionsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.addButton) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPause(){
        super.onPause();
        DataStore dataStore = DataStore.get(this);
        dataStore.setNumTimesRun(mCounter);
        dataStore.commitChanges(this);
    }
//Stores Event when added
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE) != null) {

            String message = intent.getStringExtra(AddActivity.EXTRA_EVENT_TITLE);
            String type = intent.getStringExtra(AddActivity.EXTRA_EVENT_TYPE);
            String date = intent.getStringExtra(AddActivity.EXTRA_EVENT_DATE);
            String days = intent.getStringExtra(AddActivity.EXTRA_EVENT_DIFFERENCE);
            if (type.contains("false")) {
                CDAdapter.addItem(message, date, days);
                Countdown countdown = new Countdown();
                countdown.setDate(date);
                countdown.setEvent(message);
                countdown.setDaysLeft(days);
                mData.add(countdown);
            }else if (type.contains("true")){
                CUAdapter.addItem(message, date, days);
                Countup countup = new Countup();
                countup.setDate(date);
                countup.setEvent(message);
                countup.setDaysAgo(days);
                mData2.add(countup);
            }
        }
    }
}