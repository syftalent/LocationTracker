package com.yifeishen.locationtrackersdk.LT;

import android.util.Log;

/**
 * Created by Flyaway on 12/6/15.
 */
public class LocationTracker {
    private static String LOG_TAG = LocationTracker.class.getSimpleName();
    private static SDKConfiguration sSDKConfiguration;
    private static LocationTrackerSDK sLocationTrackerSDK;

    synchronized public static void setSDKConfiguration(SDKConfiguration configuration){
        Log.i(LOG_TAG,"Set the SDK configuration");
        sSDKConfiguration = configuration;
    }

    synchronized public static LocationTrackerSDK getSDK(){
        if(sSDKConfiguration != null){
            if(sLocationTrackerSDK == null) {
                sLocationTrackerSDK = new LocationTrackerSDKImpl(sSDKConfiguration);
                sLocationTrackerSDK.init();
            }
        }else{
            Log.w(LOG_TAG, "unable to create sdk, need to set the configuration first");
        }
        return sLocationTrackerSDK;
    }
}
