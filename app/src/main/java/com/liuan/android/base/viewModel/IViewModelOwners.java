package com.liuan.android.base.viewModel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * @author Peach Parrot
 * @date 2020年11月26日 14:43
 */
public interface IViewModelOwners
{
    ViewModelStoreOwner getActivityViewModelStoreOwner();

    ViewModelStoreOwner getFragmentViewModelStoreOwner();

    LifecycleOwner getActivityLifecycleOwner();

    LifecycleOwner getFragmentLifecycleOwner();
} 