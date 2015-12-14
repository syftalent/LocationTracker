package com.yifeishen.locationtrackersdk.Sensor;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.yifeishen.locationtrackersdk.Utils.Utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Flyaway on 12/6/15.
 */
public class LTLocationManager implements ConnectionCallbacks, OnConnectionFailedListener{
    private static String LOG_TAG = LTLocationManager.class.getSimpleName();

    private Set<LTLocationListener> listenerSet;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestLocationUpdate;

    private static LTLocationManager mInstance;

    public LTLocationManager(Context context){
        listenerSet = new HashSet<LTLocationListener>();
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mInstance = this;
    }

    public static LTLocationManager getInstance(){
        return mInstance;
    }

    public void connect(){
        if(!mRequestLocationUpdate) {
            mRequestLocationUpdate = true;
            mGoogleApiClient.connect();
        }
    }

    public void disconnect(){
        listenerSet.clear();
        mRequestLocationUpdate = false;
        mGoogleApiClient.disconnect();
    }

    public void addListener(LTLocationListener listener){
        synchronized (listenerSet) {
            listenerSet.add(listener);
        }
    }

    public void removeListener(LTLocationListener listener){
        synchronized (listenerSet) {
            if (listenerSet.contains(listener)) {
                listenerSet.remove(listener);
            }
        }
    }

    private void informOnLocationChanged(Location location){
        synchronized (listenerSet){
            Iterator<LTLocationListener> iterator = listenerSet.iterator();
            while(iterator.hasNext()){
                iterator.next().onLocationChanged(location);
            }
        }
    }

    private void informOnConnectionFailed(Exception exception){
        synchronized (listenerSet){
            Iterator<LTLocationListener> iterator = listenerSet.iterator();
            while(iterator.hasNext()){
                iterator.next().onConnectionFailuer(exception);
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(LOG_TAG,"Google Play Service connect successfully");

        if(!mRequestLocationUpdate){
            return;
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(100);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(Utils.isValidLocation(lastKnownLocation)){
            informOnLocationChanged(lastKnownLocation);
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                informOnLocationChanged(location);
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(LOG_TAG,"Google Play Service connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LOG_TAG,"Google Play Service connect failed" + connectionResult);
        informOnConnectionFailed(new Exception("connection failed:" + connectionResult.toString()));
    }
}
