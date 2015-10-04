package com.brendan_and_eric.datecounter;

import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD

=======
>>>>>>> cac8a78a14afd96f467ea1b616c49e339593b9be
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AddActivity extends AppCompatActivity {

    public String date;

<<<<<<< HEAD
   // private static final String DIALOG_DATE = "DialogDate";
=======
    private static final String DIALOG_DATE = "DialogDate";

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
//        Button mDateButton = (Button)findViewById(R.id.dpResult);
//        mDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
//                dialog.show(manager, DIALOG_DATE);
//            }
//        });
//        Spinner spinner = (Spinner) findViewById(R.id.dialog_Notification_picker);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                    R.array.Counter_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);



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
    public void changeCountType (View view){
        Switch sv1 = (Switch)findViewById(R.id.mySwitch);
        TextView tv1 = (TextView) findViewById(R.id.eventTitle);
        if(sv1.isChecked() == true) {
            tv1.setText("Countup");
        }else{
            tv1.setText("Countdown");
        }
    }


    public final static String EXTRA_EVENT_TITLE = "com.brenken.myfirstapp.MESSAGE";
    public final static String EXTRA_EVENT_TYPE = "com.brenken.myfirstapp.TYPE";
    public final static String EXTRA_EVENT_DATE = "com.brenken.myfirstapp.DATE";
    public final static String EXTRA_EVENT_DIFFERENCE = "com.brenken.myfirstapp.DIFFERENCE";
    public void addItem (View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editNameText);
        Switch sv1 = (Switch)findViewById(R.id.mySwitch);
        Boolean type = sv1.isChecked();

        DatePicker date = (DatePicker) findViewById(R.id.Date);

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
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_EVENT_TITLE, message);
        intent.putExtra(EXTRA_EVENT_TYPE, type.toString());
        intent.putExtra(EXTRA_EVENT_DATE, dater);
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
