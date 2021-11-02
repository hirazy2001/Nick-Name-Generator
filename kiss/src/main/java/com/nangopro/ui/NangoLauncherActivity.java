package com.nangopro.ui;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.jgabrielfreitas.core.BlurImageView;
import com.nangopro.AAAAConfigADS;
import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.R;
import com.nangopro.ads.NangoADS;
import com.nangopro.obj.NangoOConfig;
import com.nangopro.utils.AppUtils;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;


public abstract class NangoLauncherActivity extends Activity {

    public abstract void onMain();

    int i = 0;
    BlurImageView bg;
    RelativeLayout rlAds;
    NangoADS nangoAds;
    ProgressBar progressBarl;
    ImageView imgClose;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(NangoOConfig nangoOConfig) {
        Log.e("getMessage", nangoOConfig.toString());
        nangoAds = new NangoADS();
        // nangoAds.onAdsBanner(AAAAConfigADS.BANNER_SETTING, NangoLauncherActivity.this, rlAds, AAAAConfigADS.Size.MEDIUM_RECTANGLE);
        ok(nangoOConfig);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void cickClose(View v) {

        //nangoAds.onShowInter(AAAAConfigADS.INTER_SETTING, this, 100, b -> {
            onMain();
        //});
    }

    Timer timer = new Timer();

    @Override
    protected void onStop() {
        super.onStop();
        // ConfigApplicaion.bus.register(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ac_lanucher);
        AAAAConfigApplicaion.bus.register(this);
        bg = findViewById(R.id.bg);
        rlAds = findViewById(R.id.rlAds);
        progressBarl = findViewById(R.id.progress);
        imgClose = findViewById(R.id.imgClose);
        imgClose.setVisibility(View.INVISIBLE);
        bg.setBlur(20);

//
//        AndroidNetworking.get(ConfigADS.CONFIG_A_URL)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsObject(OConfig.class, new ParsedRequestListener<OConfig>() {
//
//                    @Override
//                    public void onResponse(OConfig oConfig) {
//                        Log.e("onResponse", oConfig.toString());
//                        Gson gson = new Gson();
//                        SharedPrefsUtils.getInstance().putString(ConfigADS.CONFIG_ADS, gson.toJson(oConfig));
//                        ads = new ADS();
//                        ads.onAdsBanner(ConfigADS.BANNER_SETTING, LauncherActivity.this, rlAds, ConfigADS.Size.MEDIUM_RECTANGLE);
//                        ok(oConfig);
//
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        // handle error
//                        Log.e("onError", anError.toString());
//                        ads = new ADS();
//                        ads.onAdsBanner(ConfigADS.BANNER_SETTING, LauncherActivity.this, rlAds, ConfigADS.Size.MEDIUM_RECTANGLE);
//                        OConfig oConfig = ConfigApplicaion.getCOnfig();
//                        ok(oConfig);
//                    }
//                });


    }

    public void ok(NangoOConfig nangoOConfig) {

        if (nangoOConfig.app_exists.is_exists) {
            if (isNetworkConnected() && nangoOConfig.app_exists.isShowAds) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        i += 3;
                        if (i > 100) {

                            runOnUiThread(() -> {
                                if (!nangoOConfig.app_exists.isLoading) {
                                    timer.cancel();
                                    progressBarl.setVisibility(View.GONE);
                                } else {
                                    Log.e("iiii", i + "");
                                    i = 0;
                                }
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {

                                                if (nangoOConfig.app_exists.isLoading) {
                                                    imgClose.setVisibility(View.INVISIBLE);
                                                } else {
                                                    imgClose.setVisibility(View.VISIBLE);
                                                }
                                                Log.i("tag", "A Kiss after 5 seconds");

                                            }
                                        }, 2000);


                                // Stuff that updates the UI

                            });

                        }
                        progressBarl.setProgress(i);
                        Log.i("tag", "A Kiss every 5 seconds");
                    }
                }, 0, 100);

            } else {
                onMain();
            }

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(NangoLauncherActivity.this)
                    .setTitle("Your application is out of date")
                    .setMessage("Update version now ?")
                    .setCancelable(false)
                    .setPositiveButton("UPDATE", (dialogInterface, i) -> {
                        AppUtils.moreApp(getApplicationContext(), nangoOConfig.app_exists.id);
                        dialogInterface.dismiss();

                        ActivityCompat.finishAffinity(NangoLauncherActivity.this);

                    });
            dialog.show();
        }


    }

}
