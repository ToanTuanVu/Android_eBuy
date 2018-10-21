package com.example.admin.ebuy.network;

import android.graphics.Bitmap;

import com.example.admin.ebuy.model.respon.ProductDetailResponse;
import com.example.admin.ebuy.model.respon.ProductResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by tuan.nguyen on 23/6/18.
 */

public interface EBServices {

    String PATH="/api";
//    @GET("/api/configs")
//    Observable<ConfigResponse> getConfig();
//
//    @POST("/api/customers/login")
//    @Headers("Content-Type: application/json")
//    Observable<UserResponse> customerLogin(@Body LoginRequest loginRequest, @Query("time") String time);
//
//    @Multipart
//    @Headers("Content-Type: application/json")
//    @POST("api/orders/{id}/type/upload-task")
//    Observable<ImageResponse> postImage(@Path("id") int teacher_id, @Path("type") int type, @Query("task") Bitmap image);
    @GET(PATH+"/listproduct/getall")
    Observable<ProductResponse> getProduct();
    @GET(PATH+"/productdetail/getall")
    Observable<ProductDetailResponse> getProductDetail();


}
