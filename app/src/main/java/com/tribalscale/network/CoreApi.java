package com.tribalscale.network;

import com.tribalscale.network.responses.GetPersonsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by patel on 6/4/2017.
 */

public interface CoreApi {
    @GET("api/")
    Observable<GetPersonsResponse> getPersons(@Query("results") int numberOfResults);
}
