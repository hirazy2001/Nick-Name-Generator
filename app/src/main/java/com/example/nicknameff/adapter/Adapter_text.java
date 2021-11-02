package com.example.nicknameff.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicknameff.R;
import com.example.nicknameff.model.TextC;
import com.example.nicknameff.db.DB;
import com.example.nicknameff.ui.MainActivity;
import com.example.nicknameff.utils.Font_Assign;

import java.util.ArrayList;
import java.util.Map;

public class Adapter_text extends RecyclerView.Adapter<Adapter_text.ViewHolder> {

    ArrayList<TextC> ListText;
    Context context;
    DB db;
    OnText onText;

    public Adapter_text(ArrayList<TextC> listText, Context context, OnText ontext) {
        ListText = listText;
        this.context = context;
        db = new DB(context);
        onText = ontext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_textadapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/shoju_font.ttf");
        holder.copy.setTypeface(type);
        if (MainActivity.ok) {
            final TextC text = ListText.get(position);
            Map<Integer, String> map = new Font_Assign().map(position);
            String res = "";
            for (int i = 0; i < MainActivity.Text.length(); i++) {
                int c = (int) MainActivity.Text.charAt(i);
                if (MainActivity.Text.charAt(i) == ' ') {
                    res += " ";
                    continue;
                }
                if (c < 65 || (c > 90 && c < 97) || c > 122) {
                    res += MainActivity.Text.charAt(i);
                    continue;
                }
                res += map.get(c);
            }

            holder.text.setText(MainActivity.text1 + MainActivity.text2 + res + MainActivity.text2 + MainActivity.text3);

            String finalRes = res;
            holder.copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.addText(new TextC(MainActivity.text1
                            + MainActivity.text2
                            + finalRes + MainActivity.text2
                            + MainActivity.text3));
                    Log.d("ádasd", "ádas");
                        onText.OnTextListener(position, MainActivity.text1 + MainActivity.text2 + finalRes + MainActivity.text2 + MainActivity.text3);
                }
            });
        } else {
            holder.text.setText(ListText.get(position).getText());
            holder.copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ádasd", "ádas");
//                    dbManager.addText(new Text(MainActivity.text1 + MainActivity.text2 + res + MainActivity.text2 + MainActivity.text3));
                    onText.OnTextListener(position, ListText.get(position).getText());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ListText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        Button copy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textadapter);
            copy = itemView.findViewById(R.id.copy);
        }
    }

    public interface OnText {
        void OnTextListener(int position, String s);
    }
}