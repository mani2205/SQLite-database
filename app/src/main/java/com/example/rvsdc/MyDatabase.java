package com.example.rvsdc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper
{
    private static final String DB_NAME="MyDB";
    private static final String TB_NAME="Login";
    private static final String COL0_ID="USER_ID";
    private static final String COL1_NAME="NAME";
    private static final String COL2_EMAIL="EMAIL";
    private static final String COL3_PASSWORD="PASSWORD";


    public MyDatabase(Context context) {
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create=" create table if not exists "+TB_NAME+"("+COL0_ID+ " text primary key ,"+COL1_NAME+" varchar ,"+COL2_EMAIL+" varchar ,"+COL3_PASSWORD+" varchar );";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade=" DROP TABLE IF EXISTS "+TB_NAME;
        db.execSQL(upgrade);
        onCreate(db);
    }

    public boolean Insert(String id,String name, String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL0_ID,id);
        contentValues.put(COL1_NAME,name);
        contentValues.put(COL2_EMAIL,email);
        contentValues.put(COL3_PASSWORD,password);

        long results= db.insert(TB_NAME,null,contentValues);

        if (results==-1)
            return false;//notInserted
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String viewdata="select * from "+TB_NAME;

        Cursor res=db.rawQuery(viewdata,null);
        //res.close();

        return res;
    }
}
