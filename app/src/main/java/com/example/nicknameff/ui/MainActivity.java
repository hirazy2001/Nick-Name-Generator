package com.example.nicknameff.ui;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicknameff.Constant.Constants;
import com.example.nicknameff.R;
import com.example.nicknameff.adapter.Adapter_text;
import com.example.nicknameff.db.DB;
import com.example.nicknameff.model.TextC;
import com.example.nicknameff.utils.AppGGUtils;
import com.example.nicknameff.utils.Dialog_ShowText;
import com.example.nicknameff.utils.Dialog_ShowPrivacy;
import com.example.nicknameff.utils.GsonUtils;
import com.example.nicknameff.utils.Random_Text;
import com.example.nicknameff.utils.SharedPreferencesUtils;
import com.nangopro.AAAAConfigADS;
import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.ads.NangoADS;
import com.nangopro.ads.OnloadAds;
import com.nangopro.ui.NangoMoreAppActivity;
import com.nangopro.utils.RateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, Adapter_text.OnText, View.OnFocusChangeListener {

    ImageView option, change, apps;
    Spinner spinner1, spinner2, spinner3;
    RecyclerView recyclerView;
    ArrayList<TextC> ListText;
    EditText editText;
    Adapter_text adapterText;
    DB db;
    LinearLayout sc_copy, changetext;
    Random_Text randomText;
    TextView status, size, app_name;
    Dialog_ShowPrivacy privacy;
    NangoADS nangoADS;
    //    ProgressDialog dialog;
//    private AdView adView;
    public static int cnt_change_random = 1;
    public static boolean ok = true;
    public static final String defaultname = "NickName";
    public static String text1, text2, text3;
    LinearLayoutManager linearLayoutManager;
    public static String Text = "NickName";
    //private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    RelativeLayout rlAds;
    String TAG = "hehhe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//
        nangoADS = new NangoADS();
        init();
        for (int i = 0; i < randomText.size(); i++) {
            ListText.add(new TextC(defaultname));
        }
        getListCoppys();
        //Collections.reverse(ListText);
        text1 = (String) spinner1.getSelectedItem();
        text2 = (String) spinner2.getSelectedItem();
        text3 = (String) spinner3.getSelectedItem();
        adapterText = new Adapter_text(ListText, this, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterText);
        adapterText.notifyDataSetChanged();
        spinner1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner1.getSelectedView()).setTextColor(Color.CYAN);
            }
        });
        spinner2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner2.getSelectedView()).setTextColor(Color.CYAN);
            }
        });
        spinner3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner3.getSelectedView()).setTextColor(Color.CYAN);
            }
        });
        //   RateUtils.showRateDialog(this, "");//cai email nay la ntn v anh
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener(MainActivity.this);
                popup.inflate(R.menu.menu_main);
                popup.show();
            }
        });
        //spinner1.
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Text = editText.getText().toString();
                adapterText.notifyDataSetChanged();
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   nangoADS.onShowInter(ConfigADS.INTER_MAIN, MainActivity.this, 80, new OnloadAds() {
//                    @Override
//                    public void onAdsFinished(boolean b) {
                text1 = (String) spinner1.getSelectedItem();
                adapterText.notifyDataSetChanged();
                //  Toast.makeText(MainActivity.this, "setOnItemSelectedListener", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                nangoADS.onShowInter(ConfigADS.INTER_MAIN, MainActivity.this, 80, new OnloadAds() {
//                    @Override
//                    public void onAdsFinished(boolean b) {
                text2 = (String) spinner2.getSelectedItem();
                adapterText.notifyDataSetChanged();
//                    }
//                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                nangoADS.onShowInter(ConfigADS.INTER_MAIN, MainActivity.this, 80, new OnloadAds() {
//                    @Override
//                    public void onAdsFinished(boolean b) {
                text3 = (String) spinner3.getSelectedItem();
                adapterText.notifyDataSetChanged();
//                    }
//                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        AppConfigAds.getAppConfigAds(this);


    }

    ArrayList<TextC> listCoppy = new ArrayList<>();

    private void getListCoppys() {
        String data = SharedPreferencesUtils.getStringData(this, Constants.LIST_COPPY);
        Log.e("getListCoppys", "getListCoppys: " + data);
        if (!TextUtils.isEmpty(data)) {
            listCoppy = new GsonUtils().stringToArray(data);
//            List<TextCoppy> coppyList = GsonUtils.stringToArray(data);
            Log.e("getListCoppys", listCoppy.toString());
            //convert data sang list ma minh muon

        }
        size.setText(String.valueOf(listCoppy.size()));
    }

    public void init() {
        option = findViewById(R.id.img_option);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        app_name = findViewById(R.id.tv_appname);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/yeonsung_font.ttf");
        app_name.setTypeface(type);
        recyclerView = findViewById(R.id.recyclerview);
        change = findViewById(R.id.ic_change);
        sc_copy = findViewById(R.id.sc_copy);
        changetext = findViewById(R.id.changetext);
        editText = findViewById(R.id.edittext);
        status = findViewById(R.id.status);
        size = findViewById(R.id.listsize);
        apps = findViewById(R.id.ic_moreapps);
        ListText = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        db = new DB(this);
        Log.e("aaa", "init: " + db.getAllListTextSaved().toString());
        size.setText(String.valueOf(db.getAllListTextSaved().size()));
        randomText = new Random_Text();
//        interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId(AD_UNIT_ID);
        rlAds = findViewById(R.id.rlAds1);
//        AdsUtils.showBanner(AdSize.BANNER,adMobView);

//        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID",
//                AdSize.BANNER_HEIGHT_50);
//        rlAds.addView(adView);
        //adView.loadAd();
        //OConfig o = OConfig.getOConfig(this);
        //Log.e("adssss", "onCreate:0 " + o.ads.get(0));
        //Log.e("adssss", "onCreate:1 " + o.ads.get(1));

        boolean checkMore = AAAAConfigApplicaion.getCOnfig().app_exists.is_show_more;
//        if (checkMore) {
//            apps.setVisibility(View.VISIBLE);
//        } else apps.setVisibility(View.GONE);
//        new FaceBookAdsUtils().showBanner(Constant.MAIN, AdSize.BANNER_HEIGHT_50, rlAds);
        // nangoADS.onAdsBanner(AAAAConfigADS.BANNER_MAIN, MainActivity.this, rlAds, AAAAConfigADS.Size.BANNER);
        RateUtils.showRateDialog(this, "");
    }


    @Override
    protected void onDestroy() {
//        if (adView != null) {
//            adView.destroy();
//        }
//        if (interstitialAd != null) {
//            //interstitialAd.destroy();
//        }
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_privacy:
                privacy = new Dialog_ShowPrivacy(this);
                privacy.show();
                break;
            case R.id.menu_app:
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "nickname.generator.free.ff.games")));
                AppGGUtils.rateApp(MainActivity.this);
                break;
        }
        return false;
    }

    @Override
    public void OnTextListener(int position, String s) {
        //nangoADS.onShowInter(AAAAConfigADS.INTER_MAIN, MainActivity.this, 40, new OnloadAds() {
        //@Override
        //public void onAdsFinished(boolean b) {
        Dialog_ShowText dialogShowText = new Dialog_ShowText(MainActivity.this);
        dialogShowText.init();
        dialogShowText.startGame();
        dialogShowText.show();
        dialogShowText.setContent(s);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Result", s);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        checkSave(s);
        size.setText(String.valueOf(listCoppy.size()));
        //}
        // });
    }

    public void checkSave(String text) {
        boolean check = false;
        for (int i = 0; i < listCoppy.size(); i++) {
            TextC temp = listCoppy.get(i);
            if (temp.getText().equals(text)) check = true;
        }
        if (!check) {
            listCoppy.add(new TextC(text));
            String json = new GsonUtils().arrayToString(listCoppy);
            Log.e("listCoppy", json);
            SharedPreferencesUtils.saveData(this, Constants.LIST_COPPY, json);
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Text = editText.getText().toString();
        adapterText.notifyDataSetChanged();
    }

    @OnClick({R.id.changetext, R.id.sc_copy, R.id.ic_moreapps})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changetext:
                //nangoADS.onShowInter(AAAAConfigADS.INTER_MAIN, MainActivity.this, 40, new OnloadAds() {
                // @Override
                //public void onAdsFinished(boolean b) {
                cnt_change_random++;
                if (ok) {
                    Random random = new Random();
                    int pos = random.nextInt(randomText.size());
                    editText.setText(randomText.getName(pos));
                } else {

                    ListText.clear();
                    for (int i = 0; i < 59; i++) {
                        ListText.add(new TextC(Text));
                    }
                    change.setImageResource(R.mipmap.ic_reload);
                    status.setText("Random");
                    status.setTextSize(18);
                    adapterText.notifyDataSetChanged();
                    ok = !ok;
                }
                // }
                //});
                break;
            case R.id.sc_copy:

//                nangoADS.onShowInter(AAAAConfigADS.INTER_MAIN, MainActivity.this, 40, new OnloadAds() {
//                    @Override
//                    public void onAdsFinished(boolean b) {
                if (ok) {
                    ListText.clear();
                    String data = SharedPreferencesUtils.getStringData(MainActivity.this, Constants.LIST_COPPY);
                    Log.e("sc_copy", "sc_copy: " + data);
                    if (!TextUtils.isEmpty(data)) {
                        ListText = new GsonUtils().stringToArray(data);
                    }
//                    ListText = dbManager.getAllListTextSaved();
                    Collections.reverse(ListText);
                    adapterText = new Adapter_text(ListText, MainActivity.this, MainActivity.this);
                    recyclerView.setAdapter(adapterText);
                    adapterText.notifyDataSetChanged();
                    change.setImageResource(R.mipmap.ic_back);
                    status.setText("BACK");
                    status.setTextSize(20);
                    ok = !ok;
                }
                //}
                //});

                break;
            case R.id.ic_moreapps:
                Intent intent = new Intent(MainActivity.this, NangoMoreAppActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

}