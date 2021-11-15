package com.test.mvcnytimessample.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {
    public List<Article> getArticles() {
        return articles;
    }

    @SerializedName("results")
    private List<Article> articles;


}
