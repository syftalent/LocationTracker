package com.yifeishen.locationtrackersdk.LT;

import android.content.Context;

import com.yifeishen.locationtrackersdk.Sensor.LTLocationListener;
import com.yifeishen.locationtrackersdk.Sensor.LTLocationManager;

/**
 * Created by Flyaway on 12/6/15.
 */
public class LocationTrackerSDKImpl implements LocationTrackerSDK {
    private static String LOG_TAG = LocationTrackerSDKImpl.class.getSimpleName();

    private boolean mInitialized = false;
    private boolean mShareLocation;
    private Context mContext;
    private LTLocationManager mLocationManager;

    protected LocationTrackerSDKImpl(SDKConfiguration configuration){
        mShareLocation = configuration.getDefaultIfShareLocation();
        mContext = configuration.getContext();
        init();
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void init() {
        mLocationManager = new LTLocationManager(mContext);
        mInitialized = true;
    }

    @Override
    public boolean isInitialized() {
        return mInitialized;
    }

    @Override
    public void startFetchLocation(LTLocationListener listener) {
        mLocationManager.connect();
        mLocationManager.addListener(listener);
    }

    @Override
    public void stopFetchLocation() {
        mLocationManager.disconnect();
    }

    @Override
    public void startShareLocation() {
        mShareLocation = true;
    }

    @Override
    public void stopShareLocation() {
        mShareLocation = false;
    }
}
