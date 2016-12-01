package com.example.vladok.testtask.util;

import android.app.Application;
import android.content.Context;

/**
 *  This class singleton provides ApplicationContext's object.
 */
public class ApplicationContext extends Application {

    private static Context context;

    public static Context getContext() {
        return ApplicationContext.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext.context = getApplicationContext();
    }
}