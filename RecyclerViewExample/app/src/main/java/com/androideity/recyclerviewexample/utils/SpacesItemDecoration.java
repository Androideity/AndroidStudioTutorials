package com.androideity.recyclerviewexample.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Suleiman on 26-07-2015.
 * http://blog.grafixartist.com/pinterest-masonry-layout-staggered-grid/
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = space;
    }
}
