package ua.hanasaka.mvc2.model;

import java.util.List;

import rx.Observable;
import ua.hanasaka.mvc2.model.data.Repo;

/**
 * Created by Oleksandr Molodykh on 15.05.2017.
 */

public interface Model {
    Observable<List<Repo>> getRepoList(String name);
}
