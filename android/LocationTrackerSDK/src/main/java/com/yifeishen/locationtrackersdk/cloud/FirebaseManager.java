package com.yifeishen.locationtrackersdk.cloud;

import android.content.Context;
import android.location.Location;

import com.firebase.client.Firebase;
import com.yifeishen.locationtrackersdk.user.User;

import java.util.Map;

/**
 * Created by Flyaway on 1/4/16.
 */
public class FirebaseManager {
    private static FirebaseManager mInstance;
    private Context mContext;
    private Firebase mFirebase;

    private FirebaseManager(Context context){
        mContext = context;
        init();
    }

    private void init(){
        Firebase.setAndroidContext(mContext);
        mFirebase = new Firebase("https://geolocationtracker.firebaseio.com/users");
    }

    public static FirebaseManager getInstance(Context context){
        if(mInstance == null){
            return new FirebaseManager(context);
        }else{
            return mInstance;
        }
    }

    public void updateLocation(Location location){
        mFirebase.child("cole").child("lastknownlocation").child("lat").setValue(location.getLatitude());
        mFirebase.child("cole").child("lastknownlocation").child("lng").setValue(location.getLongitude());
    }

    public void registerUser(String email, String pwd, Firebase.ValueResultHandler<Map<String, Object>> handler){
        mFirebase.createUser(email, pwd, handler);
    }

    public void loginUser(String email, String pwd, Firebase.AuthResultHandler handler){
        mFirebase.authWithPassword(email, pwd, handler);
    }
}
