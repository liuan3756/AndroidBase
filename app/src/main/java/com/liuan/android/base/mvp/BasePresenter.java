package com.liuan.android.base.mvp;

import com.liuan.android.base.viewModel.BaseViewModelHolder;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

/**
 * @author Peach Parrot
 * @date 2019年10月12日 11:38
 */
public abstract class BasePresenter<View extends BaseContract.View> extends BaseViewModelHolder implements BaseContract.Presenter
{
    protected View view;

    protected BasePresenter(View view)
    {
        this.view = view;
    }

    @Override
    public boolean viewNotNull()
    {
        return this.view != null;
    }

    @Override
    public void detachView()
    {
        this.view = null;
    }

    protected <VM extends ViewModel> VM getViewModel(Class<VM> vmClass)
    {
        if (view instanceof Fragment)
        {
            return getFragmentViewModel(vmClass);
        }
        return getActivityViewModel(vmClass);
    }

    protected <T> void observeLiveData(LiveData<T> liveData, Observer<T> observer)
    {
        if (view instanceof Fragment)
        {
            observeFragmentLiveData(liveData, observer);
        }
        else
        {
            observeActivityLiveData(liveData, observer);
        }
    }
}
