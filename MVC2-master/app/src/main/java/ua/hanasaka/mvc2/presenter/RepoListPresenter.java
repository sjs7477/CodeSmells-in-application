package ua.hanasaka.mvc2.presenter;

import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import ua.hanasaka.mvc2.model.Model;
import ua.hanasaka.mvc2.model.ModelImpl;
import ua.hanasaka.mvc2.model.data.Repo;
import ua.hanasaka.mvc2.view.View;

/**
 * Created by Oleksandr Molodykh on 16.05.2017.
 */

public class RepoListPresenter implements Presenter {
    private String TAG = "myLogs";
    private Model model = new ModelImpl();
    private Subscription subscription = Subscriptions.empty();
    private View view;

    public RepoListPresenter(View v){
        this.view = v;
    }

    @Override
    public void onSearchButtonClick() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        Log.d(TAG, "RepoListPresenter on Click");

        subscription = model.getRepoList(view.getUserName())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onComleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Log.d(TAG, "onError "+e.getClass()+ " mess "+e.getMessage());
                    }

                    @Override
                    public void onNext(List<Repo> data) {
                        Log.d(TAG, "onNext");
                        if (data != null && !data.isEmpty()) {
                            view.showData(data);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
