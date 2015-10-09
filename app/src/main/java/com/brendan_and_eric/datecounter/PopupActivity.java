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
import android.widget.Toast;

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

        //get name of event and set it to the edit text field
        EditText editText2 = (EditText) findViewById(R.id.editNameText2);
        Intent intent3 = getIntent();

        String eventTitle = intent3.getStringExtra("event");
        editText2.setText(eventTitle);

        //set datepicker to date of event
        DatePicker date2 = (DatePicker) findViewById(R.id.Date2);
        String eventDate = intent3.getStringExtra("date");
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date = format.parse(eventDate);
            eventDate = eventDate.substring(eventDate.indexOf("/") + 1);
            eventDate = eventDate.substring(0, eventDate.indexOf("/"));
            date2.updateDate(date.getYear()+1900, date.getMonth(),Integer.valueOf(eventDate));
        }catch (Exception exception){
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
        int DaysBetween = Days.daysBetween(dater1, dater2).getDays();
        String DaysBetweenString = String.valueOf(DaysBetween);

        Intent intent2 = getIntent();
        String message = editText.getText().toString();
        int pos = intent2.getIntExtra("pos", 0);
        boolean isCountup = intent2.getBooleanExtra("isCountup", false);

        if(isCountup){
            String eventTitle = editText.getText().toString();
            if (DaysBetween < 0){
                int DaysAgo = Math.abs(Days.daysBetween(dater1, dater2).getDays());
                String DaysAgoString = String.valueOf(DaysAgo);
                //Countup
                Countup countup = CUCardAdapter.mCountups.get(pos);
                MainActivity.mData2.get(pos+1).setEvent(message);
                MainActivity.mData2.get(pos+1).setDate(dater);
                MainActivity.mData2.get(pos+1).setDaysAgo(DaysAgoString);
                countup.setEvent(message);
                countup.setDate(dater);
                countup.setDaysAgo(DaysAgoString);
                //Reordering countups
                for (int i = 0; i < CUCardAdapter.mCountups.size(); i++) {
                    int daysAgo = Integer.parseInt(countup.getDaysAgo());
                    int nextDaysAgo = Integer.parseInt(CUCardAdapter.mCountups.get(i).getDaysAgo());
                    if (daysAgo < nextDaysAgo) {
                        CUCardAdapter.mCountups.remove(pos);
                        MainActivity.mData2.remove(pos+1);

                        CUCardAdapter.mCountups.add(i, countup);
                        MainActivity.mData2.add(i+1, countup);

                        MainActivity.mData2.get(i+1).setEvent(message);
                        MainActivity.mData2.get(i+1).setDate(dater);
                        MainActivity.mData2.get(i+1).setDaysAgo(DaysBetweenString);

                        startActivity(intent);
                        return;
                    }  else if (i == pos){
                        CUCardAdapter.mCountups.set(i, countup);
                        MainActivity.mData2.set(i+1, countup);

                        PageFragment2.mAdapter.notifyItemChanged(i);
                        startActivity(intent);
                        return;
                    } else if (i == (CUCardAdapter.mCountups.size() - 1)) {
                        CUCardAdapter.mCountups.remove(pos);
                        MainActivity.mData2.remove(pos);

                        CUCardAdapter.mCountups.add(countup);
                        MainActivity.mData2.add(countup);

                        MainActivity.mData2.get(i).setEvent(message);
                        MainActivity.mData2.get(i).setDate(dater);
                        MainActivity.mData2.get(i).setDaysAgo(DaysBetweenString);

                        startActivity(intent);
                        return;
                    } else if (daysAgo >= nextDaysAgo) {
                        Log.d("CUAdapter", "Skip!");
                    }
                }

            } else if((DaysBetween == 0) && (eventTitle == message)){
                Toast.makeText(PopupActivity.this, "No changes have been made to event!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(PopupActivity.this, "Cannot make Countup after today's date!", Toast.LENGTH_SHORT).show();
                Log.d("popup", DaysBetweenString);
                return;
            }
            PageFragment2.mAdapter.notifyItemChanged(pos);
        }else {
            String eventTitle = editText.getText().toString();
            if (DaysBetween < 0){
                Toast.makeText(PopupActivity.this, "Cannot make Countdown before today's date!", Toast.LENGTH_SHORT).show();
                return;
            } else if((DaysBetween == 0) && (eventTitle == message)){
                Toast.makeText(PopupActivity.this, "No changes have been made to event!", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                //Countdown
                Countdown countdown = CDCardAdapter.mCountdowns.get(pos);
                MainActivity.mData.get(pos+1).setEvent(message);
                MainActivity.mData.get(pos+1).setDate(dater);
                MainActivity.mData.get(pos+1).setDaysLeft(DaysBetweenString);
                countdown.setEvent(message);
                countdown.setDate(dater);
                countdown.setDaysLeft(DaysBetweenString);
                //Reordering countdowns
                for (int i = 0; i < CDCardAdapter.mCountdowns.size(); i++) {
                    int daysAgo = Integer.parseInt(countdown.getDaysLeft());
                    int nextDaysAgo = Integer.parseInt(CDCardAdapter.mCountdowns.get(i).getDaysLeft());
                    if (daysAgo < nextDaysAgo) {
                        CDCardAdapter.mCountdowns.remove(pos);
                        MainActivity.mData.remove(pos+1);

                        CDCardAdapter.mCountdowns.add(i, countdown);
                        MainActivity.mData.add(i+1, countdown);

                        MainActivity.mData.get(i+1).setEvent(message);
                        MainActivity.mData.get(i+1).setDate(dater);
                        MainActivity.mData.get(i+1).setDaysLeft(DaysBetweenString);

                        startActivity(intent);
                        return;
                    } else if (i == pos){
                        CDCardAdapter.mCountdowns.set(i, countdown);
                        MainActivity.mData.set(i+1, countdown);

                        PageFragment.mAdapter.notifyItemChanged(i);
                        startActivity(intent);
                        return;
                    } else if (i == (CDCardAdapter.mCountdowns.size() - 1)) {
                        CDCardAdapter.mCountdowns.remove(pos);
                        MainActivity.mData.remove(pos);

                        CDCardAdapter.mCountdowns.add(countdown);
                        MainActivity.mData.add(countdown);

                        MainActivity.mData.get(i).setEvent(message);
                        MainActivity.mData.get(i).setDate(dater);
                        MainActivity.mData.get(i).setDaysLeft(DaysBetweenString);

                        startActivity(intent);
                        return;
                    } else if (daysAgo >= nextDaysAgo) {
                        Log.d("CDAdapter", "Skip!");
                    }
                }
            }
        }
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