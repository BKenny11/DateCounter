package com.brendan_and_eric.datecounter;

import java.util.ArrayList;
import java.util.List;

public class Countdown {
    String event;
    String date;
    String daysLeft;

    Countdown(String event, String date, String daysLeft) {
        this.event = event;
        this.date = date;
        this.daysLeft = daysLeft;
    }

    private List<Countdown> mCountdowns;

    private void initializeData() {
        mCountdowns = new ArrayList<Countdown>();
        mCountdowns.add(new Countdown("Birthday", "September 15", "352 days"));
        mCountdowns.add(new Countdown("Anniversary", "October 6", "9 days"));
        mCountdowns.add(new Countdown("Vacation", "October 2", "5 days"));
    }

}
