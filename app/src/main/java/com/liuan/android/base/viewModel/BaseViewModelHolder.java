package com.liuan.android.base.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author Peach Parrot
 * @date 2020年11月26日 15:04
 */
public class BaseViewModelHolder implements IViewModelHolder
{
    private IViewModelOwners iViewModelOwners;

    private ViewModelProvider activityViewModelProvider;
    private ViewModelProvider fragmentViewModelProvider;

    @Override
    public final void setIViewModelOwners(IViewModelOwners iViewModel)
    {
        this.iViewModelOwners = iViewModel;
        onViewModelLoaded();
    }

    @Override
    public void onViewModelLoaded()
    {

    }

    protected final <VM extends ViewModel> VM getActivityViewModel(Class<VM> vmClass)
    {
        if (activityViewModelProvider == null)
        {
            activityViewModelProvider = new ViewModelProvider(
                    iViewModelOwners.getActivityViewModelStoreOwner(),
                    new ViewModelProvider.NewInstanceFactory());
        }
        return activityViewModelProvider.get(vmClass);
    }

    protected final <VM extends ViewModel> VM getFragmentViewModel(Class<VM> vmClass)
    {
        if (fragmentViewModelProvider == null)
        {
            fragmentViewModelProvider = new ViewModelProvider(
                    iViewModelOwners.getFragmentViewModelStoreOwner(),
                    new ViewModelProvider.NewInstanceFactory());
        }
        return fragmentViewModelProvider.get(vmClass);
    }


    protected final <T> void observeActivityLiveData(LiveData<T> liveData, Observer<T> observer)
    {
        liveData.observe(iViewModelOwners.getActivityLifecycleOwner(), observer);
    }

    protected final <T> void observeFragmentLiveData(LiveData<T> liveData, Observer<T> observer)
    {
        liveData.observe(iViewModelOwners.getFragmentLifecycleOwner(), observer);
    }
}