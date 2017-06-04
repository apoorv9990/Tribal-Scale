package com.tribalscale;

import android.app.Activity;
import android.app.Application;

import com.tribalscale.di.components.CoreComponent;
import com.tribalscale.di.components.DaggerCoreComponent;
import com.tribalscale.di.modules.CoreModule;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

/**
 * Created by patel on 6/4/2017.
 */

public class TribalScaleApplication extends Application implements HasDispatchingActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static CoreComponent mCoreComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mCoreComponent = DaggerCoreComponent.builder()
                .coreModule(new CoreModule(getResources().getString(R.string.base_url)))
                .build();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static CoreComponent getCoreComponent() {
        return mCoreComponent;
    }
}
