package com.jared.base;

import android.app.Application;
import android.content.Context;

import com.jared.base.network.NetworkManager;
import com.jared.base.toast.BaseToast;
import com.jared.base.utils.AppUtils;
import com.jared.base.utils.CLog;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * @ClassName BaseManager
 * @Author Jared
 * @Date 2022/3/3 11:17
 * @Version 1.0
 * @Description
 */
public class BaseManager {
    private static final String TAG = "BaseManager";
    private volatile static BaseManager baseManager;
    private Context context;
    public static BaseManager getInstance() {
        if (baseManager == null) {
            synchronized (BaseManager.class) {
                if (baseManager == null) {
                    baseManager = new BaseManager();
                }
            }
        }
        return baseManager;
    }
    private BaseConfig baseConfig;

    public void setupBaseConfig(BaseConfig baseConfig, Application application) {
        context=application.getApplicationContext();
        this.baseConfig = baseConfig;
        startConfig();
    }
    private void startConfig(){
        if(baseConfig.isDebugMode()){
            Logger.addLogAdapter(new AndroidLogAdapter());
            CLog.setDebugMode(true, AppUtils.getAppName( context));
            CustomActivityOnCrash.install(context);
        }

        if(baseConfig.isUseBaseToast()){
            BaseToast.initContext(context);
        }
        // TODO: 2022/3/3 考虑是否舍弃网络监听
        if(baseConfig.isOpenNetworkListener()){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             new NetworkManager(context);
            }
        }

        if(baseConfig.isUseAutoSize()){
            // TODO: 2022/3/3 开启autosize
        }else {
            //TODO: 关闭autosize
        }
    }

    public BaseConfig getBaseConfig() {
        return baseConfig;
    }

    public boolean isDebug(){
        return baseConfig.isDebugMode();
    }
}
