package colomind.com.myownzhihu.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import colomind.com.myownzhihu.Entity.News;
import colomind.com.myownzhihu.Utility.Utility;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
