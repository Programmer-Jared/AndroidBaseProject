package com.jared.basedemo.model.bean;


import com.google.gson.Gson;

/**
 * @ClassName BaseBean
 * @Author ChenDongXu
 * @Date 2020/3/18 16:08
 * @Version 1.0
 * @Description TODO
 */
public class BaseBean<T> {

    private String code;
    private String desc;

    private T data;



    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + new Gson().toJson(data) +
                '}';
    }
}
