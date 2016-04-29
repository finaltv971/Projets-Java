package com.example.rss;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Mybdd extends SQLiteOpenHelper{

    //nom de ma table
    private static final String TABLEBDD="ma table";

    //id
    private static final String ID="IdRSs";

    //version
    private static final int DATABASE_VERSION =1;

    //nom de base
    static final String DATABASE_NAME ="rss-DATABASE.db";

    //colonne
    private static final String rssName ="Rss Name's";

    private static  String createSqliteBdd="";

    private static final String COLUMNRss="urlRss";

    private static final String COLUMNPFROM="provenance";

    private static final String COLUMNCATEGORIE="categorie";

    private SQLiteDatabase db;

    public Mybdd(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        createSqliteBdd="Create Table"+TABLEBDD+"("+ID+"INTEGER PRIMARY KEY AUTOINCREMENT ,"+COLUMNRss+"TEXT,"+rssName+"TEXT,"+COLUMNPFROM+"TEXT,"+COLUMNCATEGORIE+"TEXT"+")";
        db.execSQL(createSqliteBdd);
        Log.i("database","la bdd est creer");
    }
    public void onAddvalue(String nom,String url){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(rssName,nom);
        values.put(COLUMNRss,url);
        db.insert(TABLEBDD,null,values);
        db.close();

    }

    public void onAddvalue(String url,String provenance,String categorie){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMNRss,url);
        values.put(COLUMNPFROM,provenance);
        values.put(COLUMNCATEGORIE,categorie);
        db.insert(TABLEBDD,null,values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(rssName,"");

        //insertion
        long insertion=db.insert(TABLEBDD,null,values);
        onCreate(db);
    }


	
}
