package com.tribalscale.presenters;

import com.tribalscale.network.CoreApi;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by patel on 6/4/2017.
 */

public class MainPresenter {

    private Retrofit mRetrofit;

    @Inject
    public MainPresenter(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public void getPeople() {
        mRetrofit.create(CoreApi.class).getPeople().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() {
            @Override
            public void accept(@NonNull JSONObject jsonObject) throws Exception {
                System.err.println(jsonObject);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }
}
