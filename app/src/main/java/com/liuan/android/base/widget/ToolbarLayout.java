package com.liuan.android.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuan.android.base.R;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;

public class ToolbarLayout extends RelativeLayout
{
    private static int statusBarHeight;
    public static int textColor = Color.parseColor("#333333");
    public static int textSizeTitle = 18;
    public static int textSizeButton = 16;
    public static int buttonPaddingDp = 14;
    public RelativeLayout rlToolbarLeft;
    public RelativeLayout rlToolbarTitle;
    public RelativeLayout rlToolbarRight;

    private OnToolbarClickListener onToolbarClickListener;

    public ToolbarLayout(Context context)
    {
        super(context);
        init();
    }

    public ToolbarLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public ToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        if (!isInEditMode())
        {
            //            inflate(getContext(), R.layout.merge_toolbar_layout, this);

            //            ButterKnife.bind(this);
            setViewPaddingTopStatusBarHeight(this);
            setLayoutParams(
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(getToolbarHeightIncludeStatusBar(getContext()),
                                                    MeasureSpec.EXACTLY));
    }

    private RelativeLayout createRelativeLayout(int gravity)
    {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        int rule = RelativeLayout.CENTER_HORIZONTAL;
        switch (gravity)
        {
            case Gravity.START:
                rule = RelativeLayout.ALIGN_PARENT_START;
                break;
            case Gravity.END:
                rule = RelativeLayout.ALIGN_PARENT_END;
                break;
        }
        layoutParams.addRule(rule);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.setPadding(dp2px(getContext(), buttonPaddingDp), 0,
                                  dp2px(getContext(), buttonPaddingDp), 0);
        addView(relativeLayout);
        return relativeLayout;
    }

    private void checkLeftButton()
    {
        if (rlToolbarLeft == null)
        {
            rlToolbarLeft = createRelativeLayout(Gravity.START);
            rlToolbarLeft.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onToolbarClickListener != null)
                    {
                        onToolbarClickListener.onToolbarLeftClick();
                    }
                }
            });
        }
    }

    private void checkTitle()
    {
        if (rlToolbarTitle == null)
        {
            rlToolbarTitle = createRelativeLayout(Gravity.CENTER_HORIZONTAL);
            rlToolbarTitle.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onToolbarClickListener != null)
                    {
                        onToolbarClickListener.onToolbarTitleClick();
                    }
                }
            });
        }
    }

    private void checkRightButton()
    {
        if (rlToolbarRight == null)
        {
            rlToolbarRight = createRelativeLayout(Gravity.END);
            rlToolbarRight.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onToolbarClickListener != null)
                    {
                        onToolbarClickListener.onToolbarRightClick();
                    }
                }
            });
        }
    }

    private void setButtonImageResId(RelativeLayout button, int imageResId)
    {
        if (imageResId > 0)
        {
            ImageView imageView = new ImageView(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            imageView.setImageResource(imageResId);
            button.addView(imageView, layoutParams);
        }
    }

    private void setButtonText(RelativeLayout button, CharSequence text, int textSize)
    {
        if (!TextUtils.isEmpty(text))
        {
            TextView textView = new TextView(getContext());
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setText(text);
            textView.setLines(1);
            textView.setTextSize(textSize);
            textView.setTextColor(textColor);
            button.addView(textView, layoutParams);
        }
    }

    public void setLeftButtonImageResId(int imageResId)
    {
        checkLeftButton();
        rlToolbarLeft.removeAllViews();
        setButtonImageResId(rlToolbarLeft, imageResId);
    }

    public void setLeftButtonText(CharSequence text)
    {
        checkLeftButton();
        rlToolbarLeft.removeAllViews();
        setButtonText(rlToolbarLeft, text, textSizeButton);
    }

    public void setLeftButtonText(int textResId)
    {
        setLeftButtonText(getResources().getText(textResId));
    }

    public void setTitleText(CharSequence title)
    {
        checkTitle();
        rlToolbarTitle.removeAllViews();
        setButtonText(rlToolbarTitle, title, textSizeTitle);
    }

    public void setTitleText(int titleResId)
    {
        setTitleText(getContext().getResources()
                                 .getText(titleResId));
    }

    public void setRightButtonImageResId(int imageResId)
    {
        checkRightButton();
        rlToolbarRight.removeAllViews();
        setButtonImageResId(rlToolbarRight, imageResId);
    }

    public void setRightButtonText(CharSequence text)
    {
        checkRightButton();
        rlToolbarRight.removeAllViews();
        setButtonText(rlToolbarRight, text, textSizeButton);
    }

    public void setRightButtonText(int textResId)
    {
        setRightButtonText(getResources().getText(textResId));
    }

    public void setOnToolbarClickListener(@NonNull OnToolbarClickListener onToolbarClickListener)
    {
        this.onToolbarClickListener = onToolbarClickListener;
    }

    public static int getToolbarHeightIncludeStatusBar(Context context)
    {
        return getToolbarHeight(context) + getStatusBarHeight(context);
    }

    public static int getToolbarHeight(Context context)
    {
        return (int) context.getResources()
                            .getDimension(R.dimen.toolbarHeight);
    }

    public static void setViewPaddingTopStatusBarHeight(View view)
    {
        view.setPadding(view.getPaddingLeft(), getStatusBarHeight(view.getContext()),
                        view.getPaddingEnd(), view.getPaddingBottom());
    }

    @SuppressLint("PrivateApi")
    public static int getStatusBarHeight(Context context)
    {
        if (statusBarHeight == 0)
        {
            Class<?> c;
            Object obj;
            Field field;
            int x;
            try
            {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj)
                                          .toString());
                statusBarHeight = context.getResources()
                                         .getDimensionPixelSize(x);
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    private static int dp2px(Context context, int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                                               context.getResources()
                                                      .getDisplayMetrics());
    }

    public interface OnToolbarClickListener
    {
        void onToolbarLeftClick();

        void onToolbarTitleClick();

        void onToolbarRightClick();
    }

    public static class ToolbarWidget
    {
        public View customView;
        public int iconResId;
        public String content;
        public int color;
        public float size;
    }
}
