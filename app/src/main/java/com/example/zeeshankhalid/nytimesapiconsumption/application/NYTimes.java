package com.example.zeeshankhalid.nytimesapiconsumption.application;

import android.app.Application;

public class NYTimes extends Application {

    private static NYTimes nyTimes;

    public static NYTimes getApplication()
    {
        return nyTimes;
    }

    @Override public void onCreate() {
        super.onCreate();
        nyTimes = this;
    }
}
