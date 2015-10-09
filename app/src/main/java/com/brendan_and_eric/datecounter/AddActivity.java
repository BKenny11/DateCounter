package com.brendan_and_eric.datecounter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public final static String EXTRA_EVENT_TITLE = "com.brenken.myfirstapp.MESSAGE";
    public final static String EXTRA_EVENT_TYPE = "com.brenken.myfirstapp.TYPE";
    public final static String EXTRA_EVENT_DATE = "com.brenken.myfirstapp.DATE";
    public final static String EXTRA_EVENT_DIFFERENCE = "com.brenken.myfirstapp.DIFFERENCE";
    public final static String EXTRA_DATE_CREATED = "com.brendananderic.CREATED";
    public final static String EXTRA_DATE = "com.brendananderic.DATE";

    public void addItem (View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editNameText);

        Boolean type = false;

        DatePicker date = (DatePicker) findViewById(R.id.Date);

        Date now = Calendar.getInstance().getTime();

        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonth()+1);
        String year = String.valueOf(date.getYear());

        String dater = month+"/"+day+"/"+year;
        Date date2 = getDateFromDatePicker(date);

        DateTime dater1 = new DateTime(now);
        DateTime dater2 = new DateTime(date2);
        int DaysBetween = Days.daysBetween(dater1,dater2).getDays();
        if (DaysBetween > 0){
            type = false;
            String DaysBetweenString = String.valueOf(Math.abs(DaysBetween));

            intent.putExtra(EXTRA_EVENT_DIFFERENCE,DaysBetweenString);

            String message = editText.getText().toString();
            intent.putExtra(EXTRA_EVENT_TITLE, message);
            intent.putExtra(EXTRA_EVENT_TYPE, type.toString());
            intent.putExtra(EXTRA_EVENT_DATE, dater);

            startActivity(intent);
        }else if(DaysBetween == 0){
            Toast.makeText(AddActivity.this, "Cannot set event for today!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            type = true;
            String DaysBetweenString = String.valueOf(Math.abs(DaysBetween));

            intent.putExtra(EXTRA_EVENT_DIFFERENCE,DaysBetweenString);

            String message = editText.getText().toString();
            intent.putExtra(EXTRA_EVENT_TITLE, message);
            intent.putExtra(EXTRA_EVENT_TYPE, type.toString());
            intent.putExtra(EXTRA_EVENT_DATE, dater);
            intent.putExtra(EXTRA_DATE_CREATED,now);
            intent.putExtra(EXTRA_DATE,date2);
            startActivity(intent);
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
