package com.example.kakashi.newsapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.TextView;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    String mURL;
    public NewsLoader(@NonNull Context context,String URL) {
        super(context);
        mURL = URL;

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        List<News> results = QueryUtils.fetchNewsData(mURL);
        return results;
    }
}
