package com.liuan.android.base.tool;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Peach Parrot
 * @date 2020年11月03日 09:56
 */
public class LogFileSaver
{
    public static boolean saveLogFile(String logPath, String fileName, String logContent,
                                      OnGetPrintWriterCallback onGetPrintWriterCallback)
    {
        if (!Environment.getExternalStorageState()
                        .equals(Environment.MEDIA_MOUNTED))
        {
            return false;
        }
        File fileDir = new File(logPath);
        if (fileDir.mkdirs())
        {
            File file = new File(logPath + File.separator + fileName);
            try
            {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                printWriter.println(logContent);
                if (onGetPrintWriterCallback != null)
                {
                    onGetPrintWriterCallback.onGetPrintWriter(printWriter);
                }
                printWriter.close();
                return true;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean saveLogFile(String logPath, String fileName, String logContent)
    {
        return saveLogFile(logPath, fileName, logContent, null);
    }

    public interface OnGetPrintWriterCallback
    {
        void onGetPrintWriter(PrintWriter printWriter);
    }
} 