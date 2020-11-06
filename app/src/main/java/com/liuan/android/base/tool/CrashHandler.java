package com.liuan.android.base.tool;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

import com.liuan.android.base.BaseApplication;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Peach Parrot
 * @date 2019年08月15日 16:31
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread thread, Throwable ex)
    {
        long currentTime = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.CHINA).format(
                new Date(currentTime));
        LogFileSaver.saveLogFile(BaseConstant.FILE_DIRECTORY + File.separator + "crash",
                                 "crash" + time + ".log", time, printWriter -> {
                    PackageManager pm = BaseApplication.getApplication()
                                                       .getPackageManager();
                    try
                    {
                        PackageInfo pi = pm.getPackageInfo(BaseApplication.getApplication()
                                                                          .getPackageName(),
                                                           PackageManager.GET_ACTIVITIES);
                        //当前版本号
                        printWriter.println("App Version:" + pi.versionName + "_" + pi.versionCode);
                        //当前系统
                        printWriter.println(
                                "OS version:" + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);
                        //制造商
                        printWriter.println("Vendor:" + Build.MANUFACTURER);
                        //手机型号
                        printWriter.println("Model:" + Build.MODEL);
                        //CPU架构
                        printWriter.println("CPU ABI:" + Build.CPU_ABI);
                        ex.printStackTrace(printWriter);
                    }
                    catch (PackageManager.NameNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                });
        ex.printStackTrace();
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}