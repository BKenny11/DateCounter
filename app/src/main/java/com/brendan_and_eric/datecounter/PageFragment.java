package com.brendan_and_eric.datecounter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PageFragment extends Fragment {

    private RecyclerView mCountdownRecyclerView;
    //ArrayList<Countdown> countdowns = new ArrayList<>();
    //CountdownAdapter mAdapter;

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

        //mCountdownRecyclerView = (RecyclerView) view.findViewById(R.id.countdown_rv);
        //LinearLayoutManager llm = new LinearLayoutManager(getContext());
        //mCountdownRecyclerView.setLayoutManager(llm);

        //mAdapter = new CountdownAdapter(countdowns);
        //mCountdownRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public class CountdownAdapter extends RecyclerView.Adapter<CountdownAdapter.CountdownViewHolder>{

        public class CountdownViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView event;
            TextView date;
            TextView days;

            CountdownViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.countdown_cv);
                event = (TextView)itemView.findViewById(R.id.countdown_event_name);
                date = (TextView)itemView.findViewById(R.id.countdown_date);
                days = (TextView)itemView.findViewById(R.id.countdown_days);
            }
        }

        List<Countdown> countdowns;

        CountdownAdapter(List<Countdown> countdowns){
            this.countdowns = countdowns;
        }

        @Override
        public int getItemCount() {
            return countdowns.size();
        }

        @Override
        public CountdownViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_page, viewGroup, false);
            CountdownViewHolder cvh = new CountdownViewHolder(v);
            return cvh;
        }

        @Override
        public void onBindViewHolder(CountdownViewHolder countdownViewHolder, int i) {
            countdownViewHolder.event.setText(countdowns.get(i).event);
            countdownViewHolder.date.setText(countdowns.get(i).date);
            countdownViewHolder.days.setText(countdowns.get(i).daysLeft);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}