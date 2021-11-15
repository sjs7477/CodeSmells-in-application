package ua.hanasaka.mvc2.model.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import ua.hanasaka.mvc2.model.data.Repo;

/**
 * Created by Oleksandr Molodykh on 15.05.2017.
 */

public interface ApiInterface {
    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepositories(@Path("user") String user);
}
