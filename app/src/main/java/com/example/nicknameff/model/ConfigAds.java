package com.example.nicknameff.model;

import android.content.Context;
import android.util.Log;

import com.example.nicknameff.Constant.Constants;
import com.example.nicknameff.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.util.List;

public class ConfigAds {
    public Ads ads;
    public boolean onlyAdmod;
    public boolean onlyFB;
    public boolean showAds;
    public boolean showMoreApp;
    public List<App> apps;

    @Override
    public String toString() {
        return "AppConfigAds{" +
                "ads=" + ads +
                ", onlyAdmod=" + onlyAdmod +
                ", onlyFB=" + onlyFB +
                ", showAds=" + showAds +
                ", showMoreApp=" + showMoreApp +
                ", apps=" + apps +
                '}';
    }

    public class Fb {
        public String banner_setting;
        public String inter_setting;
        public String banner_main;
        public String inter_main;

        @Override
        public String toString() {
            return "Fb{" +
                    "banner_setting='" + banner_setting + '\'' +
                    ", inter_setting='" + inter_setting + '\'' +
                    ", banner_main='" + banner_main + '\'' +
                    ", inter_main='" + inter_main + '\'' +
                    '}';
        }
    }

    public class Admod {
        public String banner_setting;
        public String inter_setting;
        public String banner_main;
        public String inter_main;

        @Override
        public String toString() {
            return "Admod{" +
                    "banner_setting='" + banner_setting + '\'' +
                    ", inter_setting='" + inter_setting + '\'' +
                    ", banner_main='" + banner_main + '\'' +
                    ", inter_main='" + inter_main + '\'' +
                    '}';
        }
    }

    public class Ads {
        public Fb fb;
        public Admod admod;

        @Override
        public String toString() {
            return "Ads{" +
                    "fb=" + fb +
                    ", admod=" + admod +
                    '}';
        }
    }

    public class App {
        public String link;
        public String content;
        public String name;
        public String icon;
        public String s1;
        public String s2;
        public String s3;

        @Override
        public String toString() {
            return "App{" +
                    "link='" + link + '\'' +
                    ", content='" + content + '\'' +
                    ", name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    ", s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    ", s3='" + s3 + '\'' +
                    '}';
        }
    }


    public static ConfigAds getAppConfigAds(Context context) {
        ConfigAds oConfig = new ConfigAds();
        String json = SharedPreferencesUtils
                .getStringData(context, Constants.LIST_CONFIG);
        Log.e("hehe", "getOConfig: " + json );
//        if (!json.equals("")) {
//            oConfig = new GsonUtils<OConfig>().stringToObj(json);
//        }
        if(!json.equals("")){
            oConfig = new Gson().fromJson(json, ConfigAds.class);
        }
        Log.e("getOConfig",oConfig.toString());
        return oConfig;

    }

}
