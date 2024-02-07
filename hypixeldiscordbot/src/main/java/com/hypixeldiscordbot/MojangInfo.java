package com.hypixeldiscordbot;

import com.google.gson.annotations.SerializedName;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MojangInfo {

    interface getMojangId{
        @GET("/users/profiles/minecraft/{name}")
        Call<MojangInfo> getUuid(@Path("name") String name);
    }

    @SerializedName("id")
    private String uuid;
    private String name;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void fetchUuid(String name, Consumer<String> uuid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mojang.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getMojangId getUuid = retrofit.create(getMojangId.class);
        getUuid.getUuid(name).enqueue(new Callback<MojangInfo>() {
            @Override
            public void onResponse(Call<MojangInfo> call, Response<MojangInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    MojangInfo mojangInfo = response.body();
                    uuid.accept(mojangInfo.getUuid());
                }
                else{
                    uuid.accept(null);
                }
            }

            @Override
            public void onFailure(Call<MojangInfo> call, Throwable t) {
                uuid.accept(null);
            }
            
        });
    }

    
}
