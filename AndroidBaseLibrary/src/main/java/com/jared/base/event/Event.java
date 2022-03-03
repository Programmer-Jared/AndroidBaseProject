package com.jared.base.event;

/**
 * @ClassName Event
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class Event<T> {
    //事件类型
    private int what;
    //事件数据
    private T data;

    public Event() {
    }

    public Event(int what) {
        this.what = what;
    }

    public Event(int what, T data) {
        this.what = what;
        this.data = data;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
