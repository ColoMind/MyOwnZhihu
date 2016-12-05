package colomind.com.myownzhihu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import colomind.com.myownzhihu.Entity.News;

/**
 * Created by Administrator on 2016/12/1.
 */

public class DailyNewsDB {
    private DBHelper dBhelper;
    private SQLiteDatabase db;
    private static DailyNewsDB mDailyNewsDB;
    private String[] allColumns = {DBHelper.COLUMN_ID,DBHelper.COLUMN_NEWS_ID,DBHelper.COLUMN_NEWS_TITLE,DBHelper.COLUMN_NEWS_IMAGE};

    private DailyNewsDB(Context context){
        dBhelper = new DBHelper(context);
        db = dBhelper.getWritableDatabase();
    }

    public synchronized static DailyNewsDB getInstance(Context context){
        if (mDailyNewsDB == null){
            mDailyNewsDB = new DailyNewsDB(context);
        }
        return mDailyNewsDB;
    }

    public void saveNews(News news,Context context){
        if (news != null) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_NEWS_ID, news.getId());
            values.put(DBHelper.COLUMN_NEWS_TITLE, news.getTitle());
            values.put(DBHelper.COLUMN_NEWS_IMAGE, news.getImage());
            db.insert(DBHelper.TABLE_NAME, null, values);
            Toast.makeText(context,"save success!",Toast.LENGTH_SHORT).show();
        }
    }

    public List<News> loadFavorite(){
        List<News> fav_list = new ArrayList<News>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                News news = new News();
                news.setId(cursor.getInt(1));
                news.setTitle(cursor.getString(2));
                news.setImage(cursor.getString(3));
                fav_list.add(news);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return fav_list;
    }

    public boolean isFavorite(News news){
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.COLUMN_NEWS_ID + " = ?", new String[]{news.getId() + ""}, null, null, null);
        if (cursor.moveToNext()){
            cursor.close();
            return true;
        }
        else {
            return false;
        }
    }
    public void deleteFavorite(News news,Context context){
        if (news != null) {
            db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NEWS_ID + " = ?", new String[]{news.getId() + ""});
            Toast.makeText(context,"delete success!",Toast.LENGTH_SHORT).show();
        }
    }

    public synchronized void closeDB(){
        if (mDailyNewsDB != null){
            db.close();
        }
    }

}
