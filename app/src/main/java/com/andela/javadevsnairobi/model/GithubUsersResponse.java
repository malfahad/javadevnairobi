package com.andela.javadevsnairobi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUsersResponse {

    @SerializedName("items")
    List<GithubUser> githubUsers;

    public List<GithubUser> getGithubUsers() {
        return githubUsers;
    }
}
