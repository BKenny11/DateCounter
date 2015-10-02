package com.brendan_and_eric.datecounter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CUCardAdapter extends RecyclerView.Adapter<CUCardAdapter.ViewHolder> {

    List<Countup> mCountups;

    public CUCardAdapter() {
        super();
        mCountups = new ArrayList<>();
        Countup countup = new Countup();
        countup.setEvent("Oil Change");
        countup.setDate("August 8");
        countup.setDaysAgo("55 days ago");
        mCountups.add(countup);

        countup = new Countup();
        countup.setEvent("Haircut");
        countup.setDate("September 23");
        countup.setDaysAgo("7 days ago");
        mCountups.add(countup);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cu_rv_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Countup countup = mCountups.get(i);
        viewHolder.tvEvent.setText(countup.getEvent());
        viewHolder.tvDate.setText(countup.getDate());
        viewHolder.tvDaysAgo.setText(countup.getDaysAgo());
    }

    @Override
    public int getItemCount() {
        return mCountups.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEvent;
        public TextView tvDate;
        public TextView tvDaysAgo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEvent = (TextView)itemView.findViewById(R.id.countup_event_name);
            tvDate = (TextView)itemView.findViewById(R.id.countup_date);
            tvDaysAgo = (TextView)itemView.findViewById(R.id.countup_days);
        }
    }
}
