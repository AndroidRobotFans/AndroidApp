package com.one.duanone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/9  10:31.
 */
public class JokeDatabase{

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static final String name="joke.db";
    private static final int version = 1;


    public JokeDatabase(Context context){
        dbHelper = new DBHelper(context,name,version);
    }

}
