package com.yifeishen.locationtrackersdk.Utils;

import android.location.Location;

/**
 * Created by Flyaway on 12/10/15.
 */
public class Utils {
    public static boolean isValidLocation(Location location){
        if(location == null || (location.getLongitude() == 0 && location.getAltitude() == 0)){
            return false;
        }
        return true;
    }
}
