package com.hypixeldiscordbot.HypixelData.SkyblockData;

import java.util.List;

public class MuseumProfile {
    private int value;
    private boolean appraisal;
    private Object items;
    private List<Object> special;


    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public boolean isAppraisal() {
        return appraisal;
    }
    public void setAppraisal(boolean appraisal) {
        this.appraisal = appraisal;
    }
    public Object getItems() {
        return items;
    }
    public void setItems(Object items) {
        this.items = items;
    }
    public List<Object> getSpecial() {
        return special;
    }
    public void setSpecial(List<Object> special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "value=" + value + ", appraisal=" + appraisal + ", items=" + items + ", special="
                + special;
    }

    

    
}
