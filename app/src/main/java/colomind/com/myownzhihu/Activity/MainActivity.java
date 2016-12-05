package colomind.com.myownzhihu.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import colomind.com.myownzhihu.Adapter.NewsAdapter;
import colomind.com.myownzhihu.R;
import colomind.com.myownzhihu.Tast.NewsLoadTask;
import colomind.com.myownzhihu.Utility.Utility;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private ListView news_list;
    private SwipeRefreshLayout refreshLayout ;
    private NewsAdapter adapter;
    private boolean isConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_layout);
        isConnected = Utility.checkNetworkConnection(this);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        );
        news_list = (ListView) findViewById(R.id.news_list);
        setTitle(getTime());
        adapter = new NewsAdapter(this,R.layout.newslist_item);
        news_list.setAdapter(adapter);
        news_list.setOnItemClickListener(this);
        if (isConnected){
            new NewsLoadTask(adapter).execute();
        }
        else {
            Utility.NoNetworkWarning(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menu_setting){
            return true;
        }
        else if (id == R.id.menu_star){
            Intent intent = new Intent(this,FavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh(){
        if (isConnected){
            new NewsLoadTask(adapter, new NewsLoadTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute();
        }
        else {
            Utility.NoNetworkWarning(this);
            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view,int position,long id){
        NewsDetailsActivity.startActivity(this,adapter.getItem(position));
    }


    @TargetApi(24)
    private String getTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.date_format));
        return dateFormat.format(c.getTime());
    }
}








