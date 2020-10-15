package com.example.nicknamegeneradorpro.ui;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

import com.example.nicknamegeneradorpro.Constant.Constant;
import com.example.nicknamegeneradorpro.R;
import com.example.nicknamegeneradorpro.adapter.TextAdapter;
import com.example.nicknamegeneradorpro.db.DBManager;
import com.example.nicknamegeneradorpro.model.TextCoppy;
import com.example.nicknamegeneradorpro.utils.AdsUtils;
import com.example.nicknamegeneradorpro.utils.DialogCustom;
import com.example.nicknamegeneradorpro.utils.DialogCustom_Privacy;
import com.example.nicknamegeneradorpro.utils.GsonUtils;
import com.example.nicknamegeneradorpro.utils.NameRandom;
import com.example.nicknamegeneradorpro.utils.SharedPreferencesUtils;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.service.InShotSplashAdRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nicknamegeneradorpro.utils.AdsUtils.splashBannerAd;
import static com.facebook.ads.AudienceNetworkAds.loadSplashAd;
import static com.facebook.ads.AudienceNetworkAds.loadSplashAd;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, TextAdapter.OnText, View.OnFocusChangeListener {

    ImageView option, change;
    Spinner spinner1, spinner2, spinner3;
    RecyclerView recyclerView;
    ArrayList<TextCoppy> ListText;
    EditText editText;
    TextAdapter textAdapter;
    DBManager dbManager;
    LinearLayout sc_copy, changetext;
    NameRandom nameRandom;
    TextView status, size;
    DialogCustom_Privacy privacy;
    ProgressDialog dialog;
    private AdView adView;
    public static int cnt_change_random = 1;
    public static boolean ok = true;
    public static final String defaultname = "NickName";
    public static String text1, text2, text3;
    LinearLayoutManager linearLayoutManager;
    public static String Text = "NickName";
    public InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    RelativeLayout adMobView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        for (int i = 0; i < nameRandom.size(); i++) {
            ListText.add(new TextCoppy(defaultname));
        }
        getListCoppys();
        //Collections.reverse(ListText);
        text1 = (String) spinner1.getSelectedItem();
        text2 = (String) spinner2.getSelectedItem();
        text3 = (String) spinner3.getSelectedItem();
        textAdapter = new TextAdapter(ListText, this, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(textAdapter);
        textAdapter.notifyDataSetChanged();

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
                textAdapter.notifyDataSetChanged();
            }
        });
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item);
        //spinner1.setAdapter(adapter);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.spiner_1, R.a)
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                    @Override
                    public void onOk() {
                        text1 = (String) spinner1.getSelectedItem();
                        textAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                    @Override
                    public void onOk() {
                        text2 = (String) spinner2.getSelectedItem();
                        textAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                    @Override
                    public void onOk() {
                        text3 = (String) spinner3.getSelectedItem();
                        textAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    ArrayList<TextCoppy> listCoppy = new ArrayList<>();

    private void getListCoppys() {
        String data = SharedPreferencesUtils.getStringData(this, Constant.LIST_COPPY);
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
        recyclerView = findViewById(R.id.recyclerview);
        change = findViewById(R.id.ic_change);
        sc_copy = findViewById(R.id.sc_copy);
        changetext = findViewById(R.id.changetext);
        editText = findViewById(R.id.edittext);
        status = findViewById(R.id.status);
        size = findViewById(R.id.listsize);
        ListText = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        dbManager = new DBManager(this);
        Log.e("aaa", "init: " + dbManager.getAllListTextSaved().toString());
        size.setText(String.valueOf(dbManager.getAllListTextSaved().size()));
        nameRandom = new NameRandom();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID);

        adMobView = findViewById(R.id.adMobView);
        AdsUtils.showBanner(AdSize.BANNER,adMobView);
        splashBannerAd();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_privacy:
                privacy = new DialogCustom_Privacy(this);
                privacy.show();
                break;
            case R.id.menu_app:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "nickname.generator.free&fbclid=IwAR1SLSkKhmjOqTSJ88AnK6tpF8ML4_OCwlwoREM5eqdPwfPwTQI23K2JVp0")));
                break;
        }
        return false;
    }

    @Override
    public void OnTextListener(int position, String s) {
        DialogCustom dialogCustom = new DialogCustom(this);
        dialogCustom.init();
        dialogCustom.startGame();
        dialogCustom.show();
        dialogCustom.setContent(s);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Result", s);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
        checkSave(s);
        size.setText(String.valueOf(listCoppy.size()));
    }

    public void checkSave(String text) {
        boolean check = false;
        for (int i = 0; i < listCoppy.size(); i++) {
            TextCoppy temp = listCoppy.get(i);
            if (temp.getText().equals(text)) check = true;
        }
        if (!check) {
            listCoppy.add(new TextCoppy(text));
            String json = new GsonUtils().arrayToString(listCoppy);
            Log.e("listCoppy", json);
            SharedPreferencesUtils.saveData(this, Constant.LIST_COPPY, json);
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Text = editText.getText().toString();
        textAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.img_option, R.id.changetext, R.id.sc_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_option:

                break;
            case R.id.changetext:
                AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                    @Override
                    public void onOk() {
                        cnt_change_random++;
                        if (ok) {
                            Random random = new Random();
                            int pos = random.nextInt(nameRandom.size());
                            editText.setText(nameRandom.getName(pos));
                        } else {

                            ListText.clear();
                            for (int i = 0; i < 59; i++) {
                                ListText.add(new TextCoppy(Text));
                            }
                            change.setImageResource(R.mipmap.ic_loop);
                            status.setText("Aleatorio");
                            status.setTextSize(18);
                            textAdapter.notifyDataSetChanged();
                            ok = !ok;
                        }

                    }
                });


                break;
            case R.id.sc_copy:
                if (ok) {
                    ListText.clear();
                    String data = SharedPreferencesUtils.getStringData(this, Constant.LIST_COPPY);
                    Log.e("sc_copy", "sc_copy: " + data);
                    if (!TextUtils.isEmpty(data)) {
                        ListText = new GsonUtils().stringToArray(data);
                    }
//                    ListText = dbManager.getAllListTextSaved();
                    Collections.reverse(ListText);
                    textAdapter = new TextAdapter(ListText, this, this);
                    recyclerView.setAdapter(textAdapter);
                    textAdapter.notifyDataSetChanged();
                    change.setImageResource(R.mipmap.ic_back);
                    status.setText("ATRAS");
                    status.setTextSize(20);
                    ok = !ok;
                    AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                        @Override
                        public void onOk() {

                        }
                    });
                }
                break;
        }
    }

    public void startGame() {
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

}