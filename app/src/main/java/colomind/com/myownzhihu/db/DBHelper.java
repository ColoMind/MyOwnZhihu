package colomind.com.myownzhihu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Administrator on 2  016/12/1.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "news_daily.db";
    public static final String TABLE_NAME = "news_daily_fav";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NEWS_ID = "news_id";
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_IMAGE = "news_image";

    public static final String DATABASE_CREATE
            = "CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NEWS_ID + " INTEGER UNIQUE,"
            + COLUMN_NEWS_TITLE + " TEXT,"
            + COLUMN_NEWS_IMAGE + " TEXT)";

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
