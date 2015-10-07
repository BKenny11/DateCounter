<<<<<<< HEAD
package com.brendan_and_eric.datecounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PageFragment2 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    public static RecyclerView.Adapter mAdapter;
    List<Countup> mCountupsData = new ArrayList<Countup>();
    List<Countup> mCountups = new ArrayList<Countup>();

    PopupWindow popUp;

    public static PageFragment2 newInstance() {
        return new PageFragment2();
    }

    public PageFragment2() {
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
        View view = inflater.inflate(R.layout.fragment_page2, container, false);

        DataStore dataStore = DataStore.get(getContext());
        mCountupsData = dataStore.getData2();

        mCountups = CUCardAdapter.mCountups;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cu_rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PopupActivity.class);
                int pos = position;
                intent.putExtra("pos", pos);
                Countup countup = CUCardAdapter.mCountups.get(pos);
                String event = countup.getEvent();
                intent.putExtra("event", event);
                intent.putExtra("isCountup", true);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                deleteItem(mCountups, position);
                deleteItem(mCountupsData, position);
            }
        }));

        mAdapter = new CUCardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void deleteItem(List<Countup> array, int position){
        array.remove(position);
        mAdapter.notifyItemRangeRemoved(0, array.size());
    }
=======
package com.brendan_and_eric.datecounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class PageFragment2 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    PopupWindow popUp;

    public static PageFragment2 newInstance() {
        return new PageFragment2();
    }

    public PageFragment2() {
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
        View view = inflater.inflate(R.layout.fragment_page2, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cu_rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(getActivity(), "onClick " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                startActivity(new Intent(getActivity(), PopupActivity.class));
            }
        }));

        mAdapter = new CUCardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
>>>>>>> 71c2c6fd7b97cdbb1dec2e80381bbbd44f0e14c9
}