package com.liuan.android.base.widget;

import android.view.LayoutInflater;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.viewbinding.ViewBinding;
import timber.log.Timber;

/**
 * @author Peach Parrot
 * @date 2020年10月28日 13:50
 */
public class ViewBindingCreator
{
    @SuppressWarnings("unchecked")
    public static <VB extends ViewBinding> VB createViewBinding(Class targetClass,
                                                                LayoutInflater layoutInflater)
    {
        Type type = targetClass.getGenericSuperclass();

        if (type instanceof ParameterizedType)
        {
            try
            {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();

                for (Type type1 : types)
                {
                    if (type1.getTypeName()
                             .endsWith("Binding"))
                    {
                        Method method = ((Class<VB>) type1).getMethod("inflate",
                                                                      LayoutInflater.class);
                        return (VB) method.invoke(null, layoutInflater);
                    }
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } return null;
    }
} 