package com.jared.base.utils;

import com.orhanobut.logger.Logger;


/**
 * @ClassName CLog
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */

public class CLog {
    private static String appLogTag="BaseProject";

    private static boolean isDebug = true;
    public static void setDebugMode(boolean isDebug, String appLogTagStr) {
        appLogTag = appLogTagStr;
        setDebugMode(isDebug);
    }

    public static void setDebugMode(boolean isDebug) {
        if (isDebug) {
            Logger.e(" ......................阿弥陀佛......................\n" +
                    "                       _oo0oo_                      \n" +
                    "                      o8888888o                     \n" +
                    "                      88\" . \"88                     \n" +
                    "                      (| -_- |)                     \n" +
                    "                      0\\  =  /0                     \n" +
                    "                   ___/‘---’\\___                   \n" +
                    "                  .' \\|       |/ '.                 \n" +
                    "                 / \\\\|||  :  |||// \\                \n" +
                    "                / _||||| -卍-|||||_ \\               \n" +
                    "               |   | \\\\\\  -  /// |   |              \n" +
                    "               | \\_|  ''\\---/''  |_/ |              \n" +
                    "               \\  .-\\__  '-'  ___/-. /              \n" +
                    "             ___'. .'  /--.--\\  '. .'___            \n" +
                    "         .\"\" ‘<  ‘.___\\_<|>_/___.’>’ \"\".          \n" +
                    "       | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        \n" +
                    "         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        \n" +
                    "    =====‘-.____‘.___ \\_____/___.-’___.-’=====     \n" +
                    "                       ‘=---=’                      \n" +
                    "                                                    \n" +
                    "....................佛祖保佑 ,永无BUG...................");
        }
        CLog.isDebug = isDebug;
    }

    public static void log(String tag, String message, Throwable throwable) {
        if (isDebug) {
            Logger.log(Logger.ERROR, tag, message, throwable);
        }
    }

    public static void d(String className, String content) {

        if (isDebug) {
            Logger.d(appLogTag + "==>" + className + "→" + content);
        }
    }

    public static void e(String className, String content) {
        if (isDebug) {
            Logger.e(appLogTag + "==>" + className + "→" + content);
        }
    }

    public static void i(String className, String content) {
        if (isDebug) {
            Logger.i(appLogTag + "==>" + className + "→" + content);
        }
    }

}
