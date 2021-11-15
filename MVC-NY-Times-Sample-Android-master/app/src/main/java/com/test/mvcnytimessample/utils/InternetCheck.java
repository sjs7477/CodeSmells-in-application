package com.test.mvcnytimessample.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetCheck {
    //check internet before sending request to server
    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
