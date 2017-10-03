package com.skydelsolutions.gnssloggertutorial;


import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;


public class GnssController {
    private final LocationManager locationManager;

    private final List<GnssListner> mLoggers;

    public GnssController(Context context, GnssListner... loggers) {
        this.mLoggers = Arrays.asList(loggers);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void registerLocation() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000 /*locationRateGpsMillis*/,
                0.0f /* minDistance */,
                locationListener);
    }

    public void unregisterLocation() {
        locationManager.removeUpdates(locationListener);
    }

    public void registerMeasurements() {
        locationManager.registerGnssMeasurementsCallback(gnssMeasurementsEventCallback);
    }

    public void unregisterMeasurements() {
        locationManager.unregisterGnssMeasurementsCallback(gnssMeasurementsEventCallback);
    }

    private final LocationListener locationListener =
            new LocationListener() {

                @Override
                public void onProviderEnabled(String provider) {}

                @Override
                public void onProviderDisabled(String provider) {}

                @Override
                public void onLocationChanged(Location location) {
                    for (GnssListner logger : mLoggers) {
                        logger.onLocationChanged(location);
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {}
            };

    private final GnssMeasurementsEvent.Callback gnssMeasurementsEventCallback =
            new GnssMeasurementsEvent.Callback() {
                @Override
                public void onGnssMeasurementsReceived(GnssMeasurementsEvent event) {
                    for (GnssListner logger : mLoggers) {
                        logger.onGnssMeasurementsReceived(event);
                    }
                }


                @Override
                public void onStatusChanged(int status) {}
            };

}

