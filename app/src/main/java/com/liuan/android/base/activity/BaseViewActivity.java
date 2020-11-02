package com.liuan.android.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.liuan.android.base.R;
import com.liuan.android.base.widget.LoadingDialog;
import com.liuan.android.base.widget.ViewBindingCreator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;


/**
 * @author Peach Parrot
 * @date 2019年07月05日 15:59
 */
public abstract class BaseViewActivity<VB extends ViewBinding> extends AppCompatActivity
{
    private boolean isStatusBarTextDark = false;
    private View rootView;

    private LoadingDialog loadingDialog;

    protected VB vBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(getScreenOrientation());//竖屏

        vBinding = ViewBindingCreator.createViewBinding(getClass(), getLayoutInflater());

        rootView = generateContentView(vBinding == null ? getContentView() : vBinding.getRoot());

        setContentView(rootView);

        setStatusBarTextDark(true);
        if (rootView.getBackground() == null)
        {
            setBackgroundColor(getResources().getColor(R.color.colorBackgroundTint));
        }
        loadingDialog = new LoadingDialog(this);
    }

    protected View getContentView()
    {
        return null;
    }

    protected View generateContentView(View contentView)
    {
        return contentView;
    }

    protected int getScreenOrientation()
    {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected void setBackgroundColor(int color)
    {
        rootView.setBackgroundColor(color);
    }

    protected void setBackgroundDrawableRes(int drawableResId)
    {
        rootView.setBackgroundResource(drawableResId);
    }

    public void setStatusBarTextDark(boolean isStatusBarTextDark)
    {
        if (this.isStatusBarTextDark != isStatusBarTextDark)
        {
            this.isStatusBarTextDark = isStatusBarTextDark;
            ImmersionBar.with(this)
                        .statusBarDarkFont(isStatusBarTextDark)
                        .keyboardEnable(true)
                        .init();
        }
    }

    public void showLoadingDialog()
    {
        if (!loadingDialog.isShowing())
        {
            loadingDialog.show();
        }
    }

    public void hideLoadingDialog()
    {
        if (loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed()
    {
        if (loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        hideLoadingDialog();
        loadingDialog = null;
    }
}
