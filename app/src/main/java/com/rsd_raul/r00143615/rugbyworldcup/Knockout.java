package com.rsd_raul.r00143615.rugbyworldcup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.rsd_raul.r00143615.rugbyworldcup.controllers.WRCController;
import com.rsd_raul.r00143615.rugbyworldcup.domain.Series;
import com.rsd_raul.r00143615.rugbyworldcup.domain.Team;
import com.rsd_raul.r00143615.rugbyworldcup.listeners.MyEditTextListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Knockout extends AppCompatActivity {

    public static EditText quarter1, quarter2, quarter3, quarter4, quarter5, quarter6, quarter7, quarter8;
    public static EditText semi1, semi2, semi3, semi4;
    public static EditText final1, final2;
    public static EditText winner;
    Series series;
    private Long timer;
    private WRCController wrcController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);

        //  Show timer in case android kills the process or we switch to landscape
        if(timer == null)
            timer = inState.getLong("value");
        showElapsedTime(timer);

        //  Repaint interface in case a correct team is introduced
        repaint();

        //  Turn the button visible if all teams in quarter are valid
        if(wrcController == null)
            wrcController = new WRCController(this);
        wrcController.allTeamsValid();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //  Show timer
        if(timer != null)
            showElapsedTime(timer);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //  Save the current time
        timer = Calendar.getInstance().getTimeInMillis();
        outState.putLong("value", timer);
    }

    //  Show the time that the app has passed on the background
    private void showElapsedTime(Long timer){
        Long elapsedTime = System.currentTimeMillis() - timer;
        String text = getResources().getString(R.string.elapsedTime);

        Toast.makeText(this, text + elapsedTime/1000, Toast.LENGTH_LONG).show();
    }

    //  Play a random serie
    public void randomPlay(View v){

        List<Team> classified = new ArrayList<>();

        classified.addAll(getTypedTeams());

        series = new Series(classified);
        series.runSeries();

        publishResults();
    }

    //  Capture the 8 teams that will play the series
    public List<Team> getTypedTeams(){
        List<Team> result = new ArrayList<>();

        result.add(getTeam(quarter1));
        result.add(getTeam(quarter2));
        result.add(getTeam(quarter3));
        result.add(getTeam(quarter4));
        result.add(getTeam(quarter5));
        result.add(getTeam(quarter6));
        result.add(getTeam(quarter7));
        result.add(getTeam(quarter8));

        return result;
    }

    //  Get a team from the text inside an EditText
    public Team getTeam(EditText editText){
        Team actual = null;

        for(Team aux : Team.values())
            if(aux.getName().equals(editText.getText().toString()))
                actual = aux;

        return actual;
    }

    //  Set the random generator button to visible or invisible
    public void toggleButton(Boolean status){
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.warningText);

        //  If all teams valid, make button visible
        if(status) {
            button.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);

        //  If not, show warning
        }else{
            button.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    //  Publish the series results
    public void publishResults() {

        List<Team> quarterWinners = series.getQuarterWinners();
        List<Team> semiWinners = series.getSemiWinners();
        Team winnerSeries = series.getWinner();

        semi1.setText(quarterWinners.get(0).getName());
        semi1.setTextColor(Color.WHITE);
        semi1.setBackgroundColor(ContextCompat.getColor(this, quarterWinners.get(0).getColor()));
        semi2.setText(quarterWinners.get(1).getName());
        semi2.setTextColor(Color.WHITE);
        semi2.setBackgroundColor(ContextCompat.getColor(this, quarterWinners.get(1).getColor()));
        semi3.setText(quarterWinners.get(2).getName());
        semi3.setTextColor(Color.WHITE);
        semi3.setBackgroundColor(ContextCompat.getColor(this, quarterWinners.get(2).getColor()));
        semi4.setText(quarterWinners.get(3).getName());
        semi4.setTextColor(Color.WHITE);
        semi4.setBackgroundColor(ContextCompat.getColor(this, quarterWinners.get(3).getColor()));

        final1.setText(semiWinners.get(0).getName());
        final1.setTextColor(Color.WHITE);
        final1.setBackgroundColor(ContextCompat.getColor(this, semiWinners.get(0).getColor()));
        final2.setText(semiWinners.get(1).getName());
        final2.setTextColor(Color.WHITE);
        final2.setBackgroundColor(ContextCompat.getColor(this, semiWinners.get(1).getColor()));

        winner.setText(winnerSeries.getName());
        winner.setTextColor(Color.WHITE);
        winner.setBackgroundColor(ContextCompat.getColor(this, winnerSeries.getColor()));
    }

    //  Repaint interface when the activity is destroyed and created
    public void repaint() {

        Team onQuarter1 = getTeam(quarter1);
        if(onQuarter1!=null) {
            quarter1.setTextColor(Color.WHITE);
            quarter1.setBackgroundColor(ContextCompat.getColor(this, onQuarter1.getColor()));
        }
        Team onQuarter2 = getTeam(quarter2);
        if(onQuarter2!=null) {
            quarter2.setTextColor(Color.WHITE);
            quarter2.setBackgroundColor(ContextCompat.getColor(this, onQuarter2.getColor()));
        }
        Team onQuarter3 = getTeam(quarter3);
        if(onQuarter3!=null) {
            quarter3.setTextColor(Color.WHITE);
            quarter3.setBackgroundColor(ContextCompat.getColor(this, onQuarter3.getColor()));
        }
        Team onQuarter4 = getTeam(quarter4);
        if(onQuarter4!=null) {
            quarter4.setTextColor(Color.WHITE);
            quarter4.setBackgroundColor(ContextCompat.getColor(this, onQuarter4.getColor()));
        }
        Team onQuarter5 = getTeam(quarter5);
        if(onQuarter5!=null) {
            quarter5.setTextColor(Color.WHITE);
            quarter5.setBackgroundColor(ContextCompat.getColor(this, onQuarter5.getColor()));
        }
        Team onQuarter6 = getTeam(quarter6);
        if(onQuarter6!=null) {
            quarter6.setTextColor(Color.WHITE);
            quarter6.setBackgroundColor(ContextCompat.getColor(this, onQuarter6.getColor()));
        }
        Team onQuarter7 = getTeam(quarter7);
        if(onQuarter7!=null) {
            quarter7.setTextColor(Color.WHITE);
            quarter7.setBackgroundColor(ContextCompat.getColor(this, onQuarter7.getColor()));
        }
        Team onQuarter8 = getTeam(quarter8);
        if(onQuarter8!=null) {
            quarter8.setTextColor(Color.WHITE);
            quarter8.setBackgroundColor(ContextCompat.getColor(this, onQuarter8.getColor()));
        }

        Team onSemi1 = getTeam(semi1);
        if(onSemi1!=null) {
            semi1.setTextColor(Color.WHITE);
            semi1.setBackgroundColor(ContextCompat.getColor(this, onSemi1.getColor()));
        }
        Team onSemi2 = getTeam(semi2);
        if(onSemi2!=null) {
            semi2.setTextColor(Color.WHITE);
            semi2.setBackgroundColor(ContextCompat.getColor(this, onSemi2.getColor()));
        }
        Team onSemi3 = getTeam(semi3);
        if(onSemi3!=null) {
            semi3.setTextColor(Color.WHITE);
            semi3.setBackgroundColor(ContextCompat.getColor(this, onSemi3.getColor()));
        }
        Team onSemi4 = getTeam(semi4);
        if(onSemi4!=null) {
            semi4.setTextColor(Color.WHITE);
            semi4.setBackgroundColor(ContextCompat.getColor(this, onSemi4.getColor()));
        }

        Team onFinal1 = getTeam(final1);
        if(onFinal1!=null) {
            final1.setTextColor(Color.WHITE);
            final1.setBackgroundColor(ContextCompat.getColor(this, onFinal1.getColor()));
        }
        Team onFinal2 = getTeam(final2);
        if(onFinal2!=null) {
            final2.setTextColor(Color.WHITE);
            final2.setBackgroundColor(ContextCompat.getColor(this, onFinal2.getColor()));
        }

        Team onWinner = getTeam(winner);
        if(onWinner!=null) {
            winner.setTextColor(Color.WHITE);
            winner.setBackgroundColor(ContextCompat.getColor(this, onWinner.getColor()));
        }
    }

    private void addListeners(){

        MyEditTextListener myEditTextListener = new MyEditTextListener(this);

        quarter1 = (EditText) findViewById(R.id.quarter1);
        quarter1.addTextChangedListener(myEditTextListener);
        quarter2 = (EditText) findViewById(R.id.quarter2);
        quarter2.addTextChangedListener(myEditTextListener);
        quarter3 = (EditText) findViewById(R.id.quarter3);
        quarter3.addTextChangedListener(myEditTextListener);
        quarter4 = (EditText) findViewById(R.id.quarter4);
        quarter4.addTextChangedListener(myEditTextListener);
        quarter5 = (EditText) findViewById(R.id.quarter5);
        quarter5.addTextChangedListener(myEditTextListener);
        quarter6 = (EditText) findViewById(R.id.quarter6);
        quarter6.addTextChangedListener(myEditTextListener);
        quarter7 = (EditText) findViewById(R.id.quarter7);
        quarter7.addTextChangedListener(myEditTextListener);
        quarter8 = (EditText) findViewById(R.id.quarter8);
        quarter8.addTextChangedListener(myEditTextListener);

        semi1 = (EditText) findViewById(R.id.semi1);
        semi1.addTextChangedListener(myEditTextListener);
        semi2 = (EditText) findViewById(R.id.semi2);
        semi2.addTextChangedListener(myEditTextListener);
        semi3 = (EditText) findViewById(R.id.semi3);
        semi3.addTextChangedListener(myEditTextListener);
        semi4 = (EditText) findViewById(R.id.semi4);
        semi4.addTextChangedListener(myEditTextListener);

        final1 = (EditText) findViewById(R.id.final1);
        final1.addTextChangedListener(myEditTextListener);
        final2 = (EditText) findViewById(R.id.final2);
        final2.addTextChangedListener(myEditTextListener);

        winner = (EditText) findViewById(R.id.winner);
        winner.addTextChangedListener(myEditTextListener);
    }
}