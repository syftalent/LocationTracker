package com.yifeishen.locationtrackerapp;

import android.app.Application;
import android.util.Log;

import com.yifeishen.locationtrackersdk.LT.LocationTracker;
import com.yifeishen.locationtrackersdk.LT.LocationTrackerException;
import com.yifeishen.locationtrackersdk.LT.LocationTrackerSDK;
import com.yifeishen.locationtrackersdk.LT.SDKConfiguration;

/**
 * Created by Flyaway on 12/9/15.
 */
public class LocationTrackerApp extends Application{
    private static String LOG_TAG = LocationTrackerApp.class.getSimpleName();

    private static LocationTrackerApp mInstance = null;
    private LocationTrackerSDK mLTSDK;

    @Override
    public void onCreate() {
        Log.i(LOG_TAG, "App onCreate()");

        super.onCreate();

        mInstance = this;

        SDKConfiguration sdkConfiguration = new SDKConfiguration.ConfigurationBuilder()
                .setContext(this)
                .setShareLocation(true)
                .setHandler(new SDKConfiguration.SetupHandler() {
                    @Override
                    public void onSetupSuccess() {
                        Log.i(LOG_TAG, "SDK setup successfully");
                    }

                    @Override
                    public void onSetupFailure(LocationTrackerException e) {
                        Log.i(LOG_TAG, "SDK setup failed");
                    }
                }).build();
        LocationTracker.setSDKConfiguration(sdkConfiguration);
        mLTSDK = LocationTracker.getSDK();
    }

    public LocationTrackerApp getInstance(){
        return mInstance;
    }

}
