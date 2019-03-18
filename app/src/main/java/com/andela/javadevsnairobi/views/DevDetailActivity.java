package com.andela.javadevsnairobi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class DevDetailActivity extends AppCompatActivity implements GithubUserView {

    TextView username;
    TextView name;
    TextView githubUrl;
    TextView bio;
    ImageView avatar;
    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.dev_username);
        name = findViewById(R.id.dev_name);
        githubUrl = findViewById(R.id.dev_github_link);
        bio = findViewById(R.id.dev_bio);
        avatar = findViewById(R.id.dev_avatar);

        presenter = new GithubPresenter(this);
        presenter.getUser("malfahad");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showUser(GithubUser githubUser) {

        username.setText(githubUser.getUsername());
        name.setText(githubUser.getName());
        bio.setText(githubUser.getBio());
        githubUrl.setText(githubUser.getHtmlUrl());
        Picasso.get().load(githubUser.getAvatarUrl())
                .transform(new CropCircleTransformation()).into(avatar);
    }
}
