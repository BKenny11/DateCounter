package com.brendan_and_eric.datecounter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CDCardAdapter extends RecyclerView.Adapter<CDCardAdapter.ViewHolder> {

    static final List<Countdown> mCountdowns = new ArrayList<Countdown>();

    public CDCardAdapter() {
        super();

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

    public void addItem(String title, String date){
        Countdown countdown = new Countdown();
        countdown.setEvent(title);
        countdown.setDate(date);
        countdown.setDaysLeft("351 days");
        mCountdowns.add(countdown);


    }

    @Override
    public int getItemCount() {
        return mCountdowns.size();
    }

    public List<Countdown> getItems(){
        return mCountdowns;
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
