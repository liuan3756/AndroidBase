package com.liuan.android.base.widget;

import android.widget.Checkable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Peach Parrot
 * @date 2019年08月16日 16:11
 */
public abstract class BaseSelectableAdapter<T extends Checkable, K extends BaseViewHolder> extends BaseQuickAdapter<T, K>
{
    private OnSelectChangedListener<T> onSelectChangedListener;
    private boolean isSingleSelected = true;

    public BaseSelectableAdapter(@LayoutRes int layoutResId, @Nullable List<T> data)
    {
        super(layoutResId, data);
    }

    public BaseSelectableAdapter(@Nullable List<T> data)
    {
        super(0, data);
    }

    public BaseSelectableAdapter(@LayoutRes int layoutResId)
    {
        super(layoutResId, null);
    }

    public void setOnSelectChangedListener(OnSelectChangedListener<T> onSelectChangedListener)
    {
        this.onSelectChangedListener = onSelectChangedListener;
    }

    public void setIsSingleSelected(boolean isSingleSelected)
    {
        this.isSingleSelected = isSingleSelected;
    }

    @Override
    public void bindToRecyclerView(RecyclerView recyclerView)
    {
        super.bindToRecyclerView(recyclerView);
        DefaultItemAnimator defaultItemAnimator = ((DefaultItemAnimator) recyclerView.getItemAnimator());
        if (defaultItemAnimator != null)
        {
            defaultItemAnimator.setSupportsChangeAnimations(false);
        }
    }

    @Override
    protected abstract void convert(K helper, T item);

    protected boolean isSelected(int position)
    {
        for (int i = 0; i < getData().size(); i++)
        {
            if (i == position)
            {
                return getData().get(position)
                                .isChecked();
            }
        }
        return false;
    }

    protected void toggle(int position)
    {
        if (position >= getData().size())
        {
            return;
        }
        HashSet<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < getData().size(); i++)
        {
            T item = getData().get(i);

            if (i == position)
            {
                item.toggle();
                indexSet.add(i);
            }
            else if (isSingleSelected && item.isChecked())
            {
                item.setChecked(false);
                indexSet.add(i);
            }
        }
        notifyItemsChange(indexSet);
    }

    public T getSelectedData()
    {
        return (getSelectedDataList() == null || getSelectedDataList().size() == 0) ? null : getSelectedDataList().get(
                0);
    }

    public List<T> getSelectedDataList()
    {
        List<T> selectedItems = new ArrayList<>();
        for (T datum : getData())
        {
            if (datum.isChecked())
            {
                selectedItems.add(datum);
            }
        }
        return selectedItems;
    }

    public void clearAllSelected()
    {
        HashSet<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < getData().size(); i++)
        {
            T item = getData().get(i);
            if (item.isChecked())
            {
                item.setChecked(false);
                indexSet.add(i);
            }
        }
        notifyItemsChange(indexSet);
    }

    private void notifyItemsChange(HashSet<Integer> indexSet)
    {
        for (Integer integer : indexSet)
        {
            notifyItemChanged(integer);
        }
        if (onSelectChangedListener != null)
        {
            onSelectChangedListener.onSelectChanged(getSelectedDataList());
        }
    }


    public interface OnSelectChangedListener<Item>
    {
        void onSelectChanged(List<Item> selectedItems);
    }
}
