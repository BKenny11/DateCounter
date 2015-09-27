package com.brendan_and_eric.datecounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private static final String DIALOG_DATE = "DialogDate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button mDateButton = (Button)findViewById(R.id.dpResult);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.dialog_Notification_picker);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.Counter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



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



    public void addItem (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
