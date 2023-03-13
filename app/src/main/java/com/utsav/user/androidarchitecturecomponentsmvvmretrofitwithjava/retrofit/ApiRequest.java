package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.retrofit;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiRequest {

    @GET("users")
    Call<UserResponse> getUsers();

    @POST()
    Call<UserResponse> editUser(@Url String url, @Body User user);

    @POST()
    Call<JSONObject> createUser(@Url String url, @Body User user);

}
