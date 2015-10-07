package com.brendan_and_eric.datecounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PageFragment extends Fragment {

    static RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    static RecyclerView.Adapter mAdapter;
    List<Countdown> mCountdownsData = new ArrayList<Countdown>();
    List<Countdown> mCountdowns = new ArrayList<Countdown>();
    //public final static String EXTRA_EVENT_POS = "com.brenken.myfirstapp.POS";

    public static PageFragment newInstance() {
        return new PageFragment();
    }

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        DataStore dataStore = DataStore.get(getContext());
        mCountdownsData = dataStore.getCountdowns();

        mCountdowns = CDCardAdapter.mCountdowns;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cd_rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PopupActivity.class);
                int pos = position;
                intent.putExtra("pos", pos);
                Countdown countdown = CDCardAdapter.mCountdowns.get(pos);
                String event = countdown.getEvent();
                intent.putExtra("event", event);
                intent.putExtra("isCountup", false);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                deleteItem(mCountdowns, position);
                deleteItem(mCountdownsData, position);
            }
        }));

        mAdapter = new CDCardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void deleteItem(List<Countdown> array, int position){
        array.remove(position);
        mAdapter.notifyItemRangeRemoved(0, array.size());
    }
}