package com.example.vladok.testtask.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vladok.testtask.R;
import com.example.vladok.testtask.adapter.RecyclerViewAdapter;
import com.example.vladok.testtask.domain.dto.Post;
import com.example.vladok.testtask.service.AsyncUserServiceImpl;
import com.example.vladok.testtask.service.OnUserListener;
import com.example.vladok.testtask.util.ApplicationContext;
import com.example.vladok.testtask.util.CustomGridLayoutManager;
import com.example.vladok.testtask.util.RecyclerItemClickListener;

import java.util.List;


public class PageFragment extends Fragment {


    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private static List<Post> sPostList;
    private RecyclerView rvPosts;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private int mPageNumber;
    private AsyncUserServiceImpl asyncUserService;


    public static PageFragment newInstance(int page, List<Post> posts) {
        sPostList = posts;
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, null);
        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
        rvPosts.setHasFixedSize(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter(sPostList, mPageNumber);

        mLayoutManager = new CustomGridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false, false);
        rvPosts.setNestedScrollingEnabled(true);
        rvPosts.setLayoutManager(mLayoutManager);
        rvPosts.setAdapter(mRecyclerViewAdapter);


        rvPosts.addOnItemTouchListener(
                new RecyclerItemClickListener(ApplicationContext.getContext(), rvPosts, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        int postId = getPostIdFromPosts(position);
                        executeUserService(getContactIdByPostId(postId), postId);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        throw new UnsupportedOperationException("This operation is not supported!");
                    }


                })
        );

        return view;
    }

    private int getPostIdFromPosts(int positionAtPage) {
        return mPageNumber * 6 + positionAtPage + 1;
    }

    private int getContactIdByPostId(int position) {
        return sPostList.get(position).getUserId();
    }

    private void executeUserService(int contactId, int postId) {
        asyncUserService = new AsyncUserServiceImpl((OnUserListener) getActivity());
        asyncUserService.execute(contactId, postId);
    }


}




