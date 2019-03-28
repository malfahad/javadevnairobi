package com.andela.javadevsnairobi.views;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.presenter.GithubPresenter;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class DevDetailActivity extends AppCompatActivity implements GithubUserView, View.OnClickListener {

    TextView username;
    TextView name;
    TextView githubUrl;
    TextView bio;
    ImageView avatar;
    Button shareButton;
    GithubPresenter presenter;
    ConstraintLayout profileView;

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
        profileView = findViewById(R.id.rectangle);

        shareButton = findViewById(R.id.dev_share_btn);
        shareButton.setOnClickListener(this);

        presenter = new GithubPresenter(this);
        String username = getIntent().getExtras().getString("username");
        presenter.getUser(username);

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
        if (githubUser.getBio() != null)
            if (githubUser.getBio().contains("\n"))
                resizeProfileViewHeight(profileView, githubUser.getBio());
    }

    @Override
    public void onClick(View v) {
        String user_name = (String) username.getText();
        String url = (String) githubUrl.getText();
        String message = String.format("Checkout this awesome developer @%s, %s", user_name, url);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Share using "));
    }

    public void resizeProfileViewHeight(ConstraintLayout profileView, String bio) {
        int lines = bio.split("\n").length;
        int extraHeight = lines * 15;
        ViewGroup.LayoutParams params = profileView.getLayoutParams();
        params.height += extraHeight;
        profileView.setLayoutParams(params);
    }
}
