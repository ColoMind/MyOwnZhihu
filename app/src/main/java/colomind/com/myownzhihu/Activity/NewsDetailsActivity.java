package colomind.com.myownzhihu.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

import colomind.com.myownzhihu.Entity.News;
import colomind.com.myownzhihu.R;
import colomind.com.myownzhihu.Utility.Utility;

public class NewsDetailsActivity extends AppCompatActivity {
    private News news;
    private Boolean isFavorite = false;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        webView = (WebView) findViewById(R.id.web_view);
        setWebView(webView);
        news = (News) getIntent().getSerializableExtra("news");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        if (isFavorite) menu.findItem(R.id.menu_star).setIcon(R.drawable.fav_normal);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menu_star){
            isFavorite = true;
            item.setIcon(R.drawable.fav_active);

            return true;
        }
        else if (id == R.id.menu_setting){
            Toast.makeText(this,"U Clicked setting",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setWebView(WebView webView){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, News news){
        if (Utility.checkNetworkConnection(context)){
            Intent i = new Intent(context,NewsDetailsActivity.class);
            i.putExtra("news",news);
            context.startActivity(i);
        }
        else {
            Utility.NoNetworkWarning(context);
        }
    }
}
