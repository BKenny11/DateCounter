package com.brendan_and_eric.datecounter;

import java.util.ArrayList;

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

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getDaysLeft() {
        return mDaysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.mDaysLeft = daysLeft;
    }
}
