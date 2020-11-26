package com.liuan.android.base.viewModel;

import com.liuan.android.base.activity.BaseFunctionsActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年11月26日 15:14
 */
public abstract class BaseViewModelActivity<VB extends ViewBinding> extends BaseFunctionsActivity<VB>
{
    protected final void setViewModelHolder(@NonNull IViewModelHolder iViewModelHolder)
    {
        iViewModelHolder.setIViewModelOwners(new IViewModelOwners()
        {
            @Override
            public ViewModelStoreOwner getActivityViewModelStoreOwner()
            {
                return BaseViewModelActivity.this;
            }

            @Override
            public ViewModelStoreOwner getFragmentViewModelStoreOwner()
            {
                return null;
            }

            @Override
            public LifecycleOwner getActivityLifecycleOwner()
            {
                return BaseViewModelActivity.this;
            }

            @Override
            public LifecycleOwner getFragmentLifecycleOwner()
            {
                return null;
            }
        });
    }
} 