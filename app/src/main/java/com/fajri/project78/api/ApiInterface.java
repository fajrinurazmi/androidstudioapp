package com.fajri.project78.api;

import com.fajri.project78.model.ResponseModel;
import com.fajri.project78.model.login.Login;
import com.fajri.project78.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name

    );

    @GET("getdata.php")
    Call<ResponseModel> ardRetrieveDatapeg();

}
