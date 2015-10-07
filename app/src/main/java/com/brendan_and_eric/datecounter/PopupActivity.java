<<<<<<< HEAD
package com.brendan_and_eric.datecounter;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PopupActivity extends AppCompatActivity {

    public String date;

    private static final String DIALOG_DATE = "DialogDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));

        EditText editText2 = (EditText) findViewById(R.id.editNameText2);
        Intent intent3 = getIntent();
        String eventTitle = intent3.getStringExtra("event");
        editText2.setText(eventTitle);
        TextView counterSetting = (TextView) findViewById(R.id.eventTitle2);
        boolean countIsUp = intent3.getBooleanExtra("isCountup", false);
        if(countIsUp){
            counterSetting.setText(R.string.countupSetting);
        }else{
            counterSetting.setText(R.string.countdownSetting);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup, menu);
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

    public void closeWindow(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public final static String EXTRA_EVENT_TITLE = "com.brenken.myfirstapp.MESSAGE";
    public final static String EXTRA_EVENT_TYPE = "com.brenken.myfirstapp.TYPE";
    public final static String EXTRA_EVENT_DATE = "com.brenken.myfirstapp.DATE";
    public final static String EXTRA_EVENT_DIFFERENCE = "com.brenken.myfirstapp.DIFFERENCE";
    public void addItem (View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editNameText2);

        DatePicker date = (DatePicker) findViewById(R.id.Date2);

        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String formattedDate = formatter.format(now);

        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());

        String dater = month+"/"+day+"/"+year;

        try {
            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("mm/dd/yyyy");

            //Setting dates
            date1 = dates.parse(formattedDate);
            date2 = dates.parse(dater);

            String Days = getDateDiffString(date1,date2);
            long days = getDifferenceDays(date1, date2);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            String dayDifference = Long.toString(differenceDates);

            intent.putExtra(EXTRA_EVENT_DIFFERENCE,dayDifference+" days");

        } catch (Exception exception) {
            intent.putExtra(EXTRA_EVENT_DIFFERENCE, "0 days");
        }

        // DateTime dateTime1 = new DateTime(dater);
        //DateTime dateTime2 = new DateTime(formattedDate);

        //int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        // Log.e("NEW DATE COUNTER","Option 2: "+String.valueOf(weeks));
        Intent intent2 = getIntent();
        String message = editText.getText().toString();
        int pos = intent2.getIntExtra("pos", 0);
        boolean isCountup = intent2.getBooleanExtra("isCountup", false);
        if(isCountup){
            Countup countup = CUCardAdapter.mCountups.get(pos);
            MainActivity.mData2.get(pos + 1).setEvent(message);
            countup.setEvent(message);
            PageFragment2.mAdapter.notifyItemChanged(pos + 1);
        }else {
            Countdown countdown = CDCardAdapter.mCountdowns.get(pos);
            MainActivity.mData.get(pos + 1).setEvent(message);
            countdown.setEvent(message);
            PageFragment.mAdapter.notifyItemChanged(pos + 1);
        }
        //intent.putExtra(EXTRA_EVENT_TITLE, message);
        //intent.putExtra(EXTRA_EVENT_TYPE, type.toString());
        //intent.putExtra(EXTRA_EVENT_DATE, dater);
        startActivity(intent);
    }


    public String getDateDiffString(Date dateOne, Date dateTwo)
    {
        long timeOne = dateOne.getTime();
        long timeTwo = dateTwo.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        long delta = (timeTwo - timeOne) / oneDay;

        if (delta > 0) {
            return "dateTwo is " + delta + " days after dateOne";
        }
        else {
            delta *= -1;
            return "dateTwo is " + delta + " days before dateOne";
        }
    }
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
=======
package com.brendan_and_eric.datecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.4));
    }

    public void close(View view){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup, menu);
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
>>>>>>> 71c2c6fd7b97cdbb1dec2e80381bbbd44f0e14c9
