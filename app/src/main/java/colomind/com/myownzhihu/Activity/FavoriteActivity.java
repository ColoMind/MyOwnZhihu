package colomind.com.myownzhihu.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import colomind.com.myownzhihu.Adapter.NewsAdapter;
import colomind.com.myownzhihu.Entity.News;
import colomind.com.myownzhihu.R;
import colomind.com.myownzhihu.db.DailyNewsDB;

public class FavoriteActivity extends Activity implements AdapterView.OnItemClickListener{
    private List<News> fav_list;
    private ListView fav_view;
    private NewsAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_layout);
        fav_list = DailyNewsDB.getInstance(this).loadFavorite();
        fav_view = (ListView) findViewById(R.id.fav_list);
        adapter = new NewsAdapter(this,R.layout.newslist_item,fav_list);
        fav_view.setAdapter(adapter);
        fav_view.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
        NewsDetailsActivity.startActivity(this,adapter.getItem(position));
    }
}
