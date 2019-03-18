package com.andela.javadevsnairobi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.adapter.GithubAdapter;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.presenter.GithubPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GithubAllUsersView {

    RecyclerView devsRecyclerView;
    GithubPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devsRecyclerView = findViewById(R.id.devs_recycler_view);
        devsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new GithubPresenter(this);
        presenter.getAllUsers();
    }

    @Override
    public void showAllUsers(List<GithubUser> githubUsers) {
        GithubAdapter adapter = new GithubAdapter(githubUsers);
        devsRecyclerView.setAdapter(adapter);
    }
}
