package com.test.marketpulse;

import com.test.marketpulse.model.Scan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://mp-android-challenge.herokuapp.com/";

    @GET("data")
    Call<List<Scan>> getScans();
}