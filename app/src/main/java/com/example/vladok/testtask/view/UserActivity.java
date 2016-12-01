package com.example.vladok.testtask.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.vladok.testtask.R;
import com.example.vladok.testtask.domain.viewmodel.UserView;

public class UserActivity extends FragmentActivity {
    int mPosition;
    UserView mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mPosition = getIntent().getIntExtra("position", 0);
        mUserView = (UserView) getIntent().getSerializableExtra("UserView");
        showUserDetails(mPosition);
    }

    public void showUserDetails(int position) {
        UserFragment userFragment = (UserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.cont);

        if (userFragment == null || userFragment.getPosition() != position) {
            userFragment = UserFragment.newInstance(position, mUserView);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cont, userFragment).commit();

        }
    }
}
