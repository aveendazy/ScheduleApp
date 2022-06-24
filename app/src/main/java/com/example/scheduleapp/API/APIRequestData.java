package com.example.scheduleapp.API;

import com.example.scheduleapp.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @GET("API_Senin.php")
    Call<ResponseModel> ardGetData();

    @FormUrlEncoded
    @POST("API_Senin.php?method=post")
    Call<ResponseModel> ardCreateData(
            @Field("wktMulai") String wktMulai,
            @Field("wktSelesai") String wktSelesai,
            @Field("jadwal") String jadwal
    );

    @HTTP(method = "DELETE", path = "API_Senin.php", hasBody = true)
    Call<ResponseModel> ardDeleteData(
            @Query("id") int id
    );

    @GET("API_Senin.php")
    Call<ResponseModel> ardGetData2(
            @Query("id") int id
    );

    @FormUrlEncoded
    @POST("API_Senin.php?method=patch")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("wktMulai") String wktMulai,
            @Field("wktSelesai") String wktSelesai,
            @Field("jadwal") String jadwal
    );
}
