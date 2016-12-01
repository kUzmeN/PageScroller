package com.example.vladok.testtask.service;


import com.example.vladok.testtask.domain.dto.User;
import com.example.vladok.testtask.domain.viewmodel.UserView;

public interface OnUserListener {
    void onResponseFromUserService(User user , int position);
}
