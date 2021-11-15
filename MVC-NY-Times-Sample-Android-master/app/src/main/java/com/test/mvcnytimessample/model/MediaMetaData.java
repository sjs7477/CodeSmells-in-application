package com.test.mvcnytimessample.model;

import com.google.gson.annotations.SerializedName;

public class MediaMetaData {
    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private String height;


    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
