package com.daylon.simonsays;

import com.firebase.client.Firebase;

/**
 * Created by Daylon on 2/28/2016.
 */
public class MyApplication extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
