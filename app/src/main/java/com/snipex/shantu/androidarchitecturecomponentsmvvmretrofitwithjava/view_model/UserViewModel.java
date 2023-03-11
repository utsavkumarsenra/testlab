package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.repository.UserRepository;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;



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
