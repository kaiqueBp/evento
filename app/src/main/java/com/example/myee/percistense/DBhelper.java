package com.example.myee.percistense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(@Nullable Context context) {
        super(context, "att", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sql = new StringBuffer();
        sql.append("create table evento("+
                "id integer primary key,"+
                "nome varchar(50),"+
                "data varchar(100),"+
                "categoria varchar(100)"+
                ");");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
