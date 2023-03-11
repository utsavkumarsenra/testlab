package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.adapter.userAdapter;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private userAdapter adapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        getUsers();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new userAdapter(MainActivity.this, userArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    /**
     * get movies articles from news api
     *
     * @param @null
     */
    private void getUsers() {
        userViewModel.getUserResponseLiveData().observe(this, userRespone -> {
            if (userRespone != null) {

                progress_circular_movie_article.setVisibility(View.GONE);
                List<User> users = userRespone.getUsers();
                userArrayList.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
