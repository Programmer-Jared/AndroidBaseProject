package com.jared.basedemo.model.http;



import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ClassName RetrofitService
 * @Author ChenDongXu
 * @Date 2020/4/7 14:57
 * @Version 1.0
 * @Description TODO 基础网络访问
 */
public interface RetrofitService {
    //    String BASE_URL = "http://10.170.136.167:8008/";
    String BASE_URL = "http://10.170.245.58:8008/";


//    @GET("rest/App/recommendedList/3")
//    Observable<BaseBean<List<ProductListBean>>> getRecommendedList();
//
//    @GET("rest/App/recommendedList/{ownerId}")
//    Observable<BaseBean<List<ProductListBean>>> getRecommendedList(@Query("ownerId") String productVerId);
//
//    @POST("/rest/App/productSwitch")
//    Observable<BaseBean> switchProduct(@Body SwitchProductBean switchProductBean);
//
//    @GET("/rest/App/findProductSetting/3/{productVersionId}")
//    Observable<BaseBean<ProductSettingBean>> getProductSetting(@Path("productVersionId") String productVerId);
//
//    @POST("/rest/App/saveProductSetting")
//    Observable<BaseBean> saveProductSetting(@Body ProductSettingBean productSettingBean);
}