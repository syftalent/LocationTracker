package com.yifeishen.locationtrackersdk.LT;

/**
 * Created by Flyaway on 12/6/15.
 */
public class LocationTrackerException extends RuntimeException {
    private static String LOG_TAG  = LocationTrackerException.class.getSimpleName();

    public LocationTrackerException(){
        this("Exception without description");
    }

    public LocationTrackerException(String detailMessage){
        super(detailMessage);
    }
}
