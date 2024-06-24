package com.abdo.braintumordetection.network;


import android.util.Log;

import com.abdo.braintumordetection.utils.Consts;
import com.abdo.braintumordetection.utils.SharedModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConnection {




    private static Retrofit retrofit;
    private static Gson gson = new GsonBuilder().setLenient().create();

    private synchronized static Retrofit getRetrofit(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .build();


        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(SharedModel.getApi())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

        }
        return retrofit;
    }
    public static RetroServices getServices(){
        return getRetrofit().create(RetroServices.class);
    }

}