package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    private static final DecimalFormat magnitudeFormat = new DecimalFormat("0.0");

    private double magnitudeRaw;
    private String magnitude;
    private String location;
    private String nearBy;
    private long dateRaw;
    private Date date;

    Earthquake(double magnitude, String location, long dateRaw) {
        setMagnitudeRaw(magnitude);
        setLocation(location);
        setDateRaw(dateRaw);
    }

    public String getMagnitude() {
        return magnitude;
    }

    public double getMagnitudeRaw() {
        return magnitudeRaw;
    }

    public Earthquake setMagnitudeRaw(double magnitudeRaw) {
        this.magnitudeRaw = magnitudeRaw;
        this.magnitude = magnitudeFormat.format(magnitudeRaw);
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
