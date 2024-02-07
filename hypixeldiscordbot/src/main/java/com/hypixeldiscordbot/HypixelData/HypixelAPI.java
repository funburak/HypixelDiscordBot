package com.hypixeldiscordbot.HypixelData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HypixelAPI {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hypixel.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
