package com.example.vladok.testtask.service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.vladok.testtask.util.Constants;
import com.example.vladok.testtask.view.MainActivity;
import com.example.vladok.testtask.domain.dto.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class executes post load in background thread , then sends loaded data to MainActivity.
 */
public class AsyncPostServiceImpl extends AsyncTask<String, Integer, Void> {

    MainActivity activity;

    public void link(MainActivity activity){
        this.activity = activity;
    }

    /**
     * This method call's when MainActivity recreates and protects against memory leaks.
     */
    public void unlink(){
        this.activity = null;
    }

    @Override
    protected Void doInBackground(String... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PostService postService = retrofit.create(PostService.class);
        Call<List<Post>> call = postService.getPosts();
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                activity.onResponseFromPostService(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
        return null;
    }
}