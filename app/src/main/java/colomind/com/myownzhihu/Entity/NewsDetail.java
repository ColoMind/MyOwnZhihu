package colomind.com.myownzhihu.Entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/30.
 */

public class NewsDetail {
    private String body;
    private String image_source;
    private String share_url;
    private String image;
    private String title;
    private ArrayList<String> js;
    private int type;
    private String ga_prefix;
    private long id;
    private ArrayList<String> css;

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCss(ArrayList<String> css) {
        this.css = css;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setJs(ArrayList<String> js) {
        this.js = js;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getCss() {
        return css;
    }

    public int getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getImage() {
        return image;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getTitle() {
        return title;
    }


    public ArrayList<String> getJs() {
        return js;
    }

}
