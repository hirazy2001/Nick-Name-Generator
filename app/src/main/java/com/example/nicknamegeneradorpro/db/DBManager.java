package com.example.nicknamegeneradorpro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nicknamegeneradorpro.model.TextCoppy;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "generator_text";
    private final String content = "CONTENT";
    private final String TABLE_NAME = "Text";

    String CREATE_TABLE_TEXT = "Create table " +TABLE_NAME+ "( " +
            content +" TEXT)";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEXT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(Object o){

    }
    public void addText(TextCoppy text){
        if(CheckExist(text.getText())){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(content, text.getText());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }
    }

    public ArrayList<TextCoppy> getAllListTextSaved(){
        ArrayList<TextCoppy> List = new ArrayList<>();
        String selectQuery = "Select * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                TextCoppy text = new TextCoppy();
                text.setText(String.valueOf(cursor.getString(0)));
                List.add(text);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return List;
    }

    public boolean CheckExist( String fieldValue) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
//        String Query = "Select * from " + TABLE_NAME + " where " + content + " = " + fieldValue;
        String query2 = "SELECT * FROM TEXT WHERE CONTENT =?";
        //sqldb.rawQuery()
        Cursor cursor = sqldb.rawQuery(query2, new String[] {fieldValue});
        if(cursor.getCount() == 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
