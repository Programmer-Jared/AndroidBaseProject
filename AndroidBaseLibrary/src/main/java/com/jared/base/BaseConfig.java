package com.jared.base;

/**
 * @ClassName BaseConfig
 * @Author Jared
 * @Date 2022/3/3 11:03
 * @Version 1.0
 * @Description Base配置
 */
public class BaseConfig {
    /**
     * Base性能调试开关
     */
    public final boolean isBaseDebug = true;
    /**
     * 调试模式开关
     */
    private boolean isDebugMode = false;
    /**
     * 网络监听开关
     */
    @Deprecated
    private boolean isOpenNetworkListener = false;
    /**
     * Toast初始化开关
     */
    private boolean isUseBaseToast = false;
    /**
     * 状态显示开关   默认显示
     */
    private boolean isVisibilityStatusBar = true;
    /**
     * 导航栏显示开关  默认显示
     */
    private boolean isVisibilityNavigationBar = true;


    private boolean isUseAutoSize = false;

    private BaseAutoSizeConfig baseAutoSizeConfig;


    public BaseConfig() {
    }

    public BaseConfig(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    /**
     * 设置是否使用BaseToast true or false
     *
     * @param useBaseToast
     */
    public BaseConfig setUseBaseToast(boolean useBaseToast) {
        isUseBaseToast = useBaseToast;
        return this;
    }

    /**
     * 使用AutoSize
     *
     * @param useAutoSize
     * @param baseAutoSizeConfig
     * @return
     */
    public BaseConfig setUseAutoSize(boolean useAutoSize, BaseAutoSizeConfig baseAutoSizeConfig) {
        isUseAutoSize = useAutoSize;
        this.baseAutoSizeConfig = baseAutoSizeConfig;
        return this;
    }

    public boolean isUseAutoSize() {
        return isUseAutoSize;
    }

    /**
     * 获取AutoSizeConfig
     *
     * @return
     */
    public BaseAutoSizeConfig getBaseAutoSizeConfig() {
        return baseAutoSizeConfig;
    }

    /**
     * 设置Base框架Debug模式
     *
     * @return BaseConfig
     */
    public BaseConfig setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
        return this;
    }

    /**
     * 设置Base框架网络监听状态
     *
     * @param openNetworkListener 网络监听 true or false
     * @return
     */

    @Deprecated
    public BaseConfig setOpenNetworkListener(boolean openNetworkListener) {
        isOpenNetworkListener = openNetworkListener;
        return this;
    }

    /**
     * 设置状态栏显示隐藏
     *
     * @param visibilityStatusBar
     */
    public BaseConfig setVisibilityStatusBar(boolean visibilityStatusBar) {
        isVisibilityStatusBar = visibilityStatusBar;
        return this;
    }


    /**
     * 设置下方导航栏隐藏显示
     *
     * @param visibilityNavigationBar
     */
    public BaseConfig setVisibilityNavigationBar(boolean visibilityNavigationBar) {
        isVisibilityNavigationBar = visibilityNavigationBar;
        return this;
    }

    /**
     * 获取框架Debug模式
     *
     * @return isDebugMode
     */
    public boolean isDebugMode() {
        return isDebugMode;
    }

    /**
     * 获取Base框架网络监听状态
     *
     * @return isOpenNetworkListener true or false
     */

    public boolean isOpenNetworkListener() {
        return isOpenNetworkListener;
    }

    /**
     * 获取BaseToast开关
     *
     * @return isUseBaseToast
     */
    public boolean isUseBaseToast() {
        return isUseBaseToast;
    }

    /**
     * 获取导航栏隐藏显示状态
     *
     * @return
     */
    public boolean isVisibilityNavigationBar() {
        return isVisibilityNavigationBar;
    }

    /**
     * 获取状态栏显示状态
     *
     * @return
     */
    public boolean isVisibilityStatusBar() {
        return isVisibilityStatusBar;
    }
}
