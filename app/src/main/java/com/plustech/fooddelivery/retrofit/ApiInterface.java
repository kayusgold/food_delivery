package com.plustech.fooddelivery.retrofit;

import com.plustech.fooddelivery.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();

}
