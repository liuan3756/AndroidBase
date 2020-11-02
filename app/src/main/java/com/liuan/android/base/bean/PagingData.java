package com.liuan.android.base.bean;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class PagingData<Item>
{
    public static final int START = 0;
    public static final int LIMIT = 10;

    private MetaBean meta;
    private List<Item> list;

    private boolean isLoadMore()
    {
        return meta != null && meta.page > START;
    }

    private boolean isNoMore()
    {
        return meta == null || meta.page == meta.total_page;
    }

    public void setMeta(MetaBean meta)
    {
        this.meta = meta;
    }

    public MetaBean getMeta()
    {
        return this.meta == null ? new MetaBean() : this.meta;
    }

    public void setList(List<Item> list)
    {
        this.list = list;
    }

    public List<Item> getList()
    {
        return this.list == null ? new ArrayList<Item>() : list;
    }

    public static <T> void coordinate(SmartRefreshLayout smartRefreshLayout,
                                      BaseQuickAdapter<T, ? extends BaseViewHolder> baseQuickAdapter,
                                      PagingData<T> pagingData)
    {
        if (pagingData == null)
        {
            smartRefreshLayout.finishLoadMore();
            return;
        }
        if (baseQuickAdapter != null)
        {
            if (!pagingData.isLoadMore())
            {
                smartRefreshLayout.finishRefresh();
                baseQuickAdapter.setNewData(pagingData.getList());
            }
            else
            {
                baseQuickAdapter.addData(pagingData.getList());
            }
        }
        if (smartRefreshLayout != null)
        {
            if (pagingData.isNoMore())
            {
                smartRefreshLayout.finishLoadMoreWithNoMoreData();
            }
            else
            {
                smartRefreshLayout.finishLoadMore();
            }
        }
    }

    public static class MetaBean
    {
        int total_page;
        //        int total;
        //        int offset;
        int page;
        //        int limit;
    }
}
