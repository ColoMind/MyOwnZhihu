package colomind.com.myownzhihu.Tast;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOError;
import java.util.List;

import colomind.com.myownzhihu.Adapter.NewsAdapter;
import colomind.com.myownzhihu.Entity.News;
import colomind.com.myownzhihu.Http.Http;
import colomind.com.myownzhihu.Http.JsonHelper;

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
            newsList = JsonHelper.parseJsonToList(Http.getLastNewsList());
        }
        catch (IOError | JSONException e){

        }
        finally {
            return newsList;
        }
    }

    @Override
    protected void onPostExecute(List<News> newsList){
        adapter.refreshNewsList(newsList);
        if (listener != null){
            listener.afterTaskFinish();
        }

    }

    public interface onFinishListener{
        public void afterTaskFinish();
    }
}
