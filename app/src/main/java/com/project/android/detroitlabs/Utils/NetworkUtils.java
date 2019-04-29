package com.project.android.detroitlabs.Utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.project.android.detroitlabs.Constants.BaseUrl;

public class NetworkUtils {
    public static Retrofit instance;
    public static Retrofit getInstance(){
        if(instance==null)
            instance=new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return instance;
    }
}
