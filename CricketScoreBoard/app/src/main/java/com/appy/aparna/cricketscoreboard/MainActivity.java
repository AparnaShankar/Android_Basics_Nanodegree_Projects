package com.appy.aparna.cricketscoreboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA=0;
    int scoreTeamB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Reset scores for both teams
     */
    public void resetScore(View v)
    {
        scoreTeamA=0;
        scoreTeamB=0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
    /**
     To add 6 runs for Team A
     */
    public void addSixForTeamA(View v)
    {
        scoreTeamA+=6;
        displayForTeamA(scoreTeamA);
    }
    /**
     To add 4 runs for Team A
     */
    public void addFourForTeamA(View v)
    {
        scoreTeamA+=4;
        displayForTeamA(scoreTeamA);
    }
    /**
     To add 2 runs for Team A
     */
    public void addTwoForTeamA(View v)
    {
        scoreTeamA+=2;
        displayForTeamA(scoreTeamA);
    }
    /**
     To add 1 run for Team A
     */
    public void addOneForTeamA(View v)
    {
        scoreTeamA+=1;
        displayForTeamA(scoreTeamA);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     To add 6 runs for Team B
     */
    public void addSixForTeamB(View v)
    {
        scoreTeamB+=6;
        displayForTeamB(scoreTeamB);
    }
    /**
     To add 4 runs for Team B
     */
    public void addFourForTeamB(View v)
    {
        scoreTeamB+=4;
        displayForTeamB(scoreTeamB);
    }
    /**
     To add 2 runs for Team B
     */
    public void addTwoForTeamB(View v)
    {
        scoreTeamB+=2;
        displayForTeamB(scoreTeamB);
    }
    /**
     To add 1 run for Team B
     */
    public void addOneForTeamB(View v)
    {
        scoreTeamB+=1;
        displayForTeamB(scoreTeamB);
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
