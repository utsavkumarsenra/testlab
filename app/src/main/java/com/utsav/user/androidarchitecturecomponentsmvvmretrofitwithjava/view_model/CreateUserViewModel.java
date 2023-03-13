package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model;





import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.repository.UserRepository;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.response.UserResponse;

import org.json.JSONObject;


public class CreateUserViewModel extends AndroidViewModel {

    private User thisUser;

    private UserRepository userRepository;
    private LiveData<UserResponse> userResponseCreateLiveData;

    public CreateUserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository();

    }




    public JSONObject createUser(String url, User user) {
        JSONObject val = userRepository.createUser(url,user);
        return val;
    }
}

