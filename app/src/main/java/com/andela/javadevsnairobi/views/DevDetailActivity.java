package com.andela.javadevsnairobi.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
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
import com.google.gson.Gson;
import com.andela.javadevsnairobi.util.NetworkUtil;
import com.andela.javadevsnairobi.util.NetworkUtilContract;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class DevDetailActivity extends AppCompatActivity implements GithubUserView, View.OnClickListener, NetworkUtilContract {

    TextView username;
    TextView name;
    TextView githubUrl;
    TextView bio;
    ImageView avatar;
    Button shareButton;
    GithubPresenter presenter;
    ConstraintLayout profileView;
    ProgressDialog progressDialog;
    GithubUser mGithubUser;

    Snackbar snackbar;
    String devUsername;

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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        NetworkUtil.checkInternetConnection(this);

        presenter = new GithubPresenter(this);
        devUsername = getIntent().getExtras().getString("username");
        presenter.getUser(devUsername);

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
        mGithubUser = githubUser;
        username.setText(githubUser.getUsername());
        name.setText(githubUser.getName());
        bio.setText(githubUser.getBio());
        githubUrl.setText(githubUser.getHtmlUrl());
        Picasso.get().load(githubUser.getAvatarUrl())
                .transform(new CropCircleTransformation()).into(avatar);
        if (githubUser.getBio() != null)
            if (githubUser.getBio().contains("\n"))
                resizeProfileViewHeight(profileView, githubUser.getBio());
        progressDialog.hide();
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

    @Override
    protected void onPause() {
        super.onPause();
        String serailizedGithubUser = new Gson().toJson(mGithubUser);
        SharedPreferences githubUser = getSharedPreferences("GithubUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = githubUser.edit();
        editor.putString("githubUser", serailizedGithubUser);
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences githubUser = getSharedPreferences("GithubUser", MODE_PRIVATE);
        String serializedGithubUser = githubUser.getString("githubUser", null);
        GithubUser mGithubUser = new Gson().fromJson(serializedGithubUser, GithubUser.class);
        if (mGithubUser != null) showUser(mGithubUser);
    }

    public void resizeProfileViewHeight(ConstraintLayout profileView, String bio) {
        int lines = bio.split("\n").length;
        int extraHeight = lines * 15;
        ViewGroup.LayoutParams params = profileView.getLayoutParams();
        params.height += extraHeight;
        profileView.setLayoutParams(params);
        progressDialog.hide();
    }

    @Override
    public void onInternetUnavailable() {
        progressDialog.hide();
        snackbar = Snackbar.make(username, "Internet connection is unavailable", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                NetworkUtil.checkInternetConnection(DevDetailActivity.this);
            }
        });
        snackbar.show();
    }

    @Override
    public void onInternetRestored() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
                presenter.getUser(devUsername);
            }
        });
    }
}
