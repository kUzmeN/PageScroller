package com.example.vladok.testtask.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 *This class overrides behavior of CustomGridLayoutManager's scroll ,
 */
public class CustomGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout, boolean isScrollEnabled) {
        super(context, spanCount, orientation, reverseLayout);
        this.isScrollEnabled = isScrollEnabled;
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollVertically();
    }
}