package colomind.com.myownzhihu.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import colomind.com.myownzhihu.Entity.News;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context,int resource){
        super(context,resource);
    }

}
