package com.nangopro.ads;//package com.nhozip.aib.ads;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.nangopro.BuildConfig;
import com.nangopro.AAAAConfigADS;
import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.obj.NangoOConfig;

import java.util.Random;

public class NangoKISSAdsFB {
    private NangoOConfig nangoOConfig;

    public NangoKISSAdsFB(NangoOConfig nangoOConfig) {
        this.nangoOConfig = nangoOConfig;
    }

    AdView adView;

    public void showBanneFB(final ViewGroup viewGroup, final OnloadAds onloadAds , String size) {
        if (BuildConfig.DEBUG) {
            adView = new AdView(AAAAConfigApplicaion.getContext(), "YOUR_PLACEMENT_ID", size.equals(AAAAConfigADS.Size.MEDIUM_RECTANGLE) ? AdSize.RECTANGLE_HEIGHT_250 : AdSize.BANNER_HEIGHT_50);

        } else {
            adView = new AdView(AAAAConfigApplicaion.getContext(), nangoOConfig.ads.fb_banner, size.equals(AAAAConfigADS.Size.MEDIUM_RECTANGLE) ? AdSize.RECTANGLE_HEIGHT_250 : AdSize.BANNER_HEIGHT_50);

        }


        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout ll = new LinearLayout(AAAAConfigApplicaion.getContext());

        ll.setGravity(Gravity.CENTER);
        ll.addView(adView);
        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                onloadAds.onAdsFinished(false);
                Log.e("onError",adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                viewGroup.setVisibility(View.VISIBLE);
                onloadAds.onAdsFinished(true);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        viewGroup.addView(ll, params);
        AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig()
                .withAdListener(adListener)
                .build();


        adView.loadAd(loadAdConfig);

    }

    InterstitialAd interstitialAd;
    private Random random = new Random();
    ProgressDialog dialog;
    public void showFBInter(Context activity, int percent, final OnloadAds onloadAds) {
        int x = random.nextInt(100);
        if (x <= percent) {
            dialog = new ProgressDialog(activity);
            dialog.setMessage("Loadding...");
            dialog.setCancelable(false);
            dialog.show();
            if (BuildConfig.DEBUG) {
                interstitialAd = new InterstitialAd(AAAAConfigApplicaion.getContext(), "YOUR_PLACEMENT_ID");
            } else {
                interstitialAd = new InterstitialAd(AAAAConfigApplicaion.getContext(), nangoOConfig.ads.fb_inter);

            }


            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                private static final String TAG = "KISSAdsFB";


                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial ad displayed callback
                    dialog.dismiss();
                    Log.e(TAG, "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    onloadAds.onAdsFinished(true);
                    dialog.dismiss();
                    Log.e(TAG, "Interstitial ad dismissed.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    onloadAds.onAdsFinished(false);
                    // Ad error callback
                    dialog.dismiss();
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                    // Show the ad

                    if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
                        onloadAds.onAdsFinished(false);
                        return;
                    }
                    if (interstitialAd.isAdInvalidated()) {
                        onloadAds.onAdsFinished(false);
                        return;
                    }
                    // Show the ad
                    interstitialAd.show();
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                    onloadAds.onAdsFinished(true);
                    Log.d(TAG, "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                    Log.d(TAG, "Interstitial ad impression logged!");
                }
            };

            interstitialAd.loadAd(
                    interstitialAd.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());
//
        } else {onloadAds.onAdsFinished(false);
        }


    }

}
