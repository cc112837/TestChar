package com.henli.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/11/15.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper{

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库建表语�?
        db.execSQL("create table "+ FinalValue.TB_COLLECT+"(" +
                "_id integer primary key autoincrement," +
                "title varchar,"+"detail varchar)");
        db.execSQL("create table "+ FinalValue.TB_BODY+"(" +
                "_id integer primary key autoincrement," +
                "title varchar,"+"detail varchar)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
