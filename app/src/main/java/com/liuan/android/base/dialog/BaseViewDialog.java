package com.liuan.android.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.liuan.android.base.widget.ViewBindingCreator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 * @author Peach Parrot
 * @date 2020年10月29日 15:43
 */
public class BaseViewDialog<VB extends ViewBinding> extends Dialog
{
    protected VB vBinding;

    public BaseViewDialog(@NonNull Context context)
    {
        super(context);
        createViewBinding();

    }

    public BaseViewDialog(@NonNull Context context, int themeResId)
    {
        super(context, themeResId);
        createViewBinding();
    }

    public BaseViewDialog(@NonNull Context context, boolean cancelable,
                          @Nullable OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
        createViewBinding();
    }

    private void createViewBinding()
    {
        vBinding = ViewBindingCreator.createViewBinding(getClass(),
                                                        LayoutInflater.from(getContext()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(vBinding.getRoot());
    }
}