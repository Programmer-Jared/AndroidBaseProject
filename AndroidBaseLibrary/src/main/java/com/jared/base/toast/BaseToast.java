package com.jared.base.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName BaseToast
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description todo 加入可以自定义布局抽象
 */

public class BaseToast {
    private static Toast toast;
    private static Context mContext;

    public static void initContext(Context context) {
        mContext = context;
    }

    private static Context getAppContext() {
        return mContext;
    }


    public static void showLongToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getAppContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


}
