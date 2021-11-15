package ua.hanasaka.mvc2.view;

import java.util.List;

import ua.hanasaka.mvc2.model.data.Repo;

/**
 * Created by Oleksandr Molodykh on 16.05.2017.
 */

public interface View {
    void showData(List<Repo> list);

    void showError(String error);

    void showEmptyList();

    String getUserName();
}
