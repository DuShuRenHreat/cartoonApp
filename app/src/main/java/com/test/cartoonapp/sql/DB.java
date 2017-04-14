package com.test.cartoonapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC-2 on 2017/4/14.
 */

public class DB extends SQLiteOpenHelper {
    public static final String TABLENAME = "tb_collection";
    public DB(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLENAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(25) NOT NULL," +
                "url VARCHAR(255) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
