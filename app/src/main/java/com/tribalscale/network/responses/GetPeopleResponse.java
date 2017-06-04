package com.tribalscale.network.responses;

import com.tribalscale.models.Person;

import java.util.List;

/**
 * Created by patel on 6/4/2017.
 */

public class GetPeopleResponse {
    private List<Person> results;

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }
}
