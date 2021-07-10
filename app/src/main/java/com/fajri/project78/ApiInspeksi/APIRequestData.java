package com.fajri.project78.ApiInspeksi;

import com.fajri.project78.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(

            @Field("date") String date,
            @Field("nama_bay") String namaBay,
            @Field("kondisi_pmt") String kondisiPmt,
            @Field("kondisi_pegas") String kondisiPegas,
            @Field("tekanan_gas") String tekananGas,
            @Field("angka_tekanangas") String angkaTekananGas,
            @Field("indikator_sf6") String indikatorSf6,
            @Field("counter_pmt") String counterPmt,
            @Field("kondisi_kabel") String kondisiKabel
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id_inspeksi") int idInspeksi
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id_inspeksi") int idInspeksi
    );

    @FormUrlEncoded
    @POST("UPDATE.php")
    Call<ResponseModel> ardUpdateData(

            @Field("id_inspeksi") int idInspeksi,
            @Field("date") String date,
            @Field("nama_bay") String namaBay,
            @Field("kondisi_pmt") String kondisiPmt,
            @Field("kondisi_pegas") String kondisiPegas,
            @Field("tekanan_gas") String tekananGas,
            @Field("angka_tekanangas") String angkaTekananGas,
            @Field("indikator_sf6") String indikatorSf6,
            @Field("counter_pmt") String counterPmt,
            @Field("kondisi_kabel") String kondisiKabel
    );
}
