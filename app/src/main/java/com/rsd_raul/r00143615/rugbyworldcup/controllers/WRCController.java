package com.rsd_raul.r00143615.rugbyworldcup.controllers;/*
 * Created by rsd_raul on 11/9/15.
 */

import android.widget.EditText;

import com.rsd_raul.r00143615.rugbyworldcup.Knockout;
import com.rsd_raul.r00143615.rugbyworldcup.domain.Team;

public class WRCController {

    Knockout activity;

    public WRCController(Knockout activity) {
        this.activity = activity;
    }

    private boolean isValidTeam() {
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
                editText.setBackgroundColor(activity.getResources().getColor(actual.getColor()));
                editText.setTextColor(activity.getResources().getColor(android.R.color.white));
                allTeamsValid();
                result = true;
                //  If not, go to default
            }else{
                editText.setBackgroundColor(activity.getResources().getColor(android.R.color.white));
                editText.setTextColor(activity.getResources().getColor(android.R.color.black));
                activity.toggleButton(false);
            }
        }

        return result;
    }
}
