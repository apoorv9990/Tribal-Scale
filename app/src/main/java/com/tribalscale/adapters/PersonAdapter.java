package com.tribalscale.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.tribalscale.models.Person;
import com.tribalscale.views.viewholders.PersonViewHolder;

/**
 * Created by patel on 6/4/2017.
 */

public class PersonAdapter extends BaseRecyclerAdapter<Person, PersonViewHolder> {

    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

    }
}
