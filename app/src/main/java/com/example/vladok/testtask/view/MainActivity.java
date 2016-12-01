package com.example.vladok.testtask.view;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.vladok.testtask.R;
import com.example.vladok.testtask.adapter.PageFragmentAdapter;
import com.example.vladok.testtask.domain.DtoFactory;
import com.example.vladok.testtask.domain.dto.Post;
import com.example.vladok.testtask.domain.dto.User;
import com.example.vladok.testtask.domain.viewmodel.UserView;
import com.example.vladok.testtask.service.AsyncPostServiceImpl;
import com.example.vladok.testtask.service.OnUserListener;
import com.example.vladok.testtask.util.AsyncLogWriter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnUserListener {

    //Viwes
    private Button btnSaveLog;
    private ViewPager viewPagerPosts;
    private CirclePageIndicator circlePageIndicatorPosts;

    //Members
    private AsyncLogWriter mAsyncLogWriter;
    private AsyncPostServiceImpl mAsyncPostService;
    private PageFragmentAdapter mPageFragmentAdapter;
    private List<Post> mPostList;
    private Object[] mSavedDataFromActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeViews();

        //Obtains a reference to array objects that was saved when the activity recreated.
        mSavedDataFromActivity = (Object[]) getLastCustomNonConfigurationInstance();
        //If activity first created
        if (mSavedDataFromActivity == null) {
            executePostService();
            //Else recover all activity's data
        } else {
            recoverDataAfterRecreateActivity();
        }

    }

    /**
     * Initializes views.
     */
    public void initializeViews() {
        setContentView(R.layout.activity_main);
        btnSaveLog = (Button) findViewById(R.id.btnSaveLogCat);

        viewPagerPosts = (ViewPager) findViewById(R.id.viewPager);
        circlePageIndicatorPosts = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicatorPosts.setPageColor(ContextCompat.getColor(this, R.color.red));
        circlePageIndicatorPosts.setFillColor(Color.BLACK);
        circlePageIndicatorPosts.setStrokeColor(ContextCompat.getColor(this, R.color.yellow));

        btnSaveLog.setOnClickListener(this);
    }

    /**
     * Executes posts load.
     */
    public void executePostService() {
        mAsyncPostService = new AsyncPostServiceImpl();
        mAsyncPostService.execute();
        //Given's MainActivity's link to AsyncPostServiceImpl
        mAsyncPostService.link(this);
    }

    /**
     * This method call's when AsyncPostService API  get's response.
     *
     * @param postList
     */
    public void onResponseFromPostService(List<Post> postList) {
        this.mPostList = postList;
        mPageFragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager(), postList);
        viewPagerPosts.setAdapter(mPageFragmentAdapter);
        circlePageIndicatorPosts.setViewPager(viewPagerPosts);
    }


    /**
     * Saved the AsyncPostService, mPostList, and postAdapter - objects, when the activity is recreated.
     *
     * @return
     */
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        super.onRetainCustomNonConfigurationInstance();
        mAsyncPostService.unlink();
        mSavedDataFromActivity = new Object[2];
        mSavedDataFromActivity[0] = mAsyncPostService;
        mSavedDataFromActivity[1] = mPostList;
        return mSavedDataFromActivity;
    }

    /**
     * Recover's all objects that saved  in onRetainCustomConfigurationInstanceMethod ,
     * when the activity is recreated.
     */
    public void recoverDataAfterRecreateActivity() {
        //recover
        mAsyncPostService = (AsyncPostServiceImpl) mSavedDataFromActivity[0];
        mPostList = (ArrayList<Post>) mSavedDataFromActivity[1];
        //initialize
        mPageFragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager(), mPostList);
        viewPagerPosts.setAdapter(mPageFragmentAdapter);
        circlePageIndicatorPosts.setViewPager(viewPagerPosts);
        mAsyncPostService.link(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveLogCat:
                mAsyncLogWriter = new AsyncLogWriter();
                mAsyncLogWriter.execute();
                break;
        }
    }

    @Override
    public void onResponseFromUserService(User user, int mPosition) {
        UserView userView = DtoFactory.userDtoTOUserView(user, mPosition);
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("position", mPosition);
        intent.putExtra("UserView", userView);
        startActivity(intent);
    }


}

