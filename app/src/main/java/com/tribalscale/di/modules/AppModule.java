package com.tribalscale.di.modules;

import com.tribalscale.TribalScaleApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patel on 6/4/2017.
 */

@Module
public class AppModule {
    TribalScaleApplication mApplication;

    public AppModule(TribalScaleApplication  application) {
        mApplication = application;
    }

    // Provides TribalScaleApplication object through dependency injection
    @Singleton
    @Provides
    TribalScaleApplication providesApplication() {
        return mApplication;
    }
}
