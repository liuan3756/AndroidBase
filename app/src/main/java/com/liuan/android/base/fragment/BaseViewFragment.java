package com.liuan.android.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuan.android.base.R;
import com.liuan.android.base.activity.BaseViewActivity;
import com.liuan.android.base.tool.TimeFormatter;
import com.liuan.android.base.widget.ViewBindingCreator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年07月09日 20:39
 */
public abstract class BaseViewFragment<VB extends ViewBinding> extends Fragment
{
    private View rootView;

    protected VB vBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        vBinding = ViewBindingCreator.createViewBinding(getClass(), inflater);
        rootView = generateContentView(vBinding == null ? getContentView() : vBinding.getRoot());
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBackgroundTint));
        onCreateView(rootView);
        return rootView;
    }

    protected abstract void onCreateView(View rootView);

    protected View getContentView()
    {
        return null;
    }

    protected View generateContentView(View contentView)
    {
        return contentView;
    }

    protected void setStatusBarTextDark(boolean isStatusBarTextDark)
    {
        Activity activity = getActivity();
        if (activity instanceof BaseViewActivity)
        {
            ((BaseViewActivity) activity).setStatusBarTextDark(isStatusBarTextDark);
        }
    }

    protected void setBackgroundColor(int color)
    {
        rootView.setBackgroundColor(color);
    }

    protected void showLoadingDialog()
    {
        Activity activity = getActivity();
        if (activity instanceof BaseViewActivity)
        {
            ((BaseViewActivity) activity).showLoadingDialog();
        }
    }

    protected void hideLoadingDialog()
    {
        Activity activity = getActivity();
        if (activity instanceof BaseViewActivity)
        {
            ((BaseViewActivity) activity).hideLoadingDialog();
        }
    }
}
