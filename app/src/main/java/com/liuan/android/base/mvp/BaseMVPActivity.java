package com.liuan.android.base.mvp;

import android.os.Bundle;

import com.liuan.android.base.activity.BaseFunctionsActivity;
import com.liuan.android.base.tool.ToastUtil;

import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年10月11日 18:24
 */
public abstract class BaseMVPActivity<Presenter extends BaseContract.Presenter,VB extends ViewBinding> extends BaseFunctionsActivity<VB> implements BaseContract.View
{
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (presenter != null)
        {
            presenter.detachView();
        }
    }

    protected Presenter createPresenter()
    {
        return null;
    }
}
