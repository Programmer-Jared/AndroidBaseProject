package com.jared.base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
/**
 * @ClassName PackageUtil
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description 判断包名是否存在，打开其他APP
 */
public class PackageUtil {

    private PackageUtil() {

    }

    public static String getSimpleClassName(Object object) {
        Class clazz = object.getClass();
        String str1 = clazz.getName().replace("$", ".");
        String str2 = str1.replace(clazz.getPackage().getName(), "") + ".";

        return str2.substring(1);
    }

    /**
     * 检查包是否存在
     *
     * @param packageName
     * @return
     */
    public static boolean checkPackInfo(String packageName, PackageManager packageManager) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static void launchApp(String packageName, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (checkPackInfo(packageName, packageManager)) {
            final Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        }
    }
}
