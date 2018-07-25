package com.example.android.quakereport;

import java.net.URL;

public class Earthquake {
    private String mMagnitude;
    private String place;
    private Long time;
    private String url;

    public Earthquake(String mMagnitude, String place, Long time, String url) {
        this.mMagnitude = mMagnitude;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return place;
    }

    public Long getTime() {
        return time;
    }


    public String getURL() {
        return url;
    }
}
