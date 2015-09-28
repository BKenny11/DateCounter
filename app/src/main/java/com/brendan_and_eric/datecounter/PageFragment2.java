package com.brendan_and_eric.datecounter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageFragment2 extends Fragment {

    private RecyclerView mCountupRecyclerView;

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

        //mCountupRecyclerView = (RecyclerView) view.findViewById(R.id.countup_rv);
        //mCountupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}