package com.liuan.android.base.viewModel;

import android.os.Bundle;

import com.liuan.android.base.fragment.BaseViewFragment;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年11月23日 17:52
 */
public abstract class BaseViewModelFragment<VB extends ViewBinding> extends BaseViewFragment<VB> implements ViewModelDelegate.ViewModelInterface
{
    public static final String VM_FRAGMENT = "VMFragment";
    protected final ViewModelDelegate viewModelDelegate = new ViewModelDelegate(VM_FRAGMENT);

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModelDelegate.addViewModelInterface(BaseViewModelActivity.VM_ACTIVITY,
                                                new ViewModelDelegate.ViewModelInterface()
                                                {
                                                    @Override
                                                    public ViewModelStoreOwner getViewModelStoreOwner()
                                                    {
                                                        return requireActivity();
                                                    }

                                                    @Override
                                                    public LifecycleOwner getLifecycleOwner()
                                                    {
                                                        return requireActivity();
                                                    }
                                                });
        viewModelDelegate.addViewModelInterface(VM_FRAGMENT, this);
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
    public void onDestroy()
    {
        super.onDestroy();
        viewModelDelegate.clear();
    }
}