package com.example.litv.databaselistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by litv on 03/12/16.
 */

public class MyDataBAse extends SQLiteOpenHelper {

    public static final String baseName="maBase.db";
    public static final String champs1="prenom";
    public static final String tableName="TablePrenom";



    public MyDataBAse(Context context) {
        super(context, baseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TablePrenom (ID INTEGER PRIMARY KEY AUTOINCREMENT, prenom TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST TablePrenom");
        onCreate(db);
    }

    public void putdata(String valeur){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(champs1,valeur);
        db.insert(tableName,null,values);
        db.close();
        MainActivity mainActivity=new MainActivity();
    }

    public ArrayList<String> readdata (){

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+champs1+" FROM "+tableName,null);
        ArrayList<String>arrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(cursor.getColumnIndex(champs1)));
        }
        return arrayList;
    }

    public ArrayList<String> clean_table(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("DELETE FROM "+tableName,null);
        ArrayList<String>arrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(cursor.getColumnIndex(champs1)));
        }
        db.close();
        return arrayList;
    }
}
