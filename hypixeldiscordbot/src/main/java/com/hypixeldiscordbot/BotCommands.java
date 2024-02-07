package com.hypixeldiscordbot;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hypixeldiscordbot.HypixelData.HypixelAPI;
import com.hypixeldiscordbot.HypixelData.PlayerInfo;
import com.hypixeldiscordbot.HypixelData.StatusInfo;
import com.hypixeldiscordbot.HypixelData.SkyblockData.MayorInfo;
import com.hypixeldiscordbot.HypixelData.SkyblockData.MuseumInfo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class BotCommands extends ListenerAdapter {
    private static final String apiKey = "your-api-key";

    interface HypixelInfo{ // API calls related to hypixel data
        @GET("/v2/player")
        Call<PlayerInfo> getPlayerData(@Header("API-Key") String apiKey, @Query("uuid") String userid);

        @GET("/v2/status")
        Call<StatusInfo> getStatusInfo(@Header("API-Key") String apiKey, @Query("uuid") String userid);
    }

    interface SkyblockInfo{ // API calls related to skyblock data
        @GET("/v2/resources/skyblock/election")
        Call<MayorInfo> getElectionInfo();

        @GET("/v2/skyblock/profiles")
        Call<JsonElement> getProfileInfo(@Header("API-Key") String apiKey, @Query("uuid") String userid);

        @GET("/v2/skyblock/museum")
        Call<MuseumInfo> getMuseumInfo(@Header("API-Key") String apiKey, @Query("profile") String profileId);

    }

    public static void main(String[] args) throws InterruptedException {
        JDA bot = JDABuilder.createLight("your-bot-token")
                    .addEventListeners(new BotCommands())
                    .setActivity(Activity.playing("Hypixel info"))
                    .build();
        

        bot.updateCommands().addCommands(
            Commands.slash("info", "Get your hypixel player info")
                .addOptions(new OptionData(OptionType.STRING, "name", "Your mojang name").setRequired(true)),
            Commands.slash("status", "Get status info")
                .addOptions(new OptionData(OptionType.STRING,"name","Your mojang name").setRequired(true)),
            Commands.slash("election","Info of the current mayor and election"),
            Commands.slash("museum", "Get your museum info")
                .addOptions(new OptionData(OptionType.STRING,"name","Your mojang name").setRequired(true), 
                new OptionData(OptionType.STRING, "profile", "Enter your profile name").setRequired(true)) 
            ).queue(
                success -> System.out.println("Commands updated successfully!"),
                error -> System.err.println("Error occurred while updating commands: " + error.getMessage())
            );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        MojangInfo info = new MojangInfo();
        switch (event.getName()) {
            case "info":
            info.fetchUuid(event.getOption("name").getAsString(), uuid -> {
                if(uuid != null){
                    Retrofit retrofit = HypixelAPI.getInstance();
                    HypixelInfo hypixelInfo = retrofit.create(HypixelInfo.class);
                    hypixelInfo.getPlayerData(apiKey, uuid).enqueue(new Callback<PlayerInfo>() {

                        @Override
                        public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                            if(response.isSuccessful() && response.body() != null){
                                PlayerInfo playerInfo = response.body();
                                event.reply(playerInfo.getPlayer().toString()).queue();
                            }
                            else{
                                event.reply("Response Unsuccessful").queue();
                            }
                        }

                        @Override
                        public void onFailure(Call<PlayerInfo> call, Throwable t) {
                            event.reply(t.getMessage()).queue();
                        }
                    });
                }
                else{
                    event.reply("An error occured").queue();
                }
            });
            break;
            case "status":
                info.fetchUuid(event.getOption("name").getAsString(), uuid -> {
                    if(uuid != null){
                        Retrofit retrofit = HypixelAPI.getInstance();
                        HypixelInfo hypixelInfo = retrofit.create(HypixelInfo.class);
                        hypixelInfo.getStatusInfo(apiKey, uuid).enqueue(new Callback<StatusInfo>() {

                            @Override
                            public void onResponse(Call<StatusInfo> call, Response<StatusInfo> response) {
                                if(response.isSuccessful() && response.body() != null){
                                    StatusInfo statusInfo = response.body();
                                    event.reply(statusInfo.toString()).queue();
                                }
                                else{
                                    event.reply("Response Unsuccessful").queue();
                                }
                            }

                            @Override
                            public void onFailure(Call<StatusInfo> call, Throwable t) {
                                event.reply(t.getMessage()).queue();
                            }
                        });
                    }
                    else{
                        event.reply("An error occured").queue();
                    }
                });
                break;
            case "election":
                Retrofit retrofit = HypixelAPI.getInstance();
                SkyblockInfo skyblockInfo = retrofit.create(SkyblockInfo.class);
                skyblockInfo.getElectionInfo().enqueue(new Callback<MayorInfo>() {

                    @Override
                    public void onResponse(Call<MayorInfo> call, Response<MayorInfo> response) {
                        if(response.isSuccessful() && response.body() != null){
                            MayorInfo mayorInfo = response.body();
                            event.reply(mayorInfo.toString()).queue();
                        }
                        else{
                            event.reply("Response Unsuccessful " + response.code()).queue();
                        }
                    }

                    @Override
                    public void onFailure(Call<MayorInfo> call, Throwable t) {
                        event.reply(t.getMessage()).queue();
                    }
                });
                break;
            case "museum": 
                info.fetchUuid(event.getOption("name").getAsString(), uuid -> {
                    if(uuid != null){
                        Retrofit retrofit1 = HypixelAPI.getInstance();
                        SkyblockInfo skyblockInfo1 = retrofit1.create(SkyblockInfo.class);
                        skyblockInfo1.getProfileInfo(apiKey, uuid).enqueue(new Callback<JsonElement>() {

                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                if(response.isSuccessful() && response.body() != null){
                                    JsonElement respond = response.body();
                                    if(respond.isJsonObject()){
                                        JsonObject jsonObject = respond.getAsJsonObject();
                                        JsonArray jsonArray = jsonObject.getAsJsonArray("profiles");
                                        for(JsonElement element : jsonArray){
                                            if(element.isJsonObject()){
                                                JsonObject profileObject = element.getAsJsonObject();
                                                JsonElement cuteElement = profileObject.get("cute_name");
                                                if(cuteElement != null && !cuteElement.isJsonNull() && cuteElement.getAsString().equals(event.getOption("profile").getAsString())){
                                                    String profileId = profileObject.get("profile_id").getAsString();
                                                    if(profileId != null){
                                                        System.out.println(profileId);
                                                        Retrofit retrofit = HypixelAPI.getInstance();
                                                        SkyblockInfo skyblockInfo = retrofit.create(SkyblockInfo.class);
                                                        skyblockInfo.getMuseumInfo(apiKey, profileId).enqueue(new Callback<MuseumInfo>() {

                                                            @Override
                                                            public void onResponse(Call<MuseumInfo> call,
                                                                    Response<MuseumInfo> response) {
                                                                if(response.isSuccessful() && response.body() != null){
                                                                    MuseumInfo museumInfo = response.body();
                                                                    event.reply(museumInfo.toString()).queue();
                                                                }
                                                                else{
                                                                    event.reply("Response Unsuccessful2").queue();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<MuseumInfo> call, Throwable t) {
                                                                event.reply(t.getMessage()).queue();
                                                            }
                                                            
                                                        });
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else{
                                    event.reply("Response Unsuccessful1").queue();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                event.reply(t.getMessage()).queue();
                            }
                            
                        });
                    }
                    else{
                        event.reply("An error occured").queue();
                    }
                });
                break;
            default:
                break;
        }
    }    
}
