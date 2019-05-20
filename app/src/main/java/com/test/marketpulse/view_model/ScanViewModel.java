package com.test.marketpulse.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.test.marketpulse.Api;
import com.test.marketpulse.model.Scan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScanViewModel extends ViewModel {

    private MutableLiveData<List<Scan>> scanList;

    public LiveData<List<Scan>> getScans() {
        //if the list is null 
        if (scanList == null) {
            scanList = new MutableLiveData<List<Scan>>();
            //we will load it asynchronously from server in this method
            loadScans();
        }

        //finally we will return the list
        return scanList;
    }


    //This method is using Retrofit to get the JSON data from URL 
    private void loadScans() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Scan>> call = api.getScans();


        call.enqueue(new Callback<List<Scan>>() {
            @Override
            public void onResponse(Call<List<Scan>> call, Response<List<Scan>> response) {
                //finally we are setting the list to our MutableLiveData
                System.out.printf("trying");
                scanList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Scan>> call, Throwable t) {
                System.out.printf("trying00");
            }
        });
    }

}
