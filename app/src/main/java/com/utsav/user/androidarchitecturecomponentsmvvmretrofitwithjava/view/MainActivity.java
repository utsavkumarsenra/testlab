package com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.adapter.userAdapter;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;

import com.utsav.user.androidarchitecturecomponentsmvvmretrofitwithjava.view_model.UserViewModel;

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

    Button createUser;


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
        try {
            getSupportActionBar().setTitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        createUser = (Button) findViewById(R.id.createuser);

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

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateUser.class));
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addUserMenu) {
            startActivity(new Intent(MainActivity.this,CreateUser.class));
            // Do something
            return true;
        }
//        if (id == R.id.action_send) {
//
//            // Do something
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
