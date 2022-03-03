package com.jared.base.thread;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName ThreadUtil
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class RxThreadConvert {
    //主线程做操作
    public static void doOnUIThread(UITask uiTask) {
        Observable.just(uiTask)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UITask::doOnUI);

    }
    //io线程做操作
    public static void doOnThread(ThreadTask threadTask) {
        Observable.just(threadTask)
                .observeOn(Schedulers.io())
                .subscribe(ThreadTask::doOnThread);

    }

    public interface ThreadTask {
        void doOnThread();
    }

    public interface UITask {
        void doOnUI();
    }

}
