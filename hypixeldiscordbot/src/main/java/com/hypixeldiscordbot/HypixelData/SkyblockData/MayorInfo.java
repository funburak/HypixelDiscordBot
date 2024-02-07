package com.hypixeldiscordbot.HypixelData.SkyblockData;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class MayorInfo {
    private boolean success;
    private long lastUpdated;
    private Mayor mayor;
    private Election current;

    @Override
    public String toString() {
        return "MayorInfo => lastUpdated=" + convertUnix(lastUpdated) + "\nmayor=" + mayor + "\ncurrent="
                + current + "]";
    }

    private String convertUnix(long unixTimestamp){
        Instant instant = Instant.ofEpochMilli(unixTimestamp);
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    
}
