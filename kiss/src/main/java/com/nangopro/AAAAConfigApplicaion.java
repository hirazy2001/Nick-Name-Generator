package com.nangopro;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.AudienceNetworkAdx;
import com.flurry.android.FlurryAgent;
import com.google.gson.Gson;
import com.nangopro.obj.NangoOConfig;
import com.nangopro.utils.SharedPrefsUtils;

import com.androidnetworking.common.Priority;


public class AAAAConfigApplicaion extends Application {
    static Context context;
    public static AAAAAMainThreadBus bus = new AAAAAMainThreadBus();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


        if (!AudienceNetworkAds.isInitialized(context)) {
            if (BuildConfig.DEBUG) {
                AdSettings.turnOnSDKDebugger(context);
                AdSettings.setTestMode(true);// for get test ad in your device
            }

            AudienceNetworkAds
                    .buildInitSettings(context)
                    .withInitListener(initResult -> {

                    })
                    .initialize();
        }
        AndroidNetworking.get(AAAAConfigADS.CONFIG_A_URL)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(NangoOConfig.class, new ParsedRequestListener<NangoOConfig>() {

                    @Override
                    public void onResponse(NangoOConfig nangoOConfig) {
                        Log.e("onResponse", nangoOConfig.toString());
                        Gson gson = new Gson();
                        SharedPrefsUtils.getInstance().putString(AAAAConfigADS.CONFIG_ADS, gson.toJson(nangoOConfig));
                        if (!BuildConfig.DEBUG) {

                            if (!nangoOConfig.ads.id_app.equals("")) AudienceNetworkAdx.initialize(context, nangoOConfig.ads.id_app);
                            if (!nangoOConfig.ads.flury_id.equals("")) new FlurryAgent.Builder()
                                    .withLogEnabled(true)
                                    .build(context, nangoOConfig.ads.flury_id);

                        }
                        bus.post(nangoOConfig);

                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.e("onError", anError.toString());

                        NangoOConfig nangoOConfig = AAAAConfigApplicaion.getCOnfig();
                        if (!nangoOConfig.ads.id_app.equals("")) AudienceNetworkAdx.initialize(context, nangoOConfig.ads.id_app);
                        if (!nangoOConfig.ads.flury_id.equals("")) new FlurryAgent.Builder()
                                .withLogEnabled(true)
                                .build(context, nangoOConfig.ads.flury_id);
                        bus.post(nangoOConfig);

                    }
                });

    }

    static NangoOConfig nangoOConfig;

    public static NangoOConfig getCOnfig() {
        if (nangoOConfig == null) {
            nangoOConfig = new NangoOConfig();
            String config = SharedPrefsUtils.getInstance().getString(AAAAConfigADS.CONFIG_ADS);
            Gson gson = new Gson();

            if (!config.isEmpty()) {
                try {
                    nangoOConfig = gson.fromJson(config, NangoOConfig.class);
                } catch (Exception e) {

                }
            }
        }


        Log.d("ConfigDATA", nangoOConfig.toString());


        return nangoOConfig;
    }

    public static Context getContext() {
        return context;
    }
}
