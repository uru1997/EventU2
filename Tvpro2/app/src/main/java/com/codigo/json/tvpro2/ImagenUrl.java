package com.codigo.json.tvpro2;

/**
 * Created by narva on 1/05/2018.
 */

public class ImagenUrl {
    private String url;

    public ImagenUrl(){

    }

    public ImagenUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImagenUrl{" +
                "url='" + url + '\'' +
                '}';
    }
}
