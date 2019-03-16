package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

    private String magnitude;
    private String location;
    private long dateRaw;
    private Date date;

    Earthquake(String magnitude, String location, long dateRaw) {
        setMagnitude(magnitude);
        setLocation(location);
        setDateRaw(dateRaw);
    }

    public String getMagnitude() {
        return magnitude;
    }

    public Earthquake setMagnitude(String magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Earthquake setLocation(String location) {
        this.location = location;
        return this;
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
