package com.rsd_raul.r00143615.rugbyworldcup.domain;
/*
 * Created by rsd_raul on 10/21/15.
 */

import java.util.ArrayList;
import java.util.List;

public class Series {

    private List<Team> classified;
    private List<Team> quarterWinners;
    private List<Team> semiWinners;
    private Team winner;

    public Series(List<Team> classified) {
        this.classified = classified;

        quarterWinners = new ArrayList<>();
        semiWinners = new ArrayList<>();
    }

    public void runSeries(){
        runQuarters();
        runSemis();
        runFinal();
//        Knockout.publishResults(quarterWinners, semiWinners, winner);
    }

    private void runQuarters(){
        int cont = 0;

        while(cont < classified.size()){
            Match aux = new Match(classified.get(cont), classified.get(cont+1));
            quarterWinners.add(aux.playMatch());
            cont += 2;
        }
    }

    private void runSemis(){
        int cont = 0;

        while(cont < quarterWinners.size()){
            Match aux = new Match(quarterWinners.get(cont), quarterWinners.get(cont+1));
            semiWinners.add(aux.playMatch());
            cont += 2;
        }
    }

    private void runFinal(){
        Match aux = new Match(semiWinners.get(0), semiWinners.get(1));
        winner = aux.playMatch();
    }

    public List<Team> getQuarterWinners() {
        return quarterWinners;
    }

    public List<Team> getSemiWinners() {
        return semiWinners;
    }

    public Team getWinner() {
        return winner;
    }
}