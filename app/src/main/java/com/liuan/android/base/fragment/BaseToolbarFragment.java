package com.liuan.android.base.fragment;

import android.view.View;

import com.liuan.android.base.activity.BaseToolbarActivity;
import com.liuan.android.base.mvp.BaseMVPFragment;
import com.liuan.android.base.mvp.BasePresenter;
import com.liuan.android.base.widget.ToolbarLayout;

import androidx.viewbinding.ViewBinding;

public abstract class BaseToolbarFragment<Presenter extends BasePresenter, VB extends ViewBinding> extends BaseMVPFragment<Presenter, VB> implements ToolbarLayout.OnToolbarClickListener
{
    protected ToolbarLayout toolbar;

    @Override
    protected View generateContentView(View contentView)
    {
        toolbar = new ToolbarLayout(getContext());
        return BaseToolbarActivity.generateToolbarContentView(toolbar, contentView,
                                                              getToolbarCoverContent(), this);
    }

    protected boolean getToolbarCoverContent()
    {
        return false;
    }

    @Override
    public void onToolbarLeftClick()
    {

    }

    @Override
    public void onToolbarTitleClick()
    {

    }

    @Override
    public void onToolbarRightClick()
    {

    }
}
