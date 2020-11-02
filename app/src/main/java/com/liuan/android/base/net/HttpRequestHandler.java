package com.liuan.android.base.net;

import com.liuan.android.base.net.service.BaseService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * @author Peach Parrot
 * @date 2020年10月19日 17:48
 */
public class HttpRequestHandler
{
    private static HttpRequestHandler instance;
    private final Retrofit retrofit;
    private static final int TIME_OUT = 10;
    private final HashMap<Class, BaseService> services = new HashMap<>();

    private HttpRequestHandler()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
        {
            @Override
            public void log(String message)
            {
                Timber.d(message);
            }
        }).setLevel(
                HttpLoggingInterceptor.Level.BODY));

        retrofit = new Retrofit.Builder().client(builder.build())
                                         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .baseUrl("http://www.baidu.com/")
                                         .build();
    }

    public static HttpRequestHandler getInstance()
    {
        if (instance == null)
        {
            synchronized (HttpRequestHandler.class)
            {
                if (instance == null)
                {
                    instance = new HttpRequestHandler();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <S extends BaseService> S getRequest(Class<S> rClass)
    {
        S service;
        if (!services.containsKey(rClass))
        {
            service = retrofit.create(rClass);
            services.put(rClass, service);
            return service;
        }
        else
        {
            service = (S) services.get(rClass);
        }
        return service;
    }
} 