package com.tribalscale.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tribalscale.R;
import com.tribalscale.TribalScaleApplication;
import com.tribalscale.models.Person;
import com.tribalscale.presenters.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView{

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TribalScaleApplication.getCoreComponent().inject(this);

        presenter.attachView(this);
        presenter.getPeople();
    }

    @Override
    public void showPeople(List<Person> persons) {
    }

    @Override
    public void showError() {

    }
}
