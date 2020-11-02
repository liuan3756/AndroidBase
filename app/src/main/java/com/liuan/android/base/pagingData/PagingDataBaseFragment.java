package com.liuan.android.base.pagingData;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuan.android.base.bean.PagingData;
import com.liuan.android.base.mvp.BaseMVPFragment;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年10月15日 14:04
 */
public abstract class PagingDataBaseFragment

        <Presenter extends PagingDataBasePresenter> extends BaseMVPFragment<Presenter, ViewBinding> implements PagingDataBaseContract.View
{
    protected PagingDataDelegate pagingDataDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
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
        return pagingDataDelegate.createPagingDataLayout(getContext(), createAdapter());
    }

    @Override
    public <Data extends PagingData> void onLoadPagingDataSuccess(Data data)
    {
        pagingDataDelegate.onLoadPagingDataSuccess(data);
    }
}