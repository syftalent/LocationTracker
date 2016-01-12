package com.yifeishen.locationtrackersdk.LT;

import android.content.Context;

import com.firebase.client.Firebase;
import com.yifeishen.locationtrackersdk.Sensor.LTLocationListener;
import com.yifeishen.locationtrackersdk.Sensor.LTLocationManager;
import com.yifeishen.locationtrackersdk.user.UserManager;
import com.yifeishen.locationtrackersdk.user.UserManagerImpl;

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
        if(mInitialized){
            return;
        }
        UserManagerImpl.initUserManager(mContext);
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
        mLocationManager.startShareLocation();
    }

    @Override
    public void stopShareLocation() {
        mShareLocation = false;
        mLocationManager.stopShareLocation();
    }

    @Override
    public UserManager getUserManager() {
        return UserManagerImpl.getInstance();
    }
}
