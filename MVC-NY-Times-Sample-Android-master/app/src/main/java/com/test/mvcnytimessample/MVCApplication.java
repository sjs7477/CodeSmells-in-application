package com.test.mvcnytimessample;

import android.app.Application;
import android.content.Context;


public class MVCApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getMyAppContext(){
        return context;
    }
}
