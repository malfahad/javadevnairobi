package com.andela.javadevsnairobi.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {

    @SerializedName("login")
    String username;
    @SerializedName("avatar_url")
    String avatarUrl;
    @SerializedName("bio")
    String bio;
    @SerializedName("html_url")
    String htmlUrl;
    @SerializedName("name")
    String name;


    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }


}
