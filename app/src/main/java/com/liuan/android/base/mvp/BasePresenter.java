package com.liuan.android.base.mvp;

/**
 * @author Peach Parrot
 * @date 2019年10月12日 11:38
 */
public abstract class BasePresenter<View extends BaseContract.View> implements BaseContract.Presenter
{
    protected View view;
    private ViewModelDelegate viewModelDelegate;

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
        getViewModelDelegate().clear();
    }

    @Override
    public ViewModelDelegate getViewModelDelegate()
    {
        if (viewModelDelegate == null)
        {
            viewModelDelegate = new ViewModelDelegate();
        }
        return viewModelDelegate;
    }
}
