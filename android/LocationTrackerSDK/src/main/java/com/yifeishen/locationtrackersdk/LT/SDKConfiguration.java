package com.yifeishen.locationtrackersdk.LT;

import android.content.Context;

/**
 * Created by Flyaway on 12/6/15.
 */
public class SDKConfiguration {
    private static String LOG_TAG = SDKConfiguration.class.getSimpleName();

    private Context mContext;
    private boolean mShareLocation;
    private SetupHandler mHandler;

    public interface SetupHandler {
        public void onSetupSuccess();
        public void onSetupFailure(LocationTrackerException errorMessage);
    }

    SDKConfiguration(Context mContext, boolean shareLocation, SetupHandler handler){
        this.mContext = mContext;
        this.mShareLocation = shareLocation;
        this.mHandler = handler;

        checkService();
    }

    public static class ConfigurationBuilder{
        private Context mContext;
        private boolean mShareLocation;
        private SetupHandler mHandler;

        public ConfigurationBuilder(){}

        public ConfigurationBuilder setContext(Context context){
            mContext = context;
            return this;
        }

        public ConfigurationBuilder setShareLocation(boolean ifShareLocation){
            mShareLocation = ifShareLocation;
            return this;
        }

        public ConfigurationBuilder setHandler(SetupHandler handler){
            mHandler = handler;
            return this;
        }

        public SDKConfiguration build(){
            return new SDKConfiguration(mContext,mShareLocation,mHandler);
        }
    }



    public Context getContext(){
        return mContext;
    }

    public boolean getDefaultIfShareLocation(){
        return mShareLocation;
    }

    private void checkService(){
        mHandler.onSetupSuccess();
    }
}
