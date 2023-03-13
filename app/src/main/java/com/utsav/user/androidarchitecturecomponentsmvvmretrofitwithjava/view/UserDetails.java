package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.UserDetailsFragment;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.UserDetailsViewModel;

public class UserDetails extends AppCompatActivity {

    UserDetailsViewModel userViewModel;
    User user;
    ImageView image;
    private ProgressBar progress_circular_movie_article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        image  = (ImageView) findViewById(R.id.profileImage);

        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);



        try {
            getSupportActionBar().setTitle("My Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //getting data from first page
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        user = (User) bundle.getParcelable("user");

        Glide.with(this)
                .load(user.getUrlToImage())
                .into(image);
        //using fragments
        FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
        UserDetailsFragment fragment = (UserDetailsFragment)fm.findFragmentById(R.id.fragment_container_view);
        fragment.getUser(user);




        // View Model
        userViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);




    }

    public void editUser(User user)
    {   //editing user from fragment
        progress_circular_movie_article.setVisibility(View.VISIBLE);
        userViewModel.editUser("users/"+user.getId(),user);
        Toast.makeText(this, "User Edited", Toast.LENGTH_SHORT).show();
        progress_circular_movie_article.setVisibility(View.GONE);

    }




}