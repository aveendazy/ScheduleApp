package com.example.scheduleapp.API;

import com.example.scheduleapp.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("API_Senin.php")
    Call<ResponseModel> ardGetData();

    @FormUrlEncoded
    @POST("API_Senin.php")
    Call<ResponseModel> ardCreateData(
            @Field("wktMulai") String wktMulai,
            @Field("wktSelesai") String wktSelesai,
            @Field("jadwal") String jadwal
    );

    @FormUrlEncoded
    @DELETE("API_Senin.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @GET("API_Senin.php?id=")
    Call<ResponseModel> ardGetData2(
            @Field("id") int id
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
