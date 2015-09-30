package com.brendan_and_eric.datecounter;

public class Countup {
    private String mEvent;
    private String mDate;
    private String mDaysAgo;

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        this.mEvent = event;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getDaysAgo() {
        return mDaysAgo;
    }

    public void setDaysAgo(String daysAgo) {
        this.mDaysAgo = daysAgo;
    }
}