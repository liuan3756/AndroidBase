package com.liuan.android.base;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Environment;

import com.liuan.android.base.activity.BaseActivityFunction;
import com.liuan.android.base.activity.BaseFunctionsActivity;
import com.liuan.android.base.tool.BaseConstant;
import com.liuan.android.base.tool.CrashHandler;

import java.io.File;

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

        Timber.plant(getDebuggable() ? new Timber.DebugTree() : new CrashReportingTree());
        BaseConstant.FILE_DIRECTORY = Environment.getExternalStorageDirectory()
                                                 .getPath() + File.separator + getPackageName() + File.separator;
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    public static BaseApplication getApplication()
    {
        return instance;
    }

    private boolean getDebuggable()
    {
        boolean debuggable = false;
        try
        {
            ApplicationInfo info = getApplicationInfo();
            debuggable = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
        catch (Exception ignored)
        {

        }
        return debuggable;
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
