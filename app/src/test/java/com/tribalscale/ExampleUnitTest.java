package com.tribalscale;

import com.tribalscale.models.Person;
import com.tribalscale.network.CoreApi;
import com.tribalscale.network.responses.GetPersonsResponse;
import com.tribalscale.presenters.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
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

    @Before
    public void setUp() {
        apiService = mock(CoreApi.class);
        getPersonsResponse = mock(GetPersonsResponse.class);

        mainPresenter = new MainPresenter(apiService);

//        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
//            @Override
//            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
//                return Schedulers.trampoline();
//            }
//        });

        RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

//        RxJavaHooks.setOnIOScheduler(new Func1<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler call(Scheduler scheduler) {
//                return Schedulers.immediate();
//            }
//        });
    }

    @After
    public void teardown() {
        RxJavaPlugins.reset();
    }

    @Test
    public void test() throws IOException {
        when(apiService.getPersons(20)).thenReturn(Observable.just(getPersonsResponse));

        mainPresenter.getPersons();

        verify(mainPresenter.getView(), times(1)).showPersons(Collections.<Person>emptyList());
    }
}