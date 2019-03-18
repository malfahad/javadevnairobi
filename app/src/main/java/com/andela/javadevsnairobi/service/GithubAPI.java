package com.andela.javadevsnairobi.service;

import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    @GET("/users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);

    @GET("/search/users?q=location:Nairobi+language:Java")
    Call<GithubUsersResponse> getAllUsers();
}
