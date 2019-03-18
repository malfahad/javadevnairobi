package com.andela.javadevsnairobi.presenter;

import android.util.Log;

import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.model.GithubUsersResponse;
import com.andela.javadevsnairobi.service.GithubService;
import com.andela.javadevsnairobi.views.GithubAllUsersView;
import com.andela.javadevsnairobi.views.GithubUserView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {

    GithubUserView githubUserView;
    GithubAllUsersView githubAllUsersView;
    GithubService githubService;

    public GithubPresenter(GithubUserView view) {
        this.githubUserView = view;

        if (githubService == null) {
            githubService = new GithubService();
        }
    }

    public GithubPresenter(GithubAllUsersView view) {
        this.githubAllUsersView = view;

        if (githubService == null) {
            githubService = new GithubService();
        }
    }

    public void getAllUsers() {
        githubService.getAPI()
                .getAllUsers()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                        GithubUsersResponse body = response.body();
                        if (body != null) {
                            List<GithubUser> githubUsers = body.getGithubUsers();
                            githubAllUsersView.showAllUsers(githubUsers);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("An error occurred ");
                        } catch (InterruptedException e) {
                            Log.e("getAllUsers", e.getMessage());
                        }
                    }

                });
    }

    public void getUser(String username) {
        githubService.getAPI()
                .getUser(username)
                .enqueue(new Callback<GithubUser>() {
                    @Override
                    public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                        GithubUser user = response.body();
                        githubUserView.showUser(user);
                    }

                    @Override
                    public void onFailure(Call<GithubUser> call, Throwable t) {
                        try {
                            throw new InterruptedException("An error occurred ");
                        } catch (InterruptedException e) {
                            Log.e("getUser", e.getMessage());
                        }
                    }
                });
    }
}