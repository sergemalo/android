package com.skydelsolutions.gnssloggertutorial;

import android.location.GnssMeasurementsEvent;
import android.location.Location;

public interface GnssListner {


    void onLocationChanged(Location location);

    void onGnssMeasurementsReceived(GnssMeasurementsEvent event);
}
