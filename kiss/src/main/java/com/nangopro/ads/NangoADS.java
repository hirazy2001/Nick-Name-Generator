package com.nangopro.ads;


import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.nangopro.AAAAConfigADS;
import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.obj.NangoOConfig;

import java.util.Random;

public class NangoADS {
    NangoKISSAds nangoKissAds;
    NangoKISSAdsFB nangoKissAdsFB;
    private NangoOConfig nangoOConfig;

    public NangoADS() {
        this.nangoOConfig = AAAAConfigApplicaion.getCOnfig();
        this.nangoKissAds = new NangoKISSAds(this.nangoOConfig);
        this.nangoKissAdsFB = new NangoKISSAdsFB(this.nangoOConfig);
    }

    public void onAdsBanner(String key, Activity activity, ViewGroup rlAds, String size) {
        if (nangoOConfig.app_exists.isShowAds) {
            if (this.nangoOConfig.ads.onlyAdmod) {
                nangoKissAds.onAdsBanner(key, AAAAConfigADS.OLD_ID, activity, rlAds, size, b -> {
                    if (!b) {
                        nangoKissAdsFB.showBanneFB(rlAds, bb -> {
                            if (!bb && !nangoOConfig.ads.banner_setting_new.equals("")) {
                                nangoKissAds.onAdsBanner(key, AAAAConfigADS.NEW_ID, activity, rlAds, size, bbb -> {
                                });
                            }
                        }, size);
                    }
                });
            } else {
                if (this.nangoOConfig.ads.onlyFB) {
//                Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
                    nangoKissAdsFB.showBanneFB(rlAds, b -> {
                        if (!b) {
                            nangoKissAds.onAdsBanner(key, AAAAConfigADS.OLD_ID, activity, rlAds, size, b1 -> {
                                if (!b1 && !nangoOConfig.ads.banner_setting_new.equals("")) {
                                    nangoKissAds.onAdsBanner(key, AAAAConfigADS.NEW_ID, activity, rlAds, size, bbb -> {


                                    });
                                }
                            });
                        }
                    }, size);
                } else {
                    int x = new Random().nextInt();
                    if (x % 2 == 0) {
                        nangoKissAds.onAdsBanner(key, AAAAConfigADS.OLD_ID, activity, rlAds, size, b1 -> {
                            if (!b1 && !nangoOConfig.ads.banner_setting_new.equals("")) {
                                nangoKissAds.onAdsBanner(key, AAAAConfigADS.NEW_ID, activity, rlAds, size, bbb -> {


                                });
                            }
                        });
                    } else {
                        Log.e("onAdsBanner", "FB");
                        nangoKissAdsFB.showBanneFB(rlAds, b -> {
                            if (!b) {
                                nangoKissAds.onAdsBanner(key, AAAAConfigADS.OLD_ID, activity, rlAds, size, b1 -> {
                                    if (!b1 && !nangoOConfig.ads.banner_setting_new.equals("")) {
                                        nangoKissAds.onAdsBanner(key, AAAAConfigADS.NEW_ID, activity, rlAds, size, bbb -> {


                                        });
                                    }
                                });
                            }
                        }, size);
                    }
                }

            }
        }


    }

    public void onShowInter(String key, Activity activity, int percent, OnloadAds onloadAds) {
        if (nangoOConfig.app_exists.isShowAds) {
            if (this.nangoOConfig.ads.onlyAdmod) {
                nangoKissAds.onShowInter(key, AAAAConfigADS.OLD_ID, activity, percent, b -> {
                    if (!b) {
                        nangoKissAdsFB.showFBInter(activity, percent, b1 -> {

                            if (!b1 && !nangoOConfig.ads.inter_main_new.equals("")) {
                                nangoKissAds.onShowInter(key, AAAAConfigADS.NEW_ID, activity, percent, b2 -> {

                                    onloadAds.onAdsFinished(true);

                                });

                            } else {
                                onloadAds.onAdsFinished(true);
                            }

                        });
                    } else {
                        onloadAds.onAdsFinished(true);
                    }
                });
            } else {
                if (this.nangoOConfig.ads.onlyFB) {
                    nangoKissAdsFB.showFBInter(activity, percent, b -> {
                        if (!b) {
                            nangoKissAds.onShowInter(key, AAAAConfigADS.OLD_ID, activity, percent, new OnloadAds() {
                                @Override
                                public void onAdsFinished(boolean b1) {
                                    if (!b1 && !nangoOConfig.ads.inter_main_new.equals("")) {
                                        nangoKissAds.onShowInter(key, AAAAConfigADS.NEW_ID, activity, percent, b2 -> {

                                            onloadAds.onAdsFinished(true);

                                        });

                                    } else {
                                        onloadAds.onAdsFinished(true);
                                    }
                                }
                            });
                        } else {
                            onloadAds.onAdsFinished(true);
                        }
                    });
                } else {
                    int x = new Random().nextInt();
                    if (x % 2 == 0) {
                        nangoKissAds.onShowInter(key, AAAAConfigADS.OLD_ID, activity, percent, new OnloadAds() {
                            @Override
                            public void onAdsFinished(boolean b1) {
                                if (!b1 && !nangoOConfig.ads.inter_main_new.equals("")) {
                                    nangoKissAds.onShowInter(key, AAAAConfigADS.NEW_ID, activity, percent, b2 -> {

                                        onloadAds.onAdsFinished(true);

                                    });

                                } else {
                                    onloadAds.onAdsFinished(true);
                                }
                            }
                        });
                    } else {
                        Log.e("onShowInter", "FB");
                        nangoKissAdsFB.showFBInter(activity, percent, b -> {
                            if (!b) {
                                nangoKissAds.onShowInter(key, AAAAConfigADS.OLD_ID, activity, percent, new OnloadAds() {
                                    @Override
                                    public void onAdsFinished(boolean b1) {
                                        if (!b1 && !nangoOConfig.ads.inter_main_new.equals("")) {
                                            nangoKissAds.onShowInter(key, AAAAConfigADS.NEW_ID, activity, percent, b2 -> {

                                                onloadAds.onAdsFinished(true);

                                            });

                                        } else {
                                            onloadAds.onAdsFinished(true);
                                        }
                                    }
                                });
                            } else {
                                onloadAds.onAdsFinished(true);
                            }
                        });
                    }
                }


            }
        } else {
            onloadAds.onAdsFinished(true);
        }


    }
}
