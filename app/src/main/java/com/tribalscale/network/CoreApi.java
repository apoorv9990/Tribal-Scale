package com.tribalscale.network;

import com.tribalscale.network.responses.GetPeopleResponse;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by patel on 6/4/2017.
 */

public interface CoreApi {
    @GET("api/")
    Observable<GetPeopleResponse> getPeople();
}
