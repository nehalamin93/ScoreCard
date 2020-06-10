package com.nehal.engine;

import com.nehal.model.ScoreBoard;
import com.nehal.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.nehal.constants.Constants.TOTAL_INNINGS;

public class GameEngine {
    private Team battingTeam;
    private Team bowlingTeam;
    private Integer overs;
    private ScoreBoard[] innings;
    private BufferedReader br;
    private Integer inningsCount;

    public GameEngine(Team battingTeam, Team bowlingTeam, Integer overs) {
        this.battingTeam = battingTeam;
        this.bowlingTeam  = bowlingTeam;
        this.overs = overs;
        this.innings  = new ScoreBoard[TOTAL_INNINGS];
        this.inningsCount = 0;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        while(inningsCount < TOTAL_INNINGS) {
            this.initializeInning();
            ScoreBoard currentInning = this.innings[this.inningsCount-1];
            currentInning.play();
            this.toggleTeam();
        }
        this.decideWinner();
    }

    public void initializeInning() throws IOException {
        this.inningsCount++;
        this.innings[inningsCount-1] = new ScoreBoard(this.battingTeam, this.bowlingTeam, this.inningsCount, this.overs);
        this.innings[inningsCount-1].setupScoreBoard();
    }

    public void toggleTeam() {
        Team team = this.battingTeam;
        this.battingTeam = bowlingTeam;
        this.bowlingTeam = team;
    }

    public void decideWinner() {
        if(this.battingTeam.getScore() > this.bowlingTeam.getScore()) {
            System.out.println("Result: " + this.battingTeam.getName() + " won the match by "
                    + (this.battingTeam.getScore() - this.bowlingTeam.getScore()) + " runs");
        } else if (this.battingTeam.getScore() < this.bowlingTeam.getScore()){
            System.out.println("Result: " + this.bowlingTeam.getName() + " won the match by "
                    + (this.bowlingTeam.getPlayersCount()-this.bowlingTeam.getWicketsDown()-1) + " wickets");
        } else {
            System.out.println("Result: Tie ");
        }
    }
}
