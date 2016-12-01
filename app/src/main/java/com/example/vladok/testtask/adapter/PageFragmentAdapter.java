package com.example.vladok.testtask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vladok.testtask.view.PageFragment;
import com.example.vladok.testtask.domain.dto.Post;

import java.util.List;


public class PageFragmentAdapter extends FragmentPagerAdapter {

    List<Post> postList;

    public PageFragmentAdapter(FragmentManager fm, List<Post> posts) {
        super(fm);
        postList = posts;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position, postList);
    }

    @Override
    public int getCount() {
        return postList.size() / 6 + 1;
    }
}