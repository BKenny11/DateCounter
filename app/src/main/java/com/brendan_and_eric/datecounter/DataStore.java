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
    private ArrayList<Countdown> mData;
    public static final String PREFS_NAME = "DATA_STORE_PREFERENCES";
    public static final String KEY_NUM_TIMES_RUN = "NUM_TIMES_RUN";
    public static final String KEY_ITEMS_STRING = "ITEMS_STRING";
    private int mNumTimesRun;


    public static DataStore get(Context context){
        if(sDataStore == null){
            sDataStore = new DataStore(context);
        }
        return sDataStore;
    }
    private DataStore(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mNumTimesRun = pref.getInt(KEY_NUM_TIMES_RUN, 0);

        String arrayListAsJson = pref.getString(KEY_ITEMS_STRING, "[{text:\"Use this app!\",formattedDate:\"1/1/2015\", completed:true}]");
        Gson gson = new Gson();
        mData = gson.fromJson(arrayListAsJson, new TypeToken<ArrayList<Countdown>>(){}.getType());
    }

    public int getNumTimesRun(){
        return mNumTimesRun;
    }

    public void setNumTimesRun(int numTimesRun){
        mNumTimesRun = numTimesRun;
    }

    public ArrayList<Countdown> getData() {
        return mData;
    }

    public boolean commitChanges(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new GsonBuilder().create();
        String arrayListToJson = gson.toJson(mData);

        editor.putString(KEY_ITEMS_STRING,arrayListToJson);

        boolean success = editor.commit();

        return success;
    }


}
