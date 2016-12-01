package com.example.vladok.testtask.service;


import android.os.AsyncTask;

import com.example.vladok.testtask.domain.dto.User;
import com.example.vladok.testtask.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsyncUserServiceImpl extends AsyncTask<Integer, Integer, Void> {
     OnUserListener mOnUserListener;


    public AsyncUserServiceImpl(OnUserListener onUserListener) {
        mOnUserListener = onUserListener;
    }

    @Override
    protected Void doInBackground(final Integer... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserService userService = retrofit.create(UserService.class);
        //params[0] = contactId
        Call<User> call = userService.getUser(params[0]);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //params[1] = postId
                mOnUserListener.onResponseFromUserService(response.body(), params[1]);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return null;
    }
}
