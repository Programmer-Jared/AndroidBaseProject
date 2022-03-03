package com.jared.base.listener;

import android.view.View;

/**
 * @ClassName OnMultiClickListener
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description 防止快速点击
 */
public abstract class OnMultiClickListener implements View.OnClickListener {

    private static int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime;

    public OnMultiClickListener() {

    }

    public OnMultiClickListener(int time) {
        MIN_CLICK_DELAY_TIME = time;
    }

    /**
     * onMultiClick
     *
     * @param v v
     */
    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}
