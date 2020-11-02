package com.liuan.android.base.pagingData;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuan.android.base.bean.PagingData;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;

/**
 * @author Peach Parrot
 * @date 2020年10月15日 16:29
 */
public class PagingDataDelegate implements OnRefreshLoadMoreListener,
                                           PagingDataBaseContract.View
{
    private PagingDataLayout pagingDataLayout;

    private PagingDataBasePresenter presenter;

    public PagingDataDelegate()
    {
    }

    public void setPresenter(PagingDataBasePresenter presenter)
    {
        this.presenter = presenter;
    }

    public PagingDataLayout createPagingDataLayout(Context context,
                                                   BaseQuickAdapter baseQuickAdapter)
    {
        if (pagingDataLayout == null)
        {
            pagingDataLayout = new PagingDataLayout(context);
            pagingDataLayout.setAdapter(baseQuickAdapter);
        }
        return pagingDataLayout;
    }

    public PagingDataLayout getPagingDataLayout()
    {
        return pagingDataLayout;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout)
    {
        presenter.pageNumber++;
        presenter.loadPagingData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout)
    {
        presenter.pageNumber = 0;
        presenter.loadPagingData();
    }

    @Override
    public <Data extends PagingData> void onLoadPagingDataSuccess(Data data)
    {
    }

    @Override
    public void showLoading()
    {

    }

    @Override
    public void hideLoading()
    {

    }

    @Override
    public void message(String message)
    {

    }
}