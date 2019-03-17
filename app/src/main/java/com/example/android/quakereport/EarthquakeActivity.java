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

//Remember to import the right Loader class. It should be import android.content.AsyncTaskLoader; instead of import android.support.v4.content.AsyncTaskLoader;.
//See: https://github.com/udacity/ud843-QuakeReport/commit/78e07dce2ab5ed3a7df2b254b853a7cc7ad0b0e8

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.android.quakereport.databinding.EarthquakeActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private EarthquakeActivityBinding binding;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.earthquake_activity);

        //See: https://developer.android.com/training/basics/network-ops/connecting.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
        //Use: https://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#DetermineConnection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            binding.loadingIndicator.setVisibility(View.GONE);
            binding.emptyView.setText(R.string.no_internet_connection);
            return;
        }

        earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this, new ArrayList<Earthquake>());
        binding.list.setAdapter(earthquakeAdapter);

        //See: https://developer.android.com/reference/android/widget/AdapterView.html#setEmptyView(android.view.View)
        //See: https://material.io/design/communication/empty-states.html
        binding.list.setEmptyView(binding.emptyView);

        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = earthquakeAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    //See: https://developer.android.com/guide/components/loaders.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#starting
    //See: https://developer.android.com/reference/android/app/LoaderManager.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
    //See: https://developer.android.com/reference/android/app/LoaderManager.LoaderCallbacks.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics
    //Use: https://www.concretepage.com/android/android-asynctaskloader-example-with-listview-and-baseadapter
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, QueryUtils.SAMPLE_JSON_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakeList) {
        binding.loadingIndicator.setVisibility(View.GONE);
        binding.emptyView.setText(R.string.no_earthquakes);

        earthquakeAdapter.clear();
        if (earthquakeList != null && !earthquakeList.isEmpty()) {
            earthquakeAdapter.addAll(earthquakeList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        earthquakeAdapter.clear();
    }
}
