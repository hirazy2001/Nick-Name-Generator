package com.nangopro.obj;

import com.nangopro.AAAAConfigADS;

import java.io.Serializable;
import java.util.ArrayList;

public class NangoOConfig implements Serializable {
    public Ads ads = new Ads();
    public ArrayList<App> apps = new ArrayList<>();
    public AppExit app_exists = new AppExit();


    @Override
    public String toString() {
        return "OConfig{" +
                "ads=" + ads +
                ", apps=" + apps +
                ", app_exists=" + app_exists +
                '}';
    }


    public static class AppExit {
        public boolean is_exists = true;
        public boolean is_show_more = AAAAConfigADS.IS_SHOW_MORE;
        public String id = "";
        public boolean isLoading = AAAAConfigADS.IS_LOADING;
        public boolean isShowAds = AAAAConfigADS.IS_SHOW_ADS;

        @Override
        public String toString() {
            return "AppExit{" +
                    "is_exists=" + is_exists +
                    ", is_show_more=" + is_show_more +
                    ", id='" + id + '\'' +
                    ", isLoading=" + isLoading +
                    ", isShowAds=" + isShowAds +
                    '}';
        }
    }

    public static class Ads {

        public String id_app;
        public String flury_id;
        public String banner_setting;
        public String inter_setting;
        public String banner_main;
        public String inter_main;
        public String banner_setting_new;
        public String inter_setting_new;
        public String banner_main_new;
        public String inter_main_new;
        public String banner_back;
        public String admod_native;
        public String admod_rewarded;
        public String fb_banner;
        public String fb_inter;
        public boolean onlyAdmod = true;
        public boolean onlyFB = false;

        public Ads() {
            this.id_app = AAAAConfigADS.APP_ID;
            this.flury_id = AAAAConfigADS.FLURRY;
            this.banner_setting = AAAAConfigADS.BANNER_SETTING;
            this.inter_setting = AAAAConfigADS.INTER_SETTING;
            this.banner_main = AAAAConfigADS.BANNER_MAIN;
            this.inter_main = AAAAConfigADS.INTER_MAIN;


            this.banner_setting_new = AAAAConfigADS.BANNER_SETTING_NEW;
            this.inter_setting_new = AAAAConfigADS.INTER_SETTING_NEW;
            this.banner_main_new = AAAAConfigADS.BANNER_MAIN_NEW;
            this.inter_main_new = AAAAConfigADS.INTER_MAIN_NEW;


            this.banner_back = AAAAConfigADS.ADMOD_BACK;
            this.admod_native = AAAAConfigADS.ADMOD_NATIVE;
            this.admod_rewarded = AAAAConfigADS.ADMOD_REWARDED;
            this.fb_banner = AAAAConfigADS.FB_BANNER;
            this.fb_inter = AAAAConfigADS.FB_INTER;
            this.onlyAdmod = AAAAConfigADS.ONYLY_ADMOD;
            this.onlyFB = AAAAConfigADS.ONYLY_FB;
        }


        @Override
        public String toString() {
            return "Ads{" +
                    "id_app='" + id_app + '\'' +
                    ", flury_id='" + flury_id + '\'' +
                    ", banner_setting='" + banner_setting + '\'' +
                    ", inter_setting='" + inter_setting + '\'' +
                    ", banner_main='" + banner_main + '\'' +
                    ", inter_main='" + inter_main + '\'' +
                    ", banner_setting_new='" + banner_setting_new + '\'' +
                    ", inter_setting_new='" + inter_setting_new + '\'' +
                    ", banner_main_new='" + banner_main_new + '\'' +
                    ", inter_main_new='" + inter_main_new + '\'' +
                    ", banner_back='" + banner_back + '\'' +
                    ", admod_native='" + admod_native + '\'' +
                    ", admod_rewarded='" + admod_rewarded + '\'' +
                    ", fb_banner='" + fb_banner + '\'' +
                    ", fb_inter='" + fb_inter + '\'' +
                    ", onlyAdmod=" + onlyAdmod +
                    ", onlyFB=" + onlyFB +
                    '}';
        }
    }

    public static class App {
        public String id;
        public String icon;
        public String name;
        public String des;
        public String s1;
        public String s2;
        public String s3;


        @Override
        public String toString() {
            return "App{" +
                    "id='" + id + '\'' +
                    ", icon='" + icon + '\'' +
                    ", name='" + name + '\'' +
                    ", des='" + des + '\'' +
                    ", s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    ", s3='" + s3 + '\'' +
                    '}';
        }
    }
}
