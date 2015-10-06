package com.brendan_and_eric.datecounter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by bkishere11 on 10/4/15.
 */
public class DataStore {
    private static DataStore sDataStore;
    private ArrayList<Countdown> mCountdowns;
    private ArrayList<Countup> mCountups;
    public static final String KEY_COUNTDOWN_STRING = "ITEMS_STRING_COUNTDOWNS";
    public static final String KEY_COUNTUP_STRING = "ITEMS_STRING_COUNTUPS";
    public static final String PREFS_NAME = "DATA_STORE_PREFERENCES";
    public static final String KEY_NUM_TIMES_RUN = "NUM_TIMES_RUN";

    private int mNumTimesRun;


    public static DataStore get(Context context){
        if(sDataStore == null){
            sDataStore = new DataStore(context);
        }
        return sDataStore;
    }
    private DataStore(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mCountdowns = new ArrayList<Countdown>();
        mCountups = new ArrayList<Countup>();
        mNumTimesRun = pref.getInt(KEY_NUM_TIMES_RUN, 0);


        String arrayListAsJson = pref.getString(KEY_COUNTDOWN_STRING, "[{text:\"Event\",Date:\"1/1/2015\", Days: 0}]");
        String arrayListAsJson2 = pref.getString(KEY_COUNTUP_STRING, "[{text:\"Event\",Date:\"1/1/2015\", Days: 0}]");
        Gson gson = new Gson();
        mCountdowns = gson.fromJson(arrayListAsJson, new TypeToken<ArrayList<Countdown>>() {
        }.getType());

        mCountups = gson.fromJson(arrayListAsJson2, new TypeToken<ArrayList<Countup>>() {
        }.getType());

    }

    public int getNumTimesRun(){
        return mNumTimesRun;
    }

    public void setNumTimesRun(int numTimesRun){
        mNumTimesRun = numTimesRun;
    }

    public ArrayList<Countdown> getCountdowns() {
        return mCountdowns;
    }

    public ArrayList<Countup> getCountups() {
        return mCountups;
    }

    public boolean commitChanges(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_NUM_TIMES_RUN, mNumTimesRun);

        Gson gson = new GsonBuilder().create();
        String arrayListCountdownsToJson = gson.toJson(mCountdowns);
        String arrayListCountupsToJson = gson.toJson(mCountups);

        editor.putString(KEY_COUNTDOWN_STRING, arrayListCountdownsToJson);
        editor.putString(KEY_COUNTUP_STRING, arrayListCountupsToJson);

        boolean success = editor.commit();

        return success;
    }


}
