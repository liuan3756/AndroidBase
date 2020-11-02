package com.liuan.android.base.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import timber.log.Timber;

/**
 * @author Peach Parrot
 * @date 2020年09月07日 10:09
 */
public abstract class BaseFunctionsActivity<VB extends ViewBinding> extends BaseViewActivity<VB>
{
    public static ArrayList<Class<? extends BaseActivityFunction>> functionsClasses = new ArrayList<>();

    private LinkedHashMap<String, BaseActivityFunction> functions = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        for (Class<? extends BaseActivityFunction> functionClass : functionsClasses)
        {
            try
            {
                functions.put(functionClass.getName(), functionClass.newInstance());
            }
            catch (IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }

        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityCreated(this, savedInstanceState);
        }
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivitySaveInstanceState(this, outState);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityStarted(this);
        }
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityRestarted(this);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityResumed(this);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityPaused(this);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        for (BaseActivityFunction baseActivityFunction : functions.values())
        {
            baseActivityFunction.onActivityStopped(this);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Iterator<BaseActivityFunction> iterator = functions.values()
                                                           .iterator();
        while (iterator.hasNext())
        {
            BaseActivityFunction baseActivityFunction = iterator.next();
            baseActivityFunction.onActivityDestroyed(this);
            baseActivityFunction = null;
            iterator.remove();
        }
        functions = null;
    }

    @Override
    public void onBackPressed()
    {
        boolean canBackPressed = true;
        for (BaseActivityFunction value : functions.values())
        {
            if (!value.onBeforeBackPressed(this))
            {
                canBackPressed = false;
            }
        }
        if (canBackPressed)
        {
            super.onBackPressed();
        }
    }

    @Override
    public void finish()
    {
        super.finish();
        for (BaseActivityFunction value : functions.values())
        {
            value.onFinish(this);
        }
    }

    public BaseActivityFunction getFunction(Class<? extends BaseActivityFunction> fClass)
    {
        return functions.get(fClass.getName());
    }
}
