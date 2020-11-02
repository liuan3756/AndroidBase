package com.liuan.android.base.net.callback;

import com.liuan.android.base.bean.BaseDataEntry;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Peach Parrot
 * @date 2020年10月21日 15:32
 */
public abstract class CommonCallback<T extends BaseDataEntry> implements Callback<T>
{
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response)
    {
        if (response.isSuccessful())
        {
            T result = response.body();
            if (result != null)
            {
                onResponse(result);
            }
            else
            {
                onError(0, "error");
            }
        }
        else
        {
            onError(0, "error");
        }
        onComplete();
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t)
    {
        onError(0, t.getMessage());
        onComplete();
    }

    public abstract void onResponse(T t);

    public void onError(int errorCode, String errorMessage)
    {
    }

    public void onComplete()
    {
    }
}