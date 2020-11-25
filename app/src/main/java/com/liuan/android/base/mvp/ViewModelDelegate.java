package com.liuan.android.base.mvp;

import java.util.HashMap;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * @author Peach Parrot
 * @date 2020年11月23日 17:04
 */
public class ViewModelDelegate
{
    private HashMap<Integer, ViewModelInterface> hashMap;
    private int defaultLevel;
    public static final int LEVEL_ACTIVITY = 0;
    public static final int LEVEL_FRAGMENT = 1;

    public void addViewModelInterface(int level, ViewModelInterface viewModelInterface)
    {
        if (hashMap == null)
        {
            hashMap = new HashMap<>();
            defaultLevel = level;
        }
        hashMap.put(level, viewModelInterface);
    }

    public <VM extends ViewModel> VM createViewModel(Class<VM> vmClass)
    {
        return createViewModel(defaultLevel, vmClass);
    }

    public <VM extends ViewModel> VM createViewModel(int level, Class<VM> vmClass)
    {
        return createViewModel(level, new ViewModelProvider.NewInstanceFactory(), vmClass);
    }

    public <VM extends ViewModel> VM createViewModel(int level, ViewModelProvider.Factory factory,
                                                     Class<VM> vmClass)
    {
        ViewModelInterface viewModelInterface = hashMap.get(level);
        if (viewModelInterface != null)
        {
            return new ViewModelProvider(viewModelInterface.getViewModelStoreOwner(), factory).get(
                    vmClass);
        }
        return null;
    }

    private ViewModelInterface getDefaultViewModelInterface()
    {
        return hashMap.get(defaultLevel);
    }

    public <T> void observeLiveData(LiveData<T> liveData, Observer<T> observer)
    {
        liveData.observe(getDefaultViewModelInterface().getLifecycleOwner(), observer);
    }

    public void clear()
    {
        hashMap.clear();
        hashMap = null;
    }

    public interface ViewModelInterface
    {
        ViewModelStoreOwner getViewModelStoreOwner();

        LifecycleOwner getLifecycleOwner();
    }
} 