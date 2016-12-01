package com.example.vladok.testtask.service;

import com.example.vladok.testtask.domain.dto.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("/posts")
    Call<List<Post>> getPosts();


}
