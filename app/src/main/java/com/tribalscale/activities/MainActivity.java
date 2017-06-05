package com.tribalscale.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tribalscale.R;
import com.tribalscale.TribalScaleApplication;
import com.tribalscale.adapters.PersonAdapter;
import com.tribalscale.models.Person;
import com.tribalscale.presenters.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Activity that shows the persons list
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    @BindView(R.id.recycler_view_persons)
    public RecyclerView personsRecyclerView;

    @Inject
    MainPresenter presenter;
    
    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TribalScaleApplication.getCoreComponent().inject(this);

        mAdapter = new PersonAdapter(this, new PersonAdapter.Interactor() {
            @Override
            public void onRowClicked(Person person) {
                DetailActivity.startDetailActivity(MainActivity.this, person);
            }
        });

        personsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        personsRecyclerView.setAdapter(mAdapter);
        
        presenter.attachView(this);
        presenter.getPersons();
    }

    @Override
    public void showPersons(List<Person> persons) {
        mAdapter.setItems(persons);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Oops something went wrong", Toast.LENGTH_SHORT).show();
    }
}
