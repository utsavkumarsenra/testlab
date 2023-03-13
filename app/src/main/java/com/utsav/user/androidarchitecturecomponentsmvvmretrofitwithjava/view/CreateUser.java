package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.CreateUserViewModel;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.UserDetailsViewModel;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;

public class CreateUser extends AppCompatActivity {
    private static final String EMAIL = "email";
    LoginButton loginButton;

    CallbackManager callbackManager;

    CreateUserViewModel userViewModel;

    Button login;

    EditText fullName;
    private ProgressBar progress_circular_movie_article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);



        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));

        callbackManager = CallbackManager.Factory.create();

        login = (Button) findViewById(R.id.Login);

        fullName = (EditText) findViewById(R.id.FullName);



        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("LoginResult","true");
                        Profile.getCurrentProfile().getName();

                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("LoginResult","true");
                        Profile.getCurrentProfile();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d("LoginResult","true");
                        Profile.getCurrentProfile();
                    }
                });
        // View Model
        userViewModel = ViewModelProviders.of(this).get(CreateUserViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                String fullNameText = fullName.getText().toString();
                user.setFirst_name(fullNameText.substring(0, fullNameText.indexOf(' ')));
                user.setLast_name(fullNameText.substring(fullNameText.indexOf(' ')+1));
                editUser(user);
            }
        });
        //rmoving action bar
        Objects.requireNonNull(getSupportActionBar()).hide();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        //if we want to get fb information
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    public void editUser(User user)
    {
        //create user
        progress_circular_movie_article.setVisibility(View.VISIBLE);
        JSONObject val = userViewModel.createUser("users",user);
        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
        progress_circular_movie_article.setVisibility(View.GONE);
        startActivity(new Intent(CreateUser.this,MainActivity.class));
    }
}