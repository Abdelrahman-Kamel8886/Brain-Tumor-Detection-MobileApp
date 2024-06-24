package com.abdo.braintumordetection.network;


import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetroServices {


    @Multipart
    @POST("files")
    Call<String> get(@Part MultipartBody.Part file);


    @Multipart
    @POST("seg")
    Call<String> getseg(@Part MultipartBody.Part file);


    @Multipart
    @POST("multifiles")
    Call<String> getmulti(@Part MultipartBody.Part[] files);

    @POST("multiSeg")
    Call<ArrayList<String>> segmulti();


}
