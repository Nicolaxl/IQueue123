package com.example.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APINews {

    private static Retrofit retrofit = null;

    public static BingNewsAPI getBingNewsAPI(){

        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(BingNewsAPI.BaseURL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(BingNewsAPI.class);
    }

}
