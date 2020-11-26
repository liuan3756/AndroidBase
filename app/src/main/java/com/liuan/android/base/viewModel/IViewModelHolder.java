package com.liuan.android.base.viewModel;

/**
 * @author Peach Parrot
 * @date 2020年11月26日 15:08
 */
public interface IViewModelHolder
{
    void setIViewModelOwners(IViewModelOwners iViewModel);

    void onViewModelLoaded();
} 