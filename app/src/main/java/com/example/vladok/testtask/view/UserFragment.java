package com.example.vladok.testtask.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladok.testtask.R;
import com.example.vladok.testtask.domain.viewmodel.UserView;

public class UserFragment extends Fragment {


    public static UserFragment newInstance(int position, UserView userView) {
        UserFragment details = new UserFragment();
        Bundle args = new Bundle();

        args.putInt("position", position);
        args.putInt("contactId", userView.getUserId());
        args.putString("name", userView.getName());
        args.putString("userName", userView.getUsername());
        args.putString("email", userView.getEmail());
        args.putString("web", userView.getWebSite());
        args.putString("phone", userView.getPhone());
        args.putString("city", userView.getCity());

        details.setArguments(args);

        return details;
    }


    int getPosition() {
        return getArguments().getInt("position", 0);
    }

    UserView getUser() {
        UserView userView = new UserView();
        userView.setUserId(getArguments().getInt("contactId", 0));
        userView.setName(getArguments().getString("name"));
        userView.setUsername(getArguments().getString("userName"));
        userView.setEmail(getArguments().getString("email"));
        userView.setWebSite(getArguments().getString("web"));
        userView.setPhone(getArguments().getString("phone"));
        userView.setCity(getArguments().getString("city"));

        return userView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView tvPostId = (TextView) view.findViewById(R.id.tvPostId);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        TextView tvWeb = (TextView) view.findViewById(R.id.tvWeb);
        TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
        TextView tvCity = (TextView) view.findViewById(R.id.tvCity);

        UserView userView = getUser();
        tvPostId.setText(String.valueOf(getPosition()));
        tvName.setText(userView.getName());
        tvUserName.setText(userView.getUsername());
        tvEmail.setText(userView.getEmail());
        tvWeb.setText(userView.getWebSite());
        tvPhone.setText(userView.getPhone());
        tvCity.setText(userView.getCity());

        return view;
    }


}
