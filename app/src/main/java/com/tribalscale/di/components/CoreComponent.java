package com.tribalscale.di.components;

import com.tribalscale.activities.MainActivity;
import com.tribalscale.di.modules.AppModule;
import com.tribalscale.di.modules.CoreModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by patel on 6/4/2017.
 */

@Singleton
@Component(modules = {AppModule.class, CoreModule.class})
public interface CoreComponent {
    void inject(MainActivity activity);
}
