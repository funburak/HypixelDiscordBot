package com.hypixeldiscordbot.HypixelData.SkyblockData;

import java.util.List;

public class Election {
    private int year;
    private List<Candidate> candidates;

    @Override
    public String toString() {
        return "Election [year=" + year + "\ncandidates=" + candidates + "]";
    }

    
}
