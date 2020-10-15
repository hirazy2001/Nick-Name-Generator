package com.example.nicknamegeneradorpro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicknamegeneradorpro.R;
import com.example.nicknamegeneradorpro.model.TextCoppy;
import com.example.nicknamegeneradorpro.db.DBManager;
import com.example.nicknamegeneradorpro.ui.MainActivity;
import com.example.nicknamegeneradorpro.utils.AsignedFont;

import java.util.ArrayList;
import java.util.Map;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    ArrayList<TextCoppy> ListText;
    Context context;
    DBManager dbManager;
    OnText onText;

    public TextAdapter(ArrayList<TextCoppy> listText, Context context, OnText ontext) {
        ListText = listText;
        this.context = context;
        dbManager = new DBManager(context);
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
        if (MainActivity.ok) {
            final TextCoppy text = ListText.get(position);
            Map<Integer, String> map = new AsignedFont().map(position);
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
                    dbManager.addText(new TextCoppy(MainActivity.text1
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