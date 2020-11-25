package com.liuan.android.base.mvp;

import android.os.Bundle;

import com.liuan.android.base.activity.BaseFunctionsActivity;
import com.liuan.android.base.tool.ToastUtil;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年10月11日 18:24
 */
public abstract class BaseMVPActivity<Presenter extends BaseContract.Presenter, VB extends ViewBinding> extends BaseFunctionsActivity<VB> implements BaseContract.View,
                                                                                                                                                     ViewModelDelegate.ViewModelInterface
{
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.getViewModelDelegate()
                 .addViewModelInterface(ViewModelDelegate.LEVEL_ACTIVITY, this);
    }

    @Override
    public void showLoading()
    {
        showLoadingDialog();
    }

    @Override
    public void hideLoading()
    {
        hideLoadingDialog();
    }

    @Override
    public void message(String message)
    {
        ToastUtil.toast(message);
    }

    protected Presenter createPresenter()
    {
        return null;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (presenter != null)
        {
            presenter.detachView();
        }
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
}
