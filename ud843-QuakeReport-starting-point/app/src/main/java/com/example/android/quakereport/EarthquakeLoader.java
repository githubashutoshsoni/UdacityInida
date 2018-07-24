package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null)
            return null;
        else {
            List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
            return earthquakes;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

