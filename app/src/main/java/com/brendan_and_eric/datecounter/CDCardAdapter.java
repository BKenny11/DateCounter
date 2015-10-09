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
        viewHolder.tvDaysLeft.setText(countdown.getDaysLeft()+" days");
    }

    public void addItem(String title, String date, String days){
        Countdown countdown = new Countdown();
        countdown.setEvent(title);
        countdown.setDate(date);
        countdown.setDaysLeft(days);
        if(mCountdowns.isEmpty()){
            mCountdowns.add(countdown);
        }else {
            //Order events in ascending order
            for (int i = 0; i < mCountdowns.size(); i++) {
                int daysLeft = Integer.parseInt(countdown.getDaysLeft());
                int nextDaysLeft = Integer.parseInt(mCountdowns.get(i).getDaysLeft());
                if (daysLeft < nextDaysLeft) {
                    mCountdowns.add(i, countdown);
                    return;
                }else if (i == (mCountdowns.size()-1)){
                    mCountdowns.add(countdown);
                    return;
                }else if (daysLeft >= nextDaysLeft) {
                    Log.d("CDAdapter", "Skip!");
                }
            }
        }
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
