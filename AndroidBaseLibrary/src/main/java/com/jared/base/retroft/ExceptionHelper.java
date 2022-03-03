package com.jared.base.retroft;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jared.base.utils.CLog;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @ClassName ExceptionHelper
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class ExceptionHelper {
    private static final String TAG = "ExceptionHelper";

    public static String handleException(Throwable e) {
        e.printStackTrace();
        String error;
        //网络超时
        if (e instanceof SocketTimeoutException) {
            CLog.e(TAG, "网络连接异常: " + e.getMessage());
            error = "网络连接异常";
        } else if (e instanceof ConnectException) {
            //均视为网络错误
            CLog.e(TAG, "网络连接异常: " + e.getMessage());

            error = "网络连接异常";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //均视为解析错误
            CLog.e(TAG, "数据解析异常: " + e.getMessage());
            error = "数据解析异常";
        } else if (e instanceof ApiException) {
            //服务器返回的错误信息
            error = e.getCause().getMessage();
        } else if (e instanceof UnknownHostException) {
            CLog.e(TAG, "网络连接异常: " + e.getMessage());
            error = "网络连接异常";
        } else if (e instanceof IllegalArgumentException) {
            CLog.e(TAG, "下载文件已存在: " + e.getMessage());
            error = "下载文件已存在";
        } else {
            //未知错误
            try {
                CLog.e(TAG, "错误: " + e.getMessage());
            } catch (Exception e1) {
                CLog.e(TAG, "未知错误Debug调试 ");
            }
            error = "错误";
        }
        return error;
    }

}