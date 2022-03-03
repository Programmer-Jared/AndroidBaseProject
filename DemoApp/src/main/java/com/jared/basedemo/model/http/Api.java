package com.jared.basedemo.model.http;


import com.jared.base.retroft.BaseApiImpl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName Api
 * @Author ChenDongXu
 * @Date 2020/4/7 14:57
 * @Version 1.0
 * @Description TODO 网络访问
 */
public class Api extends BaseApiImpl {
    private static final String TAG = "Api";
    private static Api api = new Api(RetrofitService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {
        api.httpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        api.httpBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        api.httpBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        api.httpBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        // TODO: 2021/8/20 添加头
//                        .addHeader(Constant.APP_KEY, Constant.APP_ID)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        })
//                .addInterceptor(api.getLoggerInterceptor())
        ;
        api.retrofitBuilder.client(api.httpBuilder.build());
        return api.getRetrofit().create(RetrofitService.class);
    }
}
