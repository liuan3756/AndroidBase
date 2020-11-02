package com.liuan.android.base.mvp;

/**
 * @author Peach Parrot
 * @date 2020年09月28日 17:07
 */
public abstract class BaseModel
{
    public BaseModel()
    {

    }

    public interface ModelCallBack<Data>
    {
        void onSuccess(Data data);

        void onFail();
    }

    public interface ModelCallBack2<Data1, Data2>
    {
        void onSuccess(Data1 data1, Data2 data2);

        void onFail();
    }

    public interface ModelCallBack3<Data1, Data2, Data3>
    {
        void onSuccess(Data1 data1, Data2 data2, Data3 data3);

        void onFail();
    }

    public interface ModelCallBack4<Data1, Data2, Data3, Data4>
    {
        void onSuccess(Data1 data1, Data2 data2, Data3 data3, Data4 data4);

        void onFail();
    }
}