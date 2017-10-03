package com.skydelsolutions.gnssloggertutorial;

import android.location.GnssMeasurementsEvent;
import android.location.Location;

public class UiLogger implements GnssListner {

    private MainActivity.ResizableTextView resizableTextView;

    public UiLogger(MainActivity.ResizableTextView resizableTextView) {
        this.resizableTextView = resizableTextView;
    }

    @Override
    public void onLocationChanged(Location location) {
        logLocationEvent("onLocationChanged: " + location);
    }

    @Override
    public void onGnssMeasurementsReceived(GnssMeasurementsEvent event) {
        logMeasurementEvent("onGnsssMeasurementsReceived: " + event);
    }

    private void logMeasurementEvent(String event) {
        logEvent("Measurement", event);
    }

    private void logLocationEvent(String event) {
        logEvent("Location", event);
    }


    private void logEvent(String tag, String message) {
        resizableTextView.logTextFragment(tag, message);
    }
}
