package com.jared.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

/**
 * @ClassName LogcatHelper
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description 输出log保存到指定文件
 */
public class LogcatHelper {
    /**
     * @author Administrator
     * <p>
     * log打印日志保存,文件的保存以小时为单位
     * permission:<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     * <uses-permission android:name="android.permission.READ_LOGS" />
     */
    private static final String TAG = "LogCatHelper";
    private static LogcatHelper instance = null;
    private String dirPath;//保存路径
    private int appid;//应用pid
    private Thread logThread;

    /**
     * @param mContext
     * @param path     log日志保存根目录
     * @return
     */
    public static LogcatHelper getInstance(Context mContext, String path) {
        if (instance == null) {
            instance = new LogcatHelper(mContext, path);
        }
        return instance;
    }

    private static Context mC;

    private LogcatHelper(Context mContext, String path) {
        mC = mContext;
        appid = android.os.Process.myPid();
        if (TextUtils.isEmpty(path)) {
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "HondaLogDir" + File.separator + "ALL" + mContext.getPackageName();
        } else {
            dirPath = path;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 启动log日志保存
     */
    private LogRunnable logRunnable;

    public void start() {
        if (logThread == null) {
            logRunnable = new LogRunnable(appid, dirPath);
            logThread = new Thread(logRunnable);
            logThread.start();
        }

    }

    public void close() {
        if (logThread != null) {
            if (logRunnable != null) {
                logRunnable.close();
                logRunnable = null;
            }
            logThread.interrupt();
            logThread = null;
        }
    }

    private static class LogRunnable implements Runnable {

        private Process mProcess;
        private FileOutputStream fos;
        private BufferedReader mReader;
        private String cmds;
        private String mPid;

        public void close() {
            if (mProcess != null) {
                mProcess.destroy();
                mProcess = null;
            }
            try {
                if (mReader != null) {
                    mReader.close();
                    mReader = null;
                }
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public LogRunnable(int pid, String dirPath) {
            this.mPid = "" + pid;
            try {
                File file = new File(dirPath, FormatDate.getFormatDate() + ".log");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file, true);
                sysToScan(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            cmds = "logcat *:v | grep \"(" + mPid + ")\"";
        }

        @Override
        public void run() {
            try {
                mProcess = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()), 1024);
                String line;
                while ((line = mReader.readLine()) != null) {
                    if (line.length() == 0) {
                        continue;
                    }
                    if (fos != null && line.contains(mPid)) {
                        fos.write((FormatDate.getFormatTime() + "	" + line + "\r\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mProcess != null) {
                    mProcess.destroy();
                    mProcess = null;
                }
                try {
                    if (mReader != null) {
                        mReader.close();
                        mReader = null;
                    }
                    if (fos != null) {
                        fos.close();
                        fos = null;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private static class FormatDate {

        public static String getFormatDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            return sdf.format(System.currentTimeMillis());
        }

        public static String getFormatTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(System.currentTimeMillis());
        }
    }

    private static void sysToScan(String filePath) {
        //扫描指定文件夹中的文件
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        //向系统发送广播
        mC.sendBroadcast(intent);
    }

}
