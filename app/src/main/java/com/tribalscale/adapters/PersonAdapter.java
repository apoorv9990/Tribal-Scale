package com.tribalscale.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tribalscale.R;
import com.tribalscale.models.Person;
import com.tribalscale.views.viewholders.PersonViewHolder;

/**
 * Created by patel on 6/4/2017.
 *
 * Displays persons in a list
 */

public class PersonAdapter extends BaseRecyclerAdapter<Person, PersonViewHolder> {

    private Interactor mInteractor;

    public PersonAdapter(Context context, Interactor interactor) {
        super(context);
        mInteractor = interactor;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.persons_row, null);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person currentPerson = getItem(position);

        holder.bind(currentPerson, mInteractor);
    }

    public interface Interactor {
        void onRowClicked(Person person);
    }
}
