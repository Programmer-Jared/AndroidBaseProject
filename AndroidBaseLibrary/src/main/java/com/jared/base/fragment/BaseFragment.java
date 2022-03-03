package com.jared.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.jared.base.event.Event;

import com.jared.base.contact.IBaseView;
import com.jared.base.BindEventBus;
import com.jared.base.contact.IBasePresenter;
import com.jared.base.network.NetworkCode;
import com.jared.base.utils.PackageUtil;
import com.jared.base.utils.ReflectUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * @ClassName BaseFragment
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public abstract class BaseFragment<VB extends ViewBinding, P extends IBasePresenter> extends
        Fragment
        implements IBaseView, View.OnTouchListener {
    protected P presenter;
    public Context context;
    protected String TAG;
    protected VB binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = PackageUtil.getSimpleClassName(this);
        presenter = initPresenter();
        registerEventBus();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ReflectUtil.getInflate(getClass(), inflater, container);
        if (binding != null) {
            initView();
            setListener();
            initData();
            return binding.getRoot();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


    public abstract void initView();

    public abstract void setListener();

    public abstract void initData();




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public void onResume() {
        super.onResume();

    }



    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        unregisterEventBus();
        super.onDestroyView();
    }

    public abstract P initPresenter();


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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            // TODO: 2022/3/3 判断autoisze 配置按宽或者高适配 判断进行适配
//            AutoSizeCompat.autoConvertDensityBaseOnWidth(getResources(), 1280);
        }
    }
}
