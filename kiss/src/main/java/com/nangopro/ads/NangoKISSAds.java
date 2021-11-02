package com.nangopro.ads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nangopro.BuildConfig;
import com.nangopro.AAAAConfigADS;
import com.nangopro.obj.NangoOConfig;

import java.util.Random;

/**
 * Created by AIB on 11/7/2017.
 */

public class NangoKISSAds {
    private InterstitialAd iad;
    private Random random = new Random();
    private NangoOConfig nangoOConfig;

    public NangoKISSAds(NangoOConfig nangoOConfig) {
        this.nangoOConfig = nangoOConfig;
    }

    private AdSize getAdSize(Activity context) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    public void onAdsBanner(String key, String keyOldOrNew, Activity context, final ViewGroup adContainer, String size, final OnloadAds onloadAds) {


        AdView mAdView = new AdView(context);
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout ll = new LinearLayout(context);

        ll.setGravity(Gravity.CENTER);
        ll.addView(mAdView);
        mAdView.setAdSize(size.equals(AAAAConfigADS.Size.MEDIUM_RECTANGLE) ? AdSize.MEDIUM_RECTANGLE : getAdSize(context));

        if (BuildConfig.DEBUG) {
            mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        } else {
            if (key.equals(AAAAConfigADS.BANNER_SETTING))
                mAdView.setAdUnitId(keyOldOrNew.equals(AAAAConfigADS.OLD_ID) ? nangoOConfig.ads.banner_setting : nangoOConfig.ads.banner_setting_new);
            else
                mAdView.setAdUnitId(keyOldOrNew.equals(AAAAConfigADS.OLD_ID) ? nangoOConfig.ads.banner_main : nangoOConfig.ads.banner_main_new);
        }

        adContainer.addView(ll, params);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                onloadAds.onAdsFinished(false);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                onloadAds.onAdsFinished(true);
                adContainer.setVisibility(View.VISIBLE);
            }
        });


    }

    public void onShowInter(String key, String keyOldOrNew, Context context, int percent, final OnloadAds onloadAds) {
        int x = random.nextInt(100);


        if (x <= percent) {

            ProgressDialog dialog = new ProgressDialog(context);

            dialog.setMessage("Loadding...");
            dialog.setCancelable(false);
            dialog.show();
            iad = new InterstitialAd(context);
            if (BuildConfig.DEBUG) {
                iad.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            } else {
                if (key.equals(AAAAConfigADS.INTER_SETTING))
                    iad.setAdUnitId(keyOldOrNew.equals(AAAAConfigADS.OLD_ID) ? nangoOConfig.ads.inter_setting : nangoOConfig.ads.inter_setting_new);
                else
                    iad.setAdUnitId(keyOldOrNew.equals(AAAAConfigADS.OLD_ID) ? nangoOConfig.ads.inter_main : nangoOConfig.ads.inter_main_new);
            }

            AdRequest adRequest = new AdRequest.Builder().build();
            iad.loadAd(adRequest);
            iad.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (iad.isLoaded()) {
                        iad.show();
                    }
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    onloadAds.onAdsFinished(true);
                    dialog.dismiss();
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    onloadAds.onAdsFinished(false);
                    dialog.dismiss();
                }
            });
        } else onloadAds.onAdsFinished(false);
    }


}
