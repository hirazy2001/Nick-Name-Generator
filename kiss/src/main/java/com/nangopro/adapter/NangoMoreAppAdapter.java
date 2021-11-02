package com.nangopro.adapter;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nangopro.R;
import com.nangopro.obj.NangoOConfig;

import java.util.List;

public class NangoMoreAppAdapter extends RecyclerView.Adapter<NangoMoreAppAdapter.MyViewHolder> {
    private List<NangoOConfig.App> moreApps;
    private LayoutInflater mLayoutInflater;
    private Activity mContext;
    final PackageManager pm;
    List<ApplicationInfo> packages;
    Display display;

    public NangoMoreAppAdapter(Activity context, List<NangoOConfig.App> moreApps) {
        this.moreApps = moreApps;

        this.mContext = context;
        display = mContext.getWindowManager().getDefaultDisplay();
        mLayoutInflater = LayoutInflater.from(context);
        pm = mContext.getPackageManager();
        packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView tv_name;
        TextView tv_des;
        ImageView s1, s2, s3;
        Button btnInstall;


        public MyViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.ic_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_des = itemView.findViewById(R.id.tv_des);
            s1 = itemView.findViewById(R.id.s1);
            s2 = itemView.findViewById(R.id.s2);
            s3 = itemView.findViewById(R.id.s3);
            btnInstall = itemView.findViewById(R.id.btnInstall);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.item_more, parent, false);
        return new MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NangoOConfig.App app = moreApps.get(position);
        holder.tv_name.setText(app.name);
        holder.tv_des.setText(app.des);
        holder.tv_name.setText(app.name);
//        android.view.ViewGroup.LayoutParams layoutParams = holder.s1.getLayoutParams();
//        layoutParams.width = -40 + display.getWidth() / 3;
//        holder.s1.setLayoutParams(layoutParams);


        Glide.with(mContext).load(app.s1).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("tikfans: ", "onResourceReady: " + resource.getIntrinsicWidth());
                ViewGroup.LayoutParams layoutParams = holder.s1.getLayoutParams();
                if (resource.getIntrinsicWidth() >= 1000) {
                    layoutParams.width = -40 + display.getWidth();
                    layoutParams.height = layoutParams.width / 2 + 100;
                } else {
                    layoutParams.width = -40 + display.getWidth() / 3;
                }
                holder.s1.setLayoutParams(layoutParams);
                return false;
            }
        }).into(holder.s1);
        Glide.with(mContext).load(app.s2).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("tikfans: ", "onResourceReady: " + resource.getIntrinsicWidth());
                ViewGroup.LayoutParams layoutParams = holder.s2.getLayoutParams();
                if (resource.getIntrinsicWidth() >= 1000) {
                    layoutParams.width = -40 + display.getWidth();
                    layoutParams.height = layoutParams.width / 2 + 100;
                } else {
                    layoutParams.width = -40 + display.getWidth() / 3;
                }
                holder.s2.setLayoutParams(layoutParams);
                return false;
            }
        }).into(holder.s2);
        Glide.with(mContext).load(app.s3).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("tikfans: ", "onResourceReady: " + resource.getIntrinsicWidth());
                ViewGroup.LayoutParams layoutParams = holder.s3.getLayoutParams();
                if (resource.getIntrinsicWidth() >= 1000) {
                    layoutParams.width = -40 + display.getWidth();
                    layoutParams.height = layoutParams.width / 2 + 100;
                } else {
                    layoutParams.width = -40 + display.getWidth() / 3;
                }
                holder.s3.setLayoutParams(layoutParams);
                return false;
            }
        }).into(holder.s3);
        Glide.with(mContext).load(app.icon).into(holder.icon);

        final Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(app.id);
        if (intent != null) {
            holder.btnInstall.setText("OPPEN");
            holder.btnInstall.setBackgroundColor(mContext.getResources().getColor(R.color.oppen));
        } else {
            holder.btnInstall.setText("INSTALL");
            holder.btnInstall.setBackgroundColor(mContext.getResources().getColor(R.color.color_install));
        }

        holder.btnInstall.setOnClickListener(view -> {


            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            } else {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse("market://details?id=" + app.id));
                mContext.startActivity(i);
            }

        });

    }

    @Override
    public int getItemCount() {
        return moreApps.size();
    }

}
