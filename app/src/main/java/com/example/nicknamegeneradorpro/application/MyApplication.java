package com.example.nicknamegeneradorpro.application;

import android.app.Application;
import android.content.Context;

import com.example.nicknamegeneradorpro.Constant.Constant;
import com.example.nicknamegeneradorpro.utils.AdsUtils;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // AudienceNetworkAd.initialize(this, "5f576f4971c117658f937e28");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                AdsUtils.initADS();
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
