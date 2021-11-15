package com.test.mvcnytimessample.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
    @SerializedName("media-metadata")
    List<MediaMetaData> mediaMetaDatas;

    public List<MediaMetaData> getMediaMetaDatas() {
        return mediaMetaDatas;
    }
}
