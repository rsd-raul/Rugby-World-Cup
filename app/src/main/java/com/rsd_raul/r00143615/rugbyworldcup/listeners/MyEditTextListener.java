package com.rsd_raul.r00143615.rugbyworldcup.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import com.rsd_raul.r00143615.rugbyworldcup.Knockout;
import com.rsd_raul.r00143615.rugbyworldcup.controllers.WRCController;

/*
 * Created by rsd_raul on 10/14/15.
 */

public class MyEditTextListener implements TextWatcher {

    WRCController wrcController;

    public MyEditTextListener(Knockout activity) {
//        this.activity = activity;
        wrcController = new WRCController(activity);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {    }

    @Override
    public void afterTextChanged(Editable s) {

        if(wrcController.isValidTeam())
            if(wrcController.isValidOnParents())
                wrcController.allTeamsValid();
    }

}
