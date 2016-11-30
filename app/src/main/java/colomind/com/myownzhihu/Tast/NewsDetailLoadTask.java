package colomind.com.myownzhihu.Tast;

import android.os.AsyncTask;
import android.webkit.WebView;

import org.json.JSONException;

import java.io.IOException;

import colomind.com.myownzhihu.Entity.NewsDetail;
import colomind.com.myownzhihu.Http.Http;
import colomind.com.myownzhihu.Http.JsonHelper;

/**
 * Created by Administrator on 2016/11/5.
 */

public class NewsDetailLoadTask extends AsyncTask<Integer,Void,NewsDetail> {
    private WebView mWebView;
    public NewsDetailLoadTask(WebView webView){ this.mWebView = webView;}
    @Override
    protected NewsDetail doInBackground(Integer ... params){
        NewsDetail mNewsDetail = null;
        try {
            mNewsDetail = JsonHelper.parseJsonToDetail(Http.getNewsDetail(params[0]));
        }
        catch (IOException | JSONException e){

        }
        finally {
            return mNewsDetail;
        }
    }
    @Override
    protected void onPostExecute(NewsDetail mNewsDetail){
        String headerImage;
        if ( mNewsDetail.getImage()== null || mNewsDetail.getImage() == ""){
            headerImage = "file:///android_asset/news_detail_header_image.jpg";
        }
        else {
            headerImage = mNewsDetail.getImage();
        }
        StringBuilder sBuild = new StringBuilder();
        sBuild.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\"")
                .append(mNewsDetail.getTitle()).append("</h1>")
                .append("<span class =\"ima-source\">")
                .append(mNewsDetail.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + mNewsDetail.getBody().replace("<div class=\"img-place-holder\">",sBuild.toString());
        mWebView.loadDataWithBaseURL("file:///android_asset/",mNewsContent,"text/html","UTF-8",null);
    }

}
