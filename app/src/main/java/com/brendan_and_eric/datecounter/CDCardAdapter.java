package com.brendan_and_eric.datecounter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CDCardAdapter extends RecyclerView.Adapter<CDCardAdapter.ViewHolder> {

    List<Countdown> mCountdowns;

    public CDCardAdapter() {
        super();
        mCountdowns = new ArrayList<>();
        Countdown countdown = new Countdown();
        countdown.setEvent("My Birthday!");
        countdown.setDate("September 15");
        countdown.setDaysLeft("351 days");
        mCountdowns.add(countdown);

        countdown = new Countdown();
        countdown.setEvent("Anniversary <3");
        countdown.setDate("October 6");
        countdown.setDaysLeft("7 days");
        mCountdowns.add(countdown);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cd_rv_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Countdown countdown = mCountdowns.get(i);
        viewHolder.tvEvent.setText(countdown.getEvent());
        viewHolder.tvDate.setText(countdown.getDate());
        viewHolder.tvDaysLeft.setText(countdown.getDaysLeft());
    }

    @Override
    public int getItemCount() {
        return mCountdowns.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvEvent;
        public TextView tvDate;
        public TextView tvDaysLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEvent = (TextView)itemView.findViewById(R.id.countdown_event_name);
            tvDate = (TextView)itemView.findViewById(R.id.countdown_date);
            tvDaysLeft = (TextView)itemView.findViewById(R.id.countdown_days);
        }
    }
}
