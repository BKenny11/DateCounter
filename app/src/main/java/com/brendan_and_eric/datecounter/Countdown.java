package com.brendan_and_eric.datecounter;

public class Countdown {
    private String mEvent;
    private String mDate;
    private String mDaysLeft;

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        this.mEvent = event;
    }

    public String getDate() {
        return mDate;
    }

<<<<<<< HEAD
    public void setDate(String date) {
        this.mDate = date;
=======
    private void initializeData() {
        mCountdowns = new ArrayList<Countdown>();
        mCountdowns.add(new Countdown("Birthday", "September 15", "352 days"));
        mCountdowns.add(new Countdown("Anniversary", "October 6", "9 days"));
        mCountdowns.add(new Countdown("Vacation", "October 2", "5 days"));
>>>>>>> 0306668a5b879008e223ff10fce0f9d3d6ef194a
    }

    public String getDaysLeft() {
        return mDaysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.mDaysLeft = daysLeft;
    }
}
