package com.jared.base;

import android.app.Application;

import com.jared.base.network.NetworkManager;
import com.jared.base.toast.BaseToast;
import com.jared.base.utils.AppUtils;
import com.jared.base.utils.CLog;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * @ClassName BaseApplication
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        BaseManager.getInstance().setupBaseConfig(getBaseConfig(),this);
        super.onCreate();
    }

    protected abstract BaseConfig getBaseConfig();
}
