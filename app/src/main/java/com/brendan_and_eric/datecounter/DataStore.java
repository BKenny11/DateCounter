package com.brendan_and_eric.datecounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
    private ArrayList<Countup> mData2;
    public static final String KEY_ITEMS_STRING = "ITEMS_STRING";
    public static final String KEY_ITEMS_STRING2 = "ITEMS_STRING2";
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

        mData = new ArrayList<Countdown>();
        mData2 = new ArrayList<Countup>();
        mNumTimesRun = pref.getInt(KEY_NUM_TIMES_RUN, 0);


        String arrayListAsJson = pref.getString(KEY_ITEMS_STRING, "[{text:\"Use this app!\",formattedDate:\"1/1/2015\", completed:true}]");
        String arrayListAsJson2 = pref.getString(KEY_ITEMS_STRING2, "[{text:\"Use this app!\",formattedDate:\"1/1/2015\", completed:true}]");
        Gson gson = new Gson();
        mData = gson.fromJson(arrayListAsJson, new TypeToken<ArrayList<Countdown>>() {
        }.getType());

        mData2 = gson.fromJson(arrayListAsJson2, new TypeToken<ArrayList<Countup>>() {
        }.getType());

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

    public ArrayList<Countup> getData2() {
        return mData2;
    }

    public boolean commitChanges(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_NUM_TIMES_RUN, mNumTimesRun);

        Gson gson = new GsonBuilder().create();
        String arrayListToJson = gson.toJson(mData);
        String arrayListToJson2 = gson.toJson(mData2);
        
        editor.putString(KEY_ITEMS_STRING, arrayListToJson);
        editor.putString(KEY_ITEMS_STRING2, arrayListToJson2);

        boolean success = editor.commit();

        return success;
    }


}
