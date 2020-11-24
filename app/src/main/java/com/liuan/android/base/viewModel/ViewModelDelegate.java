package com.liuan.android.base.viewModel;

import com.liuan.android.base.mvp.BaseContract;

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
    private HashMap<String, ViewModelInterface> hashMap;
    private final String defaultName;

    public ViewModelDelegate(String defaultName)
    {
        this.defaultName = defaultName;
    }

    public void bindToPresenter(BaseContract.Presenter presenter)
    {
        if (presenter != null)
        {
            presenter.setViewModelDelegate(this);
        }
    }

    public void addViewModelInterface(String name, ViewModelInterface viewModelInterface)
    {
        if (hashMap == null)
        {
            hashMap = new HashMap<>();
        }
        hashMap.put(name, viewModelInterface);
    }

    public <VM extends ViewModel> VM createViewModel(Class<VM> vmClass)
    {
        return createViewModel(defaultName, vmClass);
    }

    public <VM extends ViewModel> VM createViewModel(String name, Class<VM> vmClass)
    {
        return createViewModel(name, new ViewModelProvider.NewInstanceFactory(), vmClass);
    }

    public <VM extends ViewModel> VM createViewModel(String name, ViewModelProvider.Factory factory,
                                                     Class<VM> vmClass)
    {
        ViewModelInterface viewModelInterface = hashMap.get(name);
        if (viewModelInterface != null)
        {
            return new ViewModelProvider(viewModelInterface.getViewModelStoreOwner(), factory).get(
                    vmClass);
        }
        return null;
    }

    private ViewModelInterface getDefaultViewModelInterface()
    {
        return hashMap.get(defaultName);
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