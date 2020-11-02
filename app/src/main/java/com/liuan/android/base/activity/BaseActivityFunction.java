package com.liuan.android.base.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * @author Peach Parrot
 * @date 2020年09月02日 17:40
 */
public abstract class BaseActivityFunction implements Application.ActivityLifecycleCallbacks
{
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState)
    {
    }

    @Override
    public void onActivityStarted(Activity activity)
    {
    }

    public void onActivityRestarted(Activity activity)
    {
    }

    @Override
    public void onActivityResumed(Activity activity)
    {
    }

    @Override
    public void onActivityPaused(Activity activity)
    {
    }

    @Override
    public void onActivityStopped(Activity activity)
    {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState)
    {
    }

    @Override
    public void onActivityDestroyed(Activity activity)
    {
    }

    public void onFinish(Activity activity)
    {
    }

    public boolean onBeforeBackPressed(Activity activity)
    {
        return true;
    }
}