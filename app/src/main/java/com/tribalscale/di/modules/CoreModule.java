package com.tribalscale.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tribalscale.network.CoreApi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by patel on 6/4/2017.
 */

@Module
public class CoreModule {
    String mBaseUrl;

    public CoreModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder = builder.addInterceptor(interceptor);
        builder = builder.connectTimeout(60, TimeUnit.SECONDS);

        return builder.build();
    }

    @Provides
    @Singleton
    CoreApi provideRetrofit(OkHttpClient okHttpClient) {
        CoreApi retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(CoreApi.class);
        return retrofit;
    }
}
