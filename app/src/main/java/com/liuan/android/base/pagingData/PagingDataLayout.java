package com.liuan.android.base.pagingData;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author Peach Parrot
 * @date 2020年10月15日 14:05
 */
public class PagingDataLayout extends SmartRefreshLayout
{
    private RecyclerView recyclerView;

    private BaseQuickAdapter baseQuickAdapter;

    public PagingDataLayout(Context context)
    {
        super(context);
        init(context);
    }

    public PagingDataLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                         ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                          ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public RecyclerView getRecyclerView()
    {
        return recyclerView;
    }

    public void setAdapter(BaseQuickAdapter baseQuickAdapter)
    {
        this.baseQuickAdapter = baseQuickAdapter;
        this.baseQuickAdapter.bindToRecyclerView(recyclerView);
    }
}