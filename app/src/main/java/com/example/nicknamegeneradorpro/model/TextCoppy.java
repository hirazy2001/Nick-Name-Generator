package com.example.nicknamegeneradorpro.model;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class TextCoppy implements Serializable {
    String text;

    public TextCoppy(){};

    public TextCoppy(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, TextCoppy.class);
    }
}
