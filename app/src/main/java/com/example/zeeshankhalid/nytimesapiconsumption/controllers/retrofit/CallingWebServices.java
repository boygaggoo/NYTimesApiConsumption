package com.example.zeeshankhalid.nytimesapiconsumption.controllers.retrofit;

import com.example.zeeshankhalid.nytimesapiconsumption.Constants;
import com.example.zeeshankhalid.nytimesapiconsumption.models.NYTimesResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZeeZee on 11/16/2017.
 */

public class CallingWebServices {
    Retrofit retrofit;
    WebServices webServices;
    Gson gson;

    public static final String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/";

    private static CallingWebServices callingWebServices;

    public static CallingWebServices getInstance() {
        if (callingWebServices == null) {
            callingWebServices = new CallingWebServices();
        }
        return callingWebServices;
    }

    private CallingWebServices() {
        initRetrofit();
    }

    private void initRetrofit() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);

        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient.build()).build();
        webServices = retrofit.create(WebServices.class);
    }

    public void getNews(String type, String section, String jsonType, final ServiceResponse serviceResponse) {
        Call<NYTimesResponseModel> call = webServices.getNews(type, section, jsonType, Constants.API_KEY);
        call.enqueue(new Callback<NYTimesResponseModel>() {
            @Override
            public void onResponse(Call<NYTimesResponseModel> call, Response<NYTimesResponseModel> response) {
                if (response.code() == 200 || response.code() == 201) {
                    serviceResponse.onSuccess(response.body());
                } else
                    serviceResponse.onFail("Failed to get results");
            }

            @Override
            public void onFailure(Call<NYTimesResponseModel> call, Throwable t) {
                serviceResponse.onError("Server not responding! Try again...");
            }
        });
    }
}
