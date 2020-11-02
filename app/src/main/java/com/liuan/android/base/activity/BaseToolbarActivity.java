package com.liuan.android.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.liuan.android.base.R;
import com.liuan.android.base.mvp.BaseContract;
import com.liuan.android.base.mvp.BaseMVPActivity;
import com.liuan.android.base.widget.ToolbarLayout;

import androidx.viewbinding.ViewBinding;

public abstract class BaseToolbarActivity<Presenter extends BaseContract.Presenter, VB extends ViewBinding> extends BaseMVPActivity<Presenter, VB> implements ToolbarLayout.OnToolbarClickListener
{
    protected ToolbarLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStatusBarTextDark(false);
    }

    @Override
    protected View generateContentView(View contentView)
    {
        toolbar = new ToolbarLayout(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setLeftButtonImageResId(R.drawable.vector_back_light);
        return generateToolbarContentView(toolbar, contentView, getToolbarCoverContent(), this);
    }

    protected boolean getToolbarCoverContent()
    {
        return false;
    }

    @Override
    public void onToolbarLeftClick()
    {
        onBackPressed();
    }

    @Override
    public void onToolbarTitleClick()
    {

    }

    @Override
    public void onToolbarRightClick()
    {

    }

    public static View generateToolbarContentView(ToolbarLayout toolbar, View contentView,
                                                  boolean isToolbarCoverContent,
                                                  ToolbarLayout.OnToolbarClickListener onToolbarClickListener)
    {
        Context context = toolbar.getContext();
        RelativeLayout rootLayout = new RelativeLayout(context);
        toolbar.setId(View.generateViewId());
        toolbar.setOnToolbarClickListener(onToolbarClickListener);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        if (!isToolbarCoverContent)
        {
            layoutParams.addRule(RelativeLayout.BELOW, toolbar.getId());
        }
        if (contentView != null)
        {
            rootLayout.addView(contentView, layoutParams);
        }
        rootLayout.addView(toolbar);
        return rootLayout;
    }
}
