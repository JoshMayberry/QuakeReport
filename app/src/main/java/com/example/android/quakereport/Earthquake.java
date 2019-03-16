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
    private Date date;

    private Context context;

    Earthquake(Context context, double magnitude, String location, long dateRaw) {
        this.context = context;
        setMagnitudeRaw(magnitude);
        setLocation(location);
        setDateRaw(dateRaw);
    }

    public String getMagnitude() {
        return magnitude;
    }

    public int getMagnitudeColor() {
        return magnitudeColor;
    }

    public void updateMagnitudeColor() {
        int magnitudeColorResourceId;
        switch ((int) Math.floor(magnitudeRaw)) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        magnitudeColor = ContextCompat.getColor(context, magnitudeColorResourceId);
    }

    public double getMagnitudeRaw() {
        return magnitudeRaw;
    }

    public Earthquake setMagnitudeRaw(double magnitudeRaw) {
        this.magnitudeRaw = magnitudeRaw;
        this.magnitude = magnitudeFormat.format(magnitudeRaw);
        updateMagnitudeColor();
        return this;
    }

    public Earthquake setLocation(String location) {
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

    public String getLocation() {
        return location;
    }

    public String getNearBy() {
        return nearBy;
    }

    public long getDateRaw() {
        return dateRaw;
    }

    public Earthquake setDateRaw(long dateRaw) {
        this.dateRaw = dateRaw;
        this.date = new Date(dateRaw);
        return this;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getTime() {
        return timeFormat.format(date);
    }
}
