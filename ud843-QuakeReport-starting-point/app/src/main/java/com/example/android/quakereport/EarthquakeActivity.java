/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>
{
    ProgressBar progressBar;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthQuakeAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

            ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mAdapter = new EarthQuakeAdapter(this, new ArrayList<Earthquake>());


        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOG_TAG, "onCreateLoader: ");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquake) {
        Log.d(LOG_TAG, "onLoadFinished: ");

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            Toast.makeText(EarthquakeActivity.this,"connected",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(EarthquakeActivity.this,"please connect to internet",Toast.LENGTH_LONG).show();

        }
        mAdapter.clear();
        mEmptyStateTextView.setText("No earthquake Data");
        if (earthquake != null && !earthquake.isEmpty()) {

            mAdapter.addAll(earthquake);
        }
        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "onLoaderReset: ");
        mAdapter.clear();
    }
}
