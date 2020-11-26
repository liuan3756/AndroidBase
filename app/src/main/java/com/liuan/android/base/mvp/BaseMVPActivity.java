package com.liuan.android.base.mvp;

import android.os.Bundle;

import com.liuan.android.base.tool.ToastUtil;
import com.liuan.android.base.viewModel.BaseViewModelActivity;

import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年10月11日 18:24
 */
public abstract class BaseMVPActivity<Presenter extends BaseContract.Presenter, VB extends ViewBinding> extends BaseViewModelActivity<VB> implements BaseContract.View

{
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null)
        {
            setViewModelHolder(presenter);
        }
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
}
