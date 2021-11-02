package com.example.nicknameff.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nicknameff.R;
import com.example.nicknameff.model.Config;
import java.util.ArrayList;

public class MoreAppsAdapter extends RecyclerView.Adapter<MoreAppsAdapter.View1> {

    ArrayList<Config.App> ListApp;
    Context context;
    OnAd onAd;
    @NonNull
    @Override
    public View1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_apps, parent, false);
        return new View1(view);
    }

    public MoreAppsAdapter(ArrayList<Config.App> listApp, Context context, OnAd onAd){
        this.ListApp = listApp;
        this.context = context;
        this.onAd = onAd;
    }
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull View1 holder, int position) {
        Config.App app = ListApp.get(position);
        Glide.with(context)
                .load(app.icon)
                .into(holder.symbol);
        holder.name.setText(app.getName());
        holder.contentapp.setText(app.getContent());
        PackageManager packageManager = context.getPackageManager();
        if(isPackageInstalled(app.link, packageManager)){
            holder.install.setText("INSTALLED");
        }
        Glide.with(context)
                .load(app.s1)
                .into(holder.banner1);
        Glide.with(context)
                .load(app.s2)
                .into(holder.banner2);
        Glide.with(context)
                .load(app.s3)
                .into(holder.banner3);

        holder.install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAd.OnAdListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListApp.size();
    }

    public class View1 extends RecyclerView.ViewHolder {
        ImageView symbol, banner1, banner2, banner3;
        TextView name, contentapp;
        Button install;
        public View1(View itemview) {
            super(itemview);
            symbol = itemview.findViewById(R.id.symbol1);
            name = itemview.findViewById(R.id.name);
            install = itemview.findViewById(R.id.install);
            contentapp  =   itemview.findViewById(R.id.contentapp);
            banner1 = itemView.findViewById(R.id.banner1);
            banner2 = itemview.findViewById(R.id.banner2);
            banner3 = itemview.findViewById(R.id.banner3);
        }
    }

    public interface OnAd{
        void OnAdListener(int position);
    }
    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        boolean found = true;
        try {
            packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            found = false;
        }
        return found;
    }
}
