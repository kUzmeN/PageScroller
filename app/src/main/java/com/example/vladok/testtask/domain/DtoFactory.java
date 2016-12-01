package com.example.vladok.testtask.domain;


import com.example.vladok.testtask.domain.dto.User;
import com.example.vladok.testtask.domain.viewmodel.UserView;

public class DtoFactory {

    public static UserView userDtoTOUserView(User user , int position){
        UserView viewModel = new UserView();
        viewModel.setUserId(user.getId());
        viewModel.setPostId(position);
        viewModel.setName(user.getName());
        viewModel.setUsername(user.getUsername());
        viewModel.setEmail(user.getEmail());
        viewModel.setWebSite(user.getWebsite());
        viewModel.setPhone(user.getPhone());
        viewModel.setCity(user.getAddress().getCity());

        return viewModel;
    }
}
