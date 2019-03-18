package com.andela.javadevsnairobi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.presenter.GithubPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GithubAllUsersView {

    RecyclerView devsRecyclerView;
    Button launchBtn;
    GithubPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devsRecyclerView = findViewById(R.id.devs_recycler_view);
        devsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        launchBtn = findViewById(R.id.launch_btn);
        launchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DevDetailActivity.class);
                startActivity(intent);
            }
        });
        presenter = new GithubPresenter(this);
        presenter.getAllUsers();
    }

    @Override
    public void showAllUsers(List<GithubUser> githubUsers) {
        Log.d("Users Count", String.valueOf(githubUsers.size()));
    }

}
