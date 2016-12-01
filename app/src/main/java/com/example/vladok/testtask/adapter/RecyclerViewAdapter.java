package com.example.vladok.testtask.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladok.testtask.R;
import com.example.vladok.testtask.util.StringUtil;
import com.example.vladok.testtask.domain.dto.Post;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Post> mPosts;
    private int pageNumber;

    public RecyclerViewAdapter(List<Post> postList, int pageNumber) {
        this.mPosts = postList;
        this.pageNumber = pageNumber;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_post_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int x;
        int size = mPosts.size();
        String title;
        x = position + pageNumber * 6;

        if (x < size) {
            holder.tvId.setText(String.valueOf(mPosts.get(position + pageNumber * 6).getId()));
            title = mPosts.get(position + pageNumber * 6).getTitle();
            title = StringUtil.cutStrToCountSpacesOrCountChars(title,4,34);
            holder.tvTitle.setText(title);

        } else {
            holder.tvId.setText("");
            holder.tvTitle.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvTitle;

        public ViewHolder(View v) {
            super(v);
            tvId = (TextView) v.findViewById(R.id.rvPostsItemId);
            tvTitle = (TextView) v.findViewById(R.id.rvPostsItemTitle);

        }
    }
}
