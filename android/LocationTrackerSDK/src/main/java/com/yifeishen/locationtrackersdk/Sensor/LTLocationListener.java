package com.yifeishen.locationtrackersdk.Sensor;

import android.location.Location;

import com.google.android.gms.location.LocationListener;

/**
 * Created by Flyaway on 12/9/15.
 */
public interface LTLocationListener extends LocationListener {
    public void onConnectionFailuer(Exception errorMessage);
}
