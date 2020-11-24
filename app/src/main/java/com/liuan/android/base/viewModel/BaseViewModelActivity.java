package com.liuan.android.base.viewModel;

import android.os.Bundle;

import com.liuan.android.base.activity.BaseFunctionsActivity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2020年11月23日 17:47
 */
public abstract class BaseViewModelActivity<VB extends ViewBinding> extends BaseFunctionsActivity<VB> implements ViewModelDelegate.ViewModelInterface
{
    public static final String VM_ACTIVITY = "VMActivity";
    protected final ViewModelDelegate viewModelDelegate = new ViewModelDelegate(VM_ACTIVITY);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModelDelegate.addViewModelInterface(VM_ACTIVITY, this);
    }

    @Override
    public ViewModelStoreOwner getViewModelStoreOwner()
    {
        return this;
    }

    @Override
    public LifecycleOwner getLifecycleOwner()
    {
        return this;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        viewModelDelegate.clear();
    }
}