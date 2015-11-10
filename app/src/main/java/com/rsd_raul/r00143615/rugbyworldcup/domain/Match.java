package com.rsd_raul.r00143615.rugbyworldcup.domain;/*
 * Created by rsd_raul on 10/21/15.
 */

public class Match {

    private Team team1, team2;

    public Match(Team team1, Team team2) {

        this.team1 = team1;
        this.team2 = team2;
    }

    public Team playMatch() {
        Double random = Math.random();

        return (random >= 0.5) ? team1 : team2;
    }
}