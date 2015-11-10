package com.rsd_raul.r00143615.rugbyworldcup.domain;/*
 * Created by rsd_raul on 10/31/15.
 */

import com.rsd_raul.r00143615.rugbyworldcup.R;

public enum Team {
    Australia("AUS", R.color.AUS),
    Wales("WAL", R.color.WAL),
    England("ENG", R.color.ENG),
    Fiji("FJI", R.color.FJI),
    Uruguay("URU", R.color.URU),

    SouthAfrica("RSA", R.color.RSA),
    Scotland("SCO", R.color.SCO),
    Japan("JPN", R.color.JPN),
    Samoa("SAM", R.color.SAM),
    UnitedStatesOfAmerica("USA", R.color.USA),

    NewZealand("NZL", R.color.NZL),
    Argentina("ARG", R.color.ARG),
    Georgia("GEO", R.color.GEO),
    Tonga("TGA", R.color.TGA),
    Nambia("NAM", R.color.NAM),

    Ireland("IRE", R.color.IRE),
    France("FRA", R.color.FRA),
    Italy("ITA", R.color.ITA),
    Romania("ROM", R.color.ROM),
    Canada("CAN", R.color.CAN),;


    private String name;
    private int color;

    Team(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
