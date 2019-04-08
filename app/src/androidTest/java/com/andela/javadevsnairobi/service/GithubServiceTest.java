package com.andela.javadevsnairobi.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Test;

import static org.junit.Assert.assertThat;

public class GithubServiceTest {

    GithubService service = new GithubService();

    @Test
    public void getApi_returnsRetrofitGithubAPiInstance() {
        service.getAPI();
        assertThat(service.getAPI(), instanceOf(GithubAPI.class));
    }
}
