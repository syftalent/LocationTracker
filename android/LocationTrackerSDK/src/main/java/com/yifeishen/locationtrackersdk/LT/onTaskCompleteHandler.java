package com.yifeishen.locationtrackersdk.LT;

/**
 * Created by Flyaway on 12/6/15.
 */
public interface onTaskCompleteHandler<T> {
    public void onSuccess(T result);
    public void onFailure(LocationTrackerException errorMessage);
}
