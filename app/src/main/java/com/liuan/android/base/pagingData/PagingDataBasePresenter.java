package com.liuan.android.base.pagingData;

import com.liuan.android.base.bean.BaseDataEntry;
import com.liuan.android.base.bean.PagingData;
import com.liuan.android.base.mvp.BasePresenter;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Peach Parrot
 * @date 2020年10月15日 16:01
 */

public abstract class PagingDataBasePresenter<View extends PagingDataBaseContract.View, Data extends PagingData> extends BasePresenter<View> implements PagingDataBaseContract.Presenter<Data>
{
    public int pageNumber = 0;

    protected PagingDataBasePresenter(View view)
    {
        super(view);
    }

    @Override
    public void loadPagingData()
    {
        getLoadPagingDataCall(pageNumber).enqueue(new Callback<BaseDataEntry<Data>>()
        {
            @Override
            public void onResponse(@NonNull Call<BaseDataEntry<Data>> call,
                                   @NonNull Response<BaseDataEntry<Data>> response)
            {
                if (viewNotNull())
                {
                    view.onLoadPagingDataSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseDataEntry<Data>> call, @NonNull Throwable t)
            {

            }
        });
    }
}