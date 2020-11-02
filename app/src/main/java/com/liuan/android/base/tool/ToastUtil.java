package com.liuan.android.base.tool;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liuan.android.base.BaseApplication;
import com.liuan.android.base.R;


/**
 * @author Peach Parrot
 * @date 2019年10月11日 20:19
 */
public class ToastUtil
{
    private static Toast toast;

    private static Toast getToast()
    {
        if (toast == null)
        {
            toast = new Toast(BaseApplication.getApplication());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        return toast;
    }

    public static void toast(String message)
    {
        toast(message, 0);
    }

    public static void toast(String message, int iconResId)
    {
        if (TextUtils.isEmpty(message))
        {
            return;
        }
        View viewToast;
        TextView tvContent;
        if (iconResId == 0)
        {
            viewToast = LayoutInflater.from(BaseApplication.getApplication())
                                      .inflate(R.layout.layout_toast_text, null);
            tvContent = viewToast.findViewById(R.id.tvToastTextContent);
        }
        else
        {
            viewToast = LayoutInflater.from(BaseApplication.getApplication())
                                      .inflate(R.layout.layout_toast_icon, null);
            tvContent = viewToast.findViewById(R.id.tvToastIconContent);
            ImageView imgIcon = viewToast.findViewById(R.id.imgToastIconImage);
            imgIcon.setImageResource(iconResId);
        }
        tvContent.setText(message);
        getToast().setView(viewToast);
        getToast().show();
    }
}
