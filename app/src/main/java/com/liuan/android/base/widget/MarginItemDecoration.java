package com.liuan.android.base.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.liuan.android.base.tool.DensityUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration
{
    private final int horizontalMargin;
    private final int verticalMargin;

    public MarginItemDecoration(Context context, int horizontalMarginDp, int verticalMarginDp)
    {
        this.horizontalMargin = DensityUtil.dp2px(context, horizontalMarginDp);
        this.verticalMargin = DensityUtil.dp2px(context, verticalMarginDp);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager)
        {
            if (layoutManager.getPosition(view) == 0)
            {
                outRect.top = verticalMargin;
            }
            outRect.bottom = verticalMargin;
            outRect.left = horizontalMargin;
            outRect.right = horizontalMargin;
        }
    }
}
