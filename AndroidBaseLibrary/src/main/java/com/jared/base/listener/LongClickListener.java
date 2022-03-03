package com.jared.base.listener;

import static java.util.concurrent.Executors.defaultThreadFactory;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.jared.base.utils.CLog;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LongClickListener
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class LongClickListener implements View.OnTouchListener {
    private static final String TAG = "LongClickListener";
    private int touchMax = 50;
    private int mLastMotionX;
    private int mLastMotionY;
    private ScheduledThreadPoolExecutor scheduled;
    private boolean isLongClick = false;
    private TimerTask task;
    private boolean isMoved;
    private Handler handler;
    private ILongPressListener longClickListener;
    private View longClickView;

    /**
     * @param handler           long click handler
     * @param longClickListener long click callback
     * @param longClickView     long click view
     */
    public LongClickListener(Handler handler, View longClickView, ILongPressListener longClickListener) {
        this.handler = handler;
        this.longClickListener = longClickListener;
        this.longClickView = longClickView;
    }

    public void actionUp() {
        handler.removeCallbacks(mLongPressRunnable);
        if (isLongClick) {
            isLongClick = false;
            stopSchedule();
            longClickListener.onLongClickEnd(longClickView);
            longClickView.setPressed(false);
        } else {
            stopSchedule();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                CLog.d(TAG, "onTouch: MotionEvent.ACTION_UP");
                handler.removeCallbacks(mLongPressRunnable);
                if (isLongClick) {
                    isLongClick = false;
                    stopSchedule();
                    longClickListener.onLongClickEnd(longClickView);
                    longClickView.setPressed(false);
                    return true;
                }
                stopSchedule();
                break;
            case MotionEvent.ACTION_MOVE:
                CLog.d(TAG, "onTouch: MotionEvent.ACTION_MOVE");
                if (isMoved) {
                    break;
                }
                if (Math.abs(mLastMotionX - x) > touchMax
                        || Math.abs(mLastMotionY - y) > touchMax) {
                    //When the movement exceeds the threshold, it means move has happened.
                    CLog.d(TAG, "onTouch: Math.abs > touchMax");
                    isMoved = true;
                    if (isLongClick) {
                        CLog.d(TAG, "onTouch: isLongClick ==true");
                        isLongClick = true;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                CLog.d(TAG, "onTouch: MotionEvent.ACTION_DOWN");
                //long press judge
                handler.removeCallbacks(mLongPressRunnable);
                mLastMotionX = x;
                mLastMotionY = y;
                isMoved = false;
                handler.postDelayed(mLongPressRunnable, 1000);
                break;
            default:

                break;
        }


        return false;
    }


    private Runnable mLongPressRunnable = new Runnable() {
        @Override
        public void run() {
            task = new TimerTask() {
                @Override
                public void run() {
                    if (longClickListener != null) {
                        longClickListener.onLongClick(longClickView);
                        isLongClick = true;
                    }
                }
            };

            CLog.d(TAG, "onTouch: now is long click");
            scheduled = new ScheduledThreadPoolExecutor(2, defaultThreadFactory());
            scheduled.scheduleAtFixedRate(task, 0, 400, TimeUnit.MILLISECONDS);
        }
    };


    private void stopSchedule() {
        if (scheduled != null) {
            scheduled.shutdownNow();
            scheduled = null;
        }

        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public interface ILongPressListener {
        /**
         * long press step button callback
         *
         * @param view click step button id
         */
        void onLongClick(View view);

        /**
         * when long press step button finish,
         * do tune special step.
         */
        void onLongClickEnd(View view);
    }


}
