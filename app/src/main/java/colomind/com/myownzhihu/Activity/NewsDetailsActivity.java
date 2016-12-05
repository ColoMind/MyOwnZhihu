package colomind.com.myownzhihu.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import colomind.com.myownzhihu.Entity.News;
import colomind.com.myownzhihu.R;
import colomind.com.myownzhihu.Tast.NewsDetailLoadTask;
import colomind.com.myownzhihu.Utility.Utility;
import colomind.com.myownzhihu.db.DailyNewsDB;

public class NewsDetailsActivity extends Activity {
    private Context mContext;
    private News news;
    private Boolean isFavorite = false;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        mContext = NewsDetailsActivity.this;
        getActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.web_view);
        setWebView(webView);
        news = (News) getIntent().getSerializableExtra("news");
        new NewsDetailLoadTask(webView).execute(news.getId());
        isFavorite = DailyNewsDB.getInstance(this).isFavorite(news);

    }

    public void setWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, News news) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, NewsDetailsActivity.class);
            i.putExtra("news", news);
            context.startActivity(i);
        } else {
            Utility.NoNetworkWarning(context);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (isFavorite) menu.findItem(R.id.menu_star).setIcon(R.drawable.fav_active);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_setting) {
            return true;
        }

        if (id == R.id.menu_star) {
            if (!isFavorite) {
                DailyNewsDB.getInstance(this).saveNews(news,mContext);
                item.setIcon(R.drawable.fav_active);
                isFavorite = true;
            } else {
                DailyNewsDB.getInstance(this).deleteFavorite(news,mContext);
                item.setIcon(R.drawable.fav_normal);
                isFavorite = false;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
