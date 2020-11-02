package com.liuan.android.base.mvp;

import android.os.Bundle;

import com.liuan.android.base.fragment.BaseViewFragment;
import com.liuan.android.base.tool.ToastUtil;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年10月12日 14:01
 */
public abstract class BaseMVPFragment<Presenter extends BasePresenter, VB extends ViewBinding> extends BaseViewFragment<VB> implements BaseContract.View
{
    protected Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
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
    public void onDestroy()
    {
        super.onDestroy();
        if (presenter != null)
        {
            presenter.detachView();
        }
    }
}
