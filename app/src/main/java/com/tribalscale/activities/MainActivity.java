package com.tribalscale.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tribalscale.R;
import com.tribalscale.TribalScaleApplication;
import com.tribalscale.presenters.MainPresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TribalScaleApplication.getCoreComponent().inject(this);

        presenter.getPeople();
    }
}
