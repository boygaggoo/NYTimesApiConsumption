package com.example.zeeshankhalid.nytimesapiconsumption.controllers.retrofit;

import com.example.zeeshankhalid.nytimesapiconsumption.models.NYTimesResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by khalidz on 1/30/2018.
 */

public interface WebServices {
    @GET("{type}/{section}/{json_type}")
    Call<NYTimesResponseModel> getNews(@Path("type") String type, @Path("section") String section, @Path("json_type") String jsonType, @Query("api-key") String apiKey);
}
