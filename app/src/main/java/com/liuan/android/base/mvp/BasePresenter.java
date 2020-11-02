package com.liuan.android.base.mvp;

/**
 * @author Peach Parrot
 * @date 2019年10月12日 11:38
 */
public abstract class BasePresenter<View extends BaseContract.View> implements BaseContract.Presenter
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

    protected <Model extends BaseModel> Model getModel(Class<Model> modelClass)
    {
        return ModelManager.getInstance()
                           .getModel(modelClass);
    }
}
