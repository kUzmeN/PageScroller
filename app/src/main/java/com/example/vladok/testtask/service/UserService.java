package com.example.vladok.testtask.service;

import com.example.vladok.testtask.domain.dto.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface UserService {
    @GET("/users/{id}")
    Call<User> getUser(@Path("id") int Id);
}
