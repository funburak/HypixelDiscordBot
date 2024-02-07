package com.hypixeldiscordbot.HypixelData.SkyblockData;

import java.util.List;

public class Mayor {
    private String key;
    private String name;
    private List<Perks> perks;

    @Override
    public String toString() {
        return "name=" + name + "\nperks=" + perks;
    }

    private class Perks{
        private String name;
        private String description;
        @Override
        public String toString() {
            return "Perks [name=" + name + "description=" + description + "]";
        }
    }
}
