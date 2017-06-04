package com.tribalscale.presenters;

import com.tribalscale.models.Person;
import com.tribalscale.network.CoreApi;
import com.tribalscale.network.responses.GetPersonsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by patel on 6/4/2017.
 */

public class MainPresenter extends BasePresenter<MainPresenter.MainView>{

    private static final int NUMBER_OF_RESULTS = 20;

    private Retrofit mRetrofit;

    @Inject
    public MainPresenter(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public void getPersons() {
        mRetrofit.create(CoreApi.class).getPersons(NUMBER_OF_RESULTS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPersonsResponse -> getView().showPersons(getPersonsResponse.getResults()),
                        throwable -> {
                            getView().showError();
                            throwable.printStackTrace();
                        });
     }

    public interface MainView {
        void showPersons(List<Person> persons);
        void showError();
    }
}
