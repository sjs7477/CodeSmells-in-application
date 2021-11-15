package com.test.mvcnytimessample.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Article {
    @SerializedName("url")
    private String url;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String abstractText;

    @SerializedName("byline")
    private String byLine;

    @SerializedName("published_date")
    private Date publishedDate;

    @SerializedName("media")
    private MediaList media;

    public Article(String url, String title, String abstractText, String byLine, Date publishedDate) {
        this.url = url;
        this.title = title;
        this.abstractText = abstractText;
        this.byLine = byLine;
        this.publishedDate = publishedDate;
    }


    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getByLine() {
        return byLine;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public MediaList getMedia() {
        return media;
    }
}
