package com.test.mvcnytimessample.api;

import com.test.mvcnytimessample.model.MediaList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson("yyyy-MM-dd")))
                    .build();
        }
        return retrofit;
    }

    static Gson getGson(String dateFormatString) {
        return new GsonBuilder().setDateFormat(dateFormatString).registerTypeAdapter(MediaList.class, new JsonDeserializer<MediaList>() {
            @Override
            public MediaList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.isJsonArray()) {
                    Gson gson = new Gson();
                    return gson.fromJson(json, MediaList.class);
                }

                return null;
            }
        }).create();
    }
}
