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

    private CoreApi mCoreApi;

    @Inject
    public MainPresenter(CoreApi coreApi) {
        mCoreApi = coreApi;
    }

    // Fetch the persons list from API call
    public void getPersons() {
        mCoreApi.getPersons(NUMBER_OF_RESULTS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetPersonsResponse>() {
                               @Override
                               public void accept(@NonNull GetPersonsResponse getPersonsResponse) throws Exception {
                                   getView().showPersons(getPersonsResponse.getResults());
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                getView().showError();
                                throwable.printStackTrace();
                            }
                        });
    }

    // Used to communicate between the view and presenter
    public interface MainView {
        void showPersons(List<Person> persons);
        void showError();
    }
}
