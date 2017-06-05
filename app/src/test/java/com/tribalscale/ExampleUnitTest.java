package com.tribalscale;

import com.tribalscale.models.Person;
import com.tribalscale.network.CoreApi;
import com.tribalscale.network.responses.GetPersonsResponse;
import com.tribalscale.presenters.MainPresenter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    CoreApi apiService;

    GetPersonsResponse getPersonsResponse;

    private MainPresenter mainPresenter;

    private MainPresenter.MainView mView;

    @BeforeClass
    public static void setUpSuite() {
        final Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(@android.support.annotation.NonNull Runnable runnable) {
                        runnable.run();
                    }
                });
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
    }

    @AfterClass
    public static void tearDownSuite() {
        RxJavaPlugins.reset();;
    }

    @Before
    public void setUp() {
        apiService = mock(CoreApi.class);
        getPersonsResponse = mock(GetPersonsResponse.class);
        mView = mock(MainPresenter.MainView.class);

        mainPresenter = new MainPresenter(apiService);
    }

    @Test
    public void checkSuccessfulApiCall() throws IOException {
        when(apiService.getPersons(20)).thenReturn(Observable.just(getPersonsResponse));

        mainPresenter.attachView(mView);
        mainPresenter.getPersons();

        verify(mainPresenter.getView(), times(1)).showPersons(Collections.<Person>emptyList());
        verify(mainPresenter.getView(), times(0)).showError();
    }

    @Test
    public void checkErrorCall() throws IOException {
        when(apiService.getPersons(20)).thenReturn(Observable.<GetPersonsResponse>error(new IOException()));

        mainPresenter.attachView(mView);
        mainPresenter.getPersons();

        verify(mainPresenter.getView(), times(1)).showError();
        verify(mainPresenter.getView(), times(0)).showPersons(Collections.<Person>emptyList());
    }
}