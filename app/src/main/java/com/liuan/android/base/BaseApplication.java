package com.liuan.android.base;

import android.app.Application;

import com.liuan.android.base.activity.BaseActivityFunction;
import com.liuan.android.base.activity.BaseFunctionsActivity;

import androidx.annotation.NonNull;
import timber.log.Timber;

/**
 * @author Peach Parrot
 * @date 2020年09月02日 17:06
 */
public class BaseApplication extends Application
{
    protected static BaseApplication instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportingTree());
    }

    public static BaseApplication getApplication()
    {
        return instance;
    }

    protected final void addBaseActivityFunction(Class<? extends BaseActivityFunction> fClass)
    {
        BaseFunctionsActivity.functionsClasses.add(fClass);
    }

    private static class CrashReportingTree extends Timber.Tree
    {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t)
        {

        }
    }
}
