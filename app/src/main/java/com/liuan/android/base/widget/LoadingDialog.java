package com.liuan.android.base.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;

import com.liuan.android.base.R;
import com.liuan.android.base.databinding.DialogLoadingBinding;
import com.liuan.android.base.dialog.BaseViewDialog;


/**
 * @author Peach Parrot
 * @date 2019年10月18日 16:01
 */
public class LoadingDialog extends BaseViewDialog<DialogLoadingBinding>
{

    public LoadingDialog(Context context)
    {
        super(context, R.style.LoadingDialogStyle);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContent("加载中");
    }

    public void setContent(String content)
    {
        vBinding.tvDialogLoadingContent.setText(content);
    }

    @Override
    public void show()
    {
        super.show();
        Window window = getWindow();

        if (window != null)
        {
            window.setGravity(Gravity.CENTER);
        }
    }
}
