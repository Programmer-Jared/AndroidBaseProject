package com.jared.base.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewbinding.ViewBinding;

import com.jared.base.BaseConfig;
import com.jared.base.BaseManager;
import com.jared.base.event.Event;
import com.jared.base.contact.IBasePresenter;
import com.jared.base.BindEventBus;
import com.jared.base.network.NetworkCode;
import com.jared.base.utils.AppManager;
import com.jared.base.utils.CLog;
import com.jared.base.utils.PackageUtil;
import com.jared.base.utils.ReflectUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * @ClassName BaseActivity
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public abstract class BaseActivity< VB extends ViewBinding,P extends IBasePresenter> extends AppCompatActivity {
    protected String TAG;
    protected P presenter;
    public Context context;
    protected VB binding;
    private BaseConfig baseConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CLog.d(TAG, "onCreate: Start");
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        baseConfig=BaseManager.getInstance().getBaseConfig();
        if(!baseConfig.isVisibilityStatusBar()){
            //隐藏状态兰
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if(!baseConfig.isVisibilityNavigationBar()){
            //隐藏导航栏
            hideNavigation();
        }
        super.onCreate(savedInstanceState);
        TAG = PackageUtil.getSimpleClassName(this);
        context = this;
        //将当前activity添加进入管理栈
        AppManager.getInstance().addActivity(this);
        registerEventBus();
        presenter = initPresenter();
        binding=  ReflectUtil.getInflate(getClass(),getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        setListener();
        initData();
        CLog.d(TAG, "onCreate: End");
        // TODO: 2022/3/3 沉浸式状态栏适配  齐刘海状态栏适配
//        //沉浸式代码配置
//        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
//        StatusBarUtils.setRootViewFitsSystemWindows(this, true);
//        //设置状态栏透明
//        StatusBarUtils.setTranslucentStatus(this);
//        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
//        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtils.setStatusBarColor(this, 0x55000000);
//        }
    }

    /**
     * 使用注册EventBus
     * 子类使用EventBus 在类上方声明 {@link BindEventBus} 注解
     */
    private void registerEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    private void unregisterEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 接收EventBus 事件
     *
     * @param event 事件内容
     */

    @Subscribe
    public void onEvent(Event event) {
        if (event.getWhat() == NetworkCode.NET_CONNECT) {
            onNetworkAvailable();
        } else if (event.getWhat() == NetworkCode.NET_DISCONNECT) {
            onNetworkLost();
        }

    }

    /**
     * 网络连接
     */
    protected void onNetworkAvailable() {

    }

    /**
     * 网络断开
     */
    protected void onNetworkLost() {

    }

    public abstract void initView();

    public abstract void setListener();

    public abstract void initData();


    @Override
    protected void onDestroy() {
        // TODO: 2022/3/3 释放dialog
        //将当前activity移除管理栈
        AppManager.getInstance().removeActivity(this);
        if (presenter != null) {
            presenter.detach();//在presenter中解绑释放view
            presenter = null;
        }
        unregisterEventBus();
        super.onDestroy();
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();

    /**
     * 隐藏导航栏
     */
    private void hideNavigation() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            final Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
            window.setAttributes(params);
        }
    }
}
