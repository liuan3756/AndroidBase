package com.liuan.android.base.mvp;

import com.liuan.android.base.viewModel.IViewModelHolder;

/**
 * @author Peach Parrot
 * @date 2019年10月12日 11:37
 */
public interface BaseContract
{
    interface View
    {
        void showLoading();

        void hideLoading();

        void message(String message);
    }

    interface Presenter extends IViewModelHolder
    {
        boolean viewNotNull();

        void detachView();
    }
}
