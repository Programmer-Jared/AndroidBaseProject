package com.jared.basedemo;



import com.jared.base.BaseApplication;
import com.jared.base.BaseConfig;


/**
 * @ClassName POCApplication
 * @Author ChenDongXu
 * @Date 2021/8/20 10:09
 * @Version 1.0
 * @Description TODO
 */

public class DemoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected BaseConfig getBaseConfig() {
        return new BaseConfig().setDebugMode(true).setUseBaseToast(true).setOpenNetworkListener(true)
//                .setVisibilityNavigationBar(false).setVisibilityNavigationBar(false)
                ;
    }
}

