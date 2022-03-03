package com.jared.base.contact;


import io.reactivex.disposables.Disposable;

/**
 * @ClassName BasePresenter
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public interface IBasePresenter {
    //默认初始化
    void start();

    //Activity关闭把view对象置为空
    void detach();

    //将网络请求的每一个disposable添加进入CompositeDisposable，再退出时候一并注销
    void addDisposable(Disposable subscription);

    //注销所有请求
    void unDisposable();

}
