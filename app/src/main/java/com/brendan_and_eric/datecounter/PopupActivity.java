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

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
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

        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.9));

        EditText editText2 = (EditText) findViewById(R.id.editNameText2);
        Intent intent3 = getIntent();

        String eventTitle = intent3.getStringExtra("event");

        editText2.setText(eventTitle);
        DatePicker date2 = (DatePicker) findViewById(R.id.Date2);
        String eventDate = intent3.getStringExtra("date");
        Log.e("date parameters", "Date passed: "+eventDate);
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date = format.parse(eventDate);
           // System.out.println(date);
            Log.e("date parameters", "year: "+date.getYear()+" month: "+date.getMonth()+" Day: "+date.getDay());
            date2.updateDate(date.getYear()+1900, date.getMonth(),date.getDay());
        }catch (Exception exception){
            Log.e("Date","didnt work");
        }



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

        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonth()+1);
        String year = String.valueOf(date.getYear());

        String dater = month+"/"+day+"/"+year;
        Date date2 = getDateFromDatePicker(date);

        DateTime dater1 = new DateTime(now);
        DateTime dater2 = new DateTime(date2);
        int DaysBetween = Math.abs(Days.daysBetween(dater1, dater2).getDays());
        String DaysBetweenString = String.valueOf(DaysBetween);

        Intent intent2 = getIntent();
        String message = editText.getText().toString();
        int pos = intent2.getIntExtra("pos", 0);
        boolean isCountup = intent2.getBooleanExtra("isCountup", false);
        if(isCountup){
            Countup countup = CUCardAdapter.mCountups.get(pos);
            MainActivity.mData2.get(pos + 1).setEvent(message);
            countup.setEvent(message);
            PageFragment2.mAdapter.notifyItemChanged(pos + 1);
            countup.setDate(dater);
            countup.setDaysAgo(DaysBetweenString);
        }else {
            Countdown countdown = CDCardAdapter.mCountdowns.get(pos);
            MainActivity.mData.get(pos + 1).setEvent(message);
            countdown.setEvent(message);
            PageFragment.mAdapter.notifyItemChanged(pos + 1);
            countdown.setDate(dater);
            countdown.setDaysLeft(DaysBetweenString);
        }
        startActivity(intent);
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}