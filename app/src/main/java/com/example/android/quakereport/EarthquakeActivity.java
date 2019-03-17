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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = findViewById(R.id.list);

        earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(earthquakeAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = earthquakeAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(QueryUtils.SAMPLE_JSON_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
        private final String LOG_TAG = EarthquakeAsyncTask.class.getSimpleName();

        @Override
        protected List<Earthquake> doInBackground(String... urlList) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urlList.length < 1 || urlList[0] == null) {
                return null;
            }

            List<Earthquake> earthquakeList = new ArrayList<>();
            // Perform the HTTP request for earthquake data and process the response.
            for (String url : urlList) {
                String jsonResponse = null;
                try {
                    jsonResponse = QueryUtils.makeHttpRequest(url);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing input stream", e);
                }
                if (jsonResponse == null) {
                    continue;
                }
                List<Earthquake> earthquakeList_2 = QueryUtils.parseJSON(jsonResponse);
                if (earthquakeList_2 == null) {
                    continue;
                }
                earthquakeList.addAll(earthquakeList_2);
            }

            return earthquakeList;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakeList) {
            earthquakeAdapter.clear();
            if (earthquakeList != null && !earthquakeList.isEmpty()) {
                earthquakeAdapter.addAll(earthquakeList);
            }
        }
    }
}
