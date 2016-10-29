package colomind.com.myownzhihu.Tast;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOError;
import java.util.List;

import colomind.com.myownzhihu.Adapter.NewsAdapter;
import colomind.com.myownzhihu.Entity.News;

/**
 * Created by Administrator on 2016/10/29.
 */

public class NewsLoadTask extends AsyncTask<Void,Void,List<News>> {
    private NewsAdapter adapter;
    private onFinishListener listener;
    public  NewsLoadTask(NewsAdapter adapter){
        super();
        this.adapter = adapter;
    }
    public NewsLoadTask(NewsAdapter adapter,onFinishListener listener){
        super();
        this.adapter = adapter;
        this.listener = listener;
    }
    protected List<News> doInBackground(Void... params){
        List<News> newsList = null;
        try {

        }
        catch (IOError  e){
            e.printStackTrace();
        }
        finally {
            return newsList;
        }
    }


    public interface onFinishListener{
        public void afterTaskFinish();
    }
}
