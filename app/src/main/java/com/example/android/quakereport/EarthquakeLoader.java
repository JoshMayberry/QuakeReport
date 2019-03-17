package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader {
    private String url;
    public static final String LOG_TAG = EarthquakeLoader.class.getName();

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {

        //FOR DEBUGGING: Show loading wheel
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (url.isEmpty()) {
            return null;
        }

        // Perform the HTTP request for earthquake data and process the response.
        String jsonResponse = null;
        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException error) {
            Log.e(LOG_TAG, "Error closing input stream", error);
        }
        if (jsonResponse == null) {
            return null;
        }
        return QueryUtils.parseJSON(jsonResponse);
    }
}
