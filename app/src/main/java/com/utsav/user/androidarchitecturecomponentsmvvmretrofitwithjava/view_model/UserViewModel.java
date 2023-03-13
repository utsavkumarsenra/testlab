package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.repository.UserRepository;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;



public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<UserResponse> userResponseLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository();
        this.userResponseLiveData = userRepository.getUsers();
    }

    public LiveData<UserResponse> getUserResponseLiveData() {
        return userResponseLiveData;
    }
}
