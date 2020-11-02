package com.liuan.android.base.pagingData;

import com.liuan.android.base.bean.BaseDataEntry;
import com.liuan.android.base.bean.PagingData;
import com.liuan.android.base.mvp.BaseContract;

import retrofit2.Call;

/**
 * @author Peach Parrot
 * @date 2020年10月15日 15:55
 */
public interface PagingDataBaseContract
{
    interface View extends BaseContract.View
    {
        <Data extends PagingData> void onLoadPagingDataSuccess(Data data);
    }

    interface Presenter<Data extends PagingData> extends BaseContract.Presenter
    {
        Call<BaseDataEntry<Data>> getLoadPagingDataCall(int pageNumber);

        void loadPagingData();
    }
}
