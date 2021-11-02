package com.example.nicknameff.model;

import android.content.Context;
import android.util.Log;

import com.example.nicknameff.Constant.Constants;
import com.example.nicknameff.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Config implements Serializable {
    public List<Ad> ads = new ArrayList<>();
    public boolean onlyAdmod = false;
    public boolean onlyFB = true;
    public boolean showAds = true;
    public boolean showMoreApp = false;
    public List<App> apps = new ArrayList<>();

    @Override
    public String toString() {
        return "OConfig{" +
                "ads=" + ads +
                ", onlyAdmod=" + onlyAdmod +
                ", onlyFB=" + onlyFB +
                ", showAds=" + showAds +
                ", showMoreApp=" + showMoreApp +
                ", apps=" + apps +
                '}';
    }

    public static class Ad {
        public Ad(String banner_setting, String inter_setting, String banner_main, String inter_main) {
            this.banner_setting = banner_setting;
            this.inter_setting = inter_setting;
            this.banner_main = banner_main;
            this.inter_main = inter_main;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "banner_setting='" + banner_setting + '\'' +
                    ", inter_setting='" + inter_setting + '\'' +
                    ", banner_main='" + banner_main + '\'' +
                    ", inter_main='" + inter_main + '\'' +
                    '}';
        }

        public String banner_setting;
        public String inter_setting;
        public String banner_main;
        public String inter_main;
    }

    public class App {
        public String link;
        public String content;
        public String name;
        public String icon;
        public String s1;
        public String s2;
        public String s3;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public String getS3() {
            return s3;
        }

        public void setS3(String s3) {
            this.s3 = s3;
        }

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

    public static Config getOConfig(Context context) {
        Config config = new Config();
        String json = SharedPreferencesUtils
                .getStringData(context, Constants.LIST_CONFIG);
        Log.e("hehe", "getOConfig: " + json);
//        if (!json.equals("")) {
//            oConfig = new GsonUtils<OConfig>().stringToObj(json);
//        }
        if (!json.equals("")) {
            config = new Gson().fromJson(json, Config.class);
        } else {
            config.ads.add(new Ad(Constants.FaceBookADS.BANNER_SETTING, Constants.FaceBookADS.INTER_SETIING, Constants.FaceBookADS.BANNER_MAIN, Constants.FaceBookADS.INTER_MAIN));
            config.ads.add(new Ad(Constants.ADS.BANNER_SETTING, Constants.ADS.INTER_SETIING, Constants.ADS.BANNER_MAIN, Constants.ADS.INTER_MAIN));
        }
        Log.e("getOConfig", config.toString());
        return config;

    }

}
