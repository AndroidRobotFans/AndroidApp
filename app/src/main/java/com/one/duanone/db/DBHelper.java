package com.one.duanone.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/9  10:00.
 */
public class DBHelper extends SQLiteOpenHelper {

    private String sql ;
    public DBHelper(Context context, String name, int version,String sql) {
        this(context, name, null, version);
        this.sql = sql;

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
