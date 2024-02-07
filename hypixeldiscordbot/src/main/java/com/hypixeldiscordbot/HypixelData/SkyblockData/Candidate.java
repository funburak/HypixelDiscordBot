package com.hypixeldiscordbot.HypixelData.SkyblockData;

import java.util.List;

public class Candidate {
    private String name;
    private List<Perks> perks;
    private long votes;

    
    @Override
    public String toString() {
        return "Candidate [name=" + name + "\nperks=" + perks + "\nvotes=" + votes + "]\n";
    }

    private class Perks{
        private String name;
        private String description;

        @Override
        public String toString() {
            return "Perks [name=" + name + "\ndescription=" + description + "]";
        }

        
    }
}
