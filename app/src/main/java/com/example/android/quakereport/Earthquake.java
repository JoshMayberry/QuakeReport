package com.example.android.quakereport;

public class Earthquake {

    private String magnitude;
    private String location;
    private String date;

    Earthquake(String magnitude, String location, String date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public Earthquake setDate(String date) {
        this.date = date;
        return this;
    }
}
