package com.yifeishen.locationtrackersdk.user;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.yifeishen.locationtrackersdk.LT.LocationTrackerException;
import com.yifeishen.locationtrackersdk.LT.OnTaskCompleteHandler;
import com.yifeishen.locationtrackersdk.cloud.FirebaseManager;
import com.yifeishen.locationtrackersdk.user.UserManager;

import java.util.Map;

/**
 * Created by Flyaway on 1/11/16.
 */
public class UserManagerImpl implements UserManager {
    private static UserManagerImpl mInstance;
    private static Context mContext;

    private FirebaseManager mFirebaseManager;

    private User mActiveUser;

    private UserManagerImpl() {
        mFirebaseManager = FirebaseManager.getInstance(mContext);
        mActiveUser = getActiveUser();
    }

    public static void initUserManager(Context context) {
        mContext = context;
    }

    @Override
    public User getActiveUser() {
        return null;
    }

    @Override
    public boolean setActiveUser(User user) {
        mActiveUser = user;
        return true;
    }

    @Override
    public boolean logoutUser() {
        return true;
    }

    @Override
    public void registerUser(String email, String pwd, Firebase.ValueResultHandler<Map<String, Object>> handler) {
        mFirebaseManager.registerUser(email, pwd, handler);
    }

    @Override
    public void loginUser(String email, String pwd, final OnTaskCompleteHandler<AuthData> handler) {
        mFirebaseManager.loginUser(email, pwd, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                setActiveUser(new User(authData.getProviderData().get("email").toString(), authData.getUid()));
                handler.onSuccess(authData);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                handler.onFailure(new LocationTrackerException(firebaseError.getMessage()));
            }
        });
    }

    public static UserManagerImpl getInstance() {
        if (mInstance == null) {
            return new UserManagerImpl();
        } else {
            return mInstance;
        }
    }
}

