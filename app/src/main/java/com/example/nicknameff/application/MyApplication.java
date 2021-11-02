package com.example.nicknameff.application;

import android.content.Context;


import com.nangopro.AAAAConfigApplicaion;

public class MyApplication extends AAAAConfigApplicaion {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static Context getContext() {
        return context;
    }
}
