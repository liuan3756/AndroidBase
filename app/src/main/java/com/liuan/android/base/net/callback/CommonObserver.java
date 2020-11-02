package com.liuan.android.base.net.callback;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Peach Parrot
 * @date 2020年10月22日 16:19
 */
public abstract class CommonObserver<T> implements Observer<T>
{

    @Override
    public void onSubscribe(@NonNull Disposable d)
    {

    }

    @Override
    public void onNext(@NonNull T t)
    {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e)
    {

    }

    @Override
    public void onComplete()
    {

    }

    public abstract void onSuccess(T t);

    public void onError(int errorCode, String errorMessage)
    {
    }
}