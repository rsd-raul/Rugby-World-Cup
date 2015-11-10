package com.rsd_raul.r00143615.rugbyworldcup.controllers;/*
 * Created by rsd_raul on 11/9/15.
 */

import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.Toast;

import com.rsd_raul.r00143615.rugbyworldcup.Knockout;
import com.rsd_raul.r00143615.rugbyworldcup.R;
import com.rsd_raul.r00143615.rugbyworldcup.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WRCController {

    Knockout activity;

    public WRCController(Knockout activity) {
        this.activity = activity;
    }

    public boolean isValidTeam() {
        EditText editText = (EditText) activity.getCurrentFocus();
        Team actual = null;
        boolean result = false;

        if(editText != null){
            //  Check if it's among the allowed Teams
            for(Team aux : Team.values())
                if(aux.getName().equals(editText.getText().toString()))
                    actual = aux;

            //  If exists change background and check the rest for unlocking the button
            if(actual != null) {

                editText.setBackgroundColor(ContextCompat.getColor(activity, actual.getColor()));
                editText.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
                allTeamsValid();
                result = true;
                //  If not, go to default
            }else{
                editText.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.white));
                editText.setTextColor(ContextCompat.getColor(activity, android.R.color.black));
                activity.toggleButton(false);
            }
        }

        return result;
    }

    public boolean isValidOnParents(){
        EditText editText = (EditText) activity.getCurrentFocus();
        Team actual = activity.getTeam(editText);
        boolean result = true;
        Team daddy;
        Team mommy;

        switch (editText.getId()){
            case R.id.semi1:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.quarter1));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.quarter2));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.semi2:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.quarter3));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.quarter4));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.semi3:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.quarter5));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.quarter6));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.semi4:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.quarter7));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.quarter8));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.final1:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.semi1));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.semi2));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.final2:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.semi3));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.semi4));

                result = testParent(daddy, mommy, actual);
                break;
            case R.id.winner:
                daddy = activity.getTeam((EditText) activity.findViewById(R.id.final1));
                mommy = activity.getTeam((EditText) activity.findViewById(R.id.final2));

                result = testParent(daddy, mommy, actual);
                break;
        }

        return result;
    }

    public boolean testParent(Team daddy, Team mommy, Team actual) {
        Boolean result = true;
        if(actual != daddy && actual != mommy) {
            Toast.makeText(activity, R.string.wrongParent, Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }

    public void allTeamsValid() {
        List<Team> teams = activity.getTypedTeams();

        //  Duplicate detection
        List<Team> teamsCopy = new ArrayList<>(teams);
        teamsCopy.removeAll(Collections.singleton(null));
        Set<Team> setTeams = new HashSet<>(teamsCopy);
        Boolean duplicates = teamsCopy.size() != setTeams.size();

        if(duplicates) {
            Toast.makeText(activity, R.string.duplicatedTeam, Toast.LENGTH_SHORT).show();
        }else if(teams.contains(null) || duplicates)
            activity.toggleButton(false);
        else
            activity.toggleButton(true);
    }


}
