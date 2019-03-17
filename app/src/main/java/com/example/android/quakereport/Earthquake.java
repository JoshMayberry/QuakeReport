package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    private static final DecimalFormat magnitudeFormat = new DecimalFormat("0.0");

    private double magnitudeRaw;
    private int magnitudeColor;
    private String magnitude;
    private String location;
    private String nearBy;
    private long dateRaw;
    private String url;
    private Date date;

    Earthquake(double magnitude, String location, long dateRaw, String url) {
        setMagnitudeRaw(magnitude);
        setLocation(location);
        setDateRaw(dateRaw);
        setUrl(url);
    }

    String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    String getMagnitude() {
        return magnitude;
    }

    int getMagnitudeColor() {
        return magnitudeColor;
    }

    void updateMagnitudeColor() {
        switch ((int) Math.floor(magnitudeRaw)) {
            case 0:
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
                break;
        }
    }

    double getMagnitudeRaw() {
        return magnitudeRaw;
    }

    Earthquake setMagnitudeRaw(double magnitudeRaw) {
        this.magnitudeRaw = magnitudeRaw;
        this.magnitude = magnitudeFormat.format(magnitudeRaw);
        updateMagnitudeColor();
        return this;
    }

    Earthquake setLocation(String location) {
        String[] locationList = location.split(" of ");

        switch (locationList.length) {
            case 2:
                this.nearBy = locationList[0];
                this.location = locationList[1];
                break;
            default:
                this.nearBy = "Near By";
                this.location = location;
        }
        return this;

    }

    String getLocation() {
        return location;
    }

    String getNearBy() {
        return nearBy;
    }

    long getDateRaw() {
        return dateRaw;
    }

    Earthquake setDateRaw(long dateRaw) {
        this.dateRaw = dateRaw;
        this.date = new Date(dateRaw);
        return this;
    }

    String getDate() {
        return dateFormat.format(date);
    }

    String getTime() {
        return timeFormat.format(date);
    }
}
