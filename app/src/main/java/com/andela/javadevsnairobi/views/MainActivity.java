package com.andela.javadevsnairobi.views;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.adapter.GithubAdapter;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.presenter.GithubPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GithubAllUsersView {

    RecyclerView devsRecyclerView;
    GithubPresenter presenter;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;
    List<GithubUser> mGithubUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devsRecyclerView = findViewById(R.id.devs_recycler_view);
        devsRecyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_count)));
        presenter = new GithubPresenter(this);
        presenter.getAllUsers();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllUsers();
            }
        });
    }

    @Override
    public void showAllUsers(List<GithubUser> githubUsers) {
        mGithubUsers = githubUsers;
        GithubAdapter adapter = new GithubAdapter(githubUsers);
        devsRecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
        progressDialog.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh_item:
                swipeRefreshLayout.setRefreshing(true);
                presenter.getAllUsers();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Type listType = new TypeToken<List<GithubUser>>() {
        }.getType();
        String serailizedGithubUsers = new Gson().toJson(mGithubUsers, listType);
        SharedPreferences githubUsers = getSharedPreferences("GithubUsers", MODE_PRIVATE);
        SharedPreferences.Editor editor = githubUsers.edit();
        editor.putString("github users", serailizedGithubUsers);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Type listType = new TypeToken<List<GithubUser>>() {
        }.getType();
        SharedPreferences githubUsers = getSharedPreferences("GithubUsers", MODE_PRIVATE);
        String serializedGithubUsers = githubUsers.getString("github users", null);
        List<GithubUser> githubUserList = new Gson().fromJson(serializedGithubUsers, listType);
        if (githubUserList != null) showAllUsers(githubUserList);
    }
}
