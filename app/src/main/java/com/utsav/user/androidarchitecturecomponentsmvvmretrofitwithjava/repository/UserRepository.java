package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.ApiRequest;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit.RetrofitRequest;

import org.json.JSONObject;

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

    public LiveData<UserResponse> editUser(String url, User user) {
        final MutableLiveData<UserResponse> data = new MutableLiveData<>();
        apiRequest.editUser(url,user)
                .enqueue(new Callback<UserResponse>() {


                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



//                        if (response.body() != null) {
//                            data.setValue(response.body());
//
//
//
//                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public JSONObject createUser(String url, User user) {
        final JSONObject data = new JSONObject();
        apiRequest.createUser(url,user)
                .enqueue(new Callback<JSONObject>() {


                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        Log.d(TAG, "onResponse response:: " + response);



//                        if (response.body() != null) {
//                            data.setValue(response.body());
//
//
//
//                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {

                    }
                });
        return data;
    }
}
