package com.liuan.android.base.pagingData;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuan.android.base.activity.BaseToolbarActivity;
import com.liuan.android.base.bean.PagingData;

import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年10月15日 15:23
 */
public abstract class PagingDataBaseActivity<Presenter extends PagingDataBasePresenter> extends BaseToolbarActivity<Presenter, ViewBinding> implements PagingDataBaseContract.View
{
    protected PagingDataDelegate pagingDataDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        pagingDataDelegate = new PagingDataDelegate();
        super.onCreate(savedInstanceState);
        pagingDataDelegate.setPresenter(presenter);
    }

    @Override
    protected abstract Presenter createPresenter();

    protected abstract BaseQuickAdapter createAdapter();

    @Override
    protected View getContentView()
    {
        return pagingDataDelegate.createPagingDataLayout(this, createAdapter());
    }

    @Override
    public <Data extends PagingData> void onLoadPagingDataSuccess(Data data)
    {
        pagingDataDelegate.onLoadPagingDataSuccess(data);
    }
}