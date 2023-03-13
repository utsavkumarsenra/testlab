package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.repository.UserRepository;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;


public class UserDetailsViewModel extends AndroidViewModel {

    private User thisUser;

    private UserRepository userRepository;
    private LiveData<UserResponse> userResponseEditLiveData;

    public UserDetailsViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository();

    }

    public void editUser(String url,User user)
    {
        this.userResponseEditLiveData = userRepository.editUser(url,user);

    }

    public LiveData<UserResponse> getUserEditResponseLiveData() {
        return userResponseEditLiveData;
    }


}
