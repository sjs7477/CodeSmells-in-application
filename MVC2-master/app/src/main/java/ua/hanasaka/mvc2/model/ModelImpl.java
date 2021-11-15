package ua.hanasaka.mvc2.model;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.hanasaka.mvc2.model.api.ApiInterface;
import ua.hanasaka.mvc2.model.api.ApiModule;
import ua.hanasaka.mvc2.model.data.Repo;

/**
 * Created by Oleksandr Molodykh on 15.05.2017.
 */

public class ModelImpl implements Model {
    ApiInterface apiInterface = ApiModule.getApiInterface();
    @Override
    public Observable<List<Repo>> getRepoList(String name) {
        return apiInterface.getRepositories(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
