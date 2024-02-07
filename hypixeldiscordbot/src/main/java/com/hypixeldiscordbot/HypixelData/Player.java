package com.hypixeldiscordbot.HypixelData;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Player {
    private String uuid;
    private String displayname;
    private String rank;
    private String packageRank;
    private String newPackageRank;
    private String monthlyPackageRank;
    private long firstLogin;
    private long lastLogin;
    private long lastLogout;
    private Object stats;

    private String convertUnix(long unixTimestamp){
        Instant instant = Instant.ofEpochMilli(unixTimestamp);
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    
    @Override
    public String toString() {
        return "Player displayname=" + displayname + "\nRank=" + rank + "\nPackage rank="
                + packageRank + "\nNew package rank=" + newPackageRank + "\nMonthly package rank=" + monthlyPackageRank
                + "\nFirst login=" + convertUnix(firstLogin) + "\nLast login=" + convertUnix(lastLogin)+ "\nLast logout=" + convertUnix(lastLogout);
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getDisplayname() {
        return displayname;
    }
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getPackageRank() {
        return packageRank;
    }
    public void setPackageRank(String packageRank) {
        this.packageRank = packageRank;
    }
    public String getNewPackageRank() {
        return newPackageRank;
    }
    public void setNewPackageRank(String newPackageRank) {
        this.newPackageRank = newPackageRank;
    }
    public String getMonthlyPackageRank() {
        return monthlyPackageRank;
    }
    public void setMonthlyPackageRank(String monthlyPackageRank) {
        this.monthlyPackageRank = monthlyPackageRank;
    }
    public long getFirstLogin() {
        return firstLogin;
    }
    public void setFirstLogin(long firstLogin) {
        this.firstLogin = firstLogin;
    }
    public long getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }
    public long getLastLogout() {
        return lastLogout;
    }
    public void setLastLogout(long lastLogout) {
        this.lastLogout = lastLogout;
    }
    public Object getStats() {
        return stats;
    }
    public void setStats(Object stats) {
        this.stats = stats;
    }


    
}
