package com.andela.javadevsnairobi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GithubUserTest {
    String testUsername = "username";
    String testBio = "Sample Bio";
    String testAvatarUrl = "http://github.io/u/abc.jpg";
    String testHtmlUrl = "http://github.io/u/user12";

    GithubUser testGithubUser = new GithubUser();

    @Test
    public void githubUser_hasCorrectUseranme() {
        testGithubUser.username = testUsername;
        assertEquals(testGithubUser.getUsername(),testUsername);
    }

    @Test
    public void githubUser_hasCorrectBio(){
        testGithubUser.bio = testBio;
        assertEquals(testGithubUser.getBio(),testBio);
    }

    @Test
    public void githubUser_returnsCorrectAvatarUrl () {
        testGithubUser.avatarUrl = testAvatarUrl;
        assertEquals(testGithubUser.getAvatarUrl(),testAvatarUrl);
    }

    @Test
    public  void githubUser_returnsCorrectHtmlUrl() {
        testGithubUser.htmlUrl = testHtmlUrl;
        assertEquals(testGithubUser.getHtmlUrl(),testHtmlUrl);
    }
}
