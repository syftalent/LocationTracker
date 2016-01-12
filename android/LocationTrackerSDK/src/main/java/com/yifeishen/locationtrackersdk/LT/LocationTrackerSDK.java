package com.yifeishen.locationtrackersdk.LT;

import android.content.Context;

import com.yifeishen.locationtrackersdk.Sensor.LTLocationListener;
import com.yifeishen.locationtrackersdk.user.UserManager;

/**
 * Created by Flyaway on 12/6/15.
 */
public interface LocationTrackerSDK {
    public void init();

    public boolean isInitialized();

    public Context getContext();

    public void startFetchLocation(LTLocationListener listener);

    public void stopFetchLocation();

    public void startShareLocation();

    public void stopShareLocation();

    public UserManager getUserManager();
}
