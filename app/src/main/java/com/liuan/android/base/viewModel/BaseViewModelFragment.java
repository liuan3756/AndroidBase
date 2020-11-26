package com.liuan.android.base.viewModel;

import com.liuan.android.base.fragment.BaseViewFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年11月26日 15:15
 */
public abstract class BaseViewModelFragment<VB extends ViewBinding> extends BaseViewFragment<VB>
{
    protected final void setViewModelHolder(@NonNull IViewModelHolder iViewModelHolder)
    {
        iViewModelHolder.setIViewModelOwners(new IViewModelOwners()
        {
            @Override
            public ViewModelStoreOwner getActivityViewModelStoreOwner()
            {
                return requireActivity();
            }

            @Override
            public ViewModelStoreOwner getFragmentViewModelStoreOwner()
            {
                return BaseViewModelFragment.this;
            }

            @Override
            public LifecycleOwner getActivityLifecycleOwner()
            {
                return requireActivity();
            }

            @Override
            public LifecycleOwner getFragmentLifecycleOwner()
            {
                return BaseViewModelFragment.this;
            }
        });
    }
} 