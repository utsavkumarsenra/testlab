package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public UserRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<UserResponse> getUsers() {
        final MutableLiveData<UserResponse> data = new MutableLiveData<>();
        apiRequest.getUsers()
                .enqueue(new Callback<UserResponse>() {


                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "users total result:: " + response.body().getUsers().size());

                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
