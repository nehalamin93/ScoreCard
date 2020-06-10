package com.nehal.model;

import com.nehal.factory.DeliveryOutputFactory;
import com.nehal.model.output.DeliveryOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.nehal.constants.Constants.*;

public class ScoreBoard {
    private Batsman[] battingOrder;
    private BufferedReader br;
    private Batsman onStrike;
    private Batsman offStrike;
    private Team battingTeam;
    private Team bowlingTeam;
    private boolean gameOver;
    private Integer inningNumber;

    public ScoreBoard(Team battingTeam, Team bowlingTeam, Integer inningNumber) {
        br = new BufferedReader(new InputStreamReader(System.in));
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.inningNumber = inningNumber;
        this.gameOver = false;
    }

    public void setupScoreBoard() throws IOException {
        setUpBattingOrder();
    }

    public void setUpBattingOrder() throws IOException {
        System.out.println("Batting order for " + battingTeam.getName() + ":");
        Integer playersCount = this.battingTeam.getPlayersCount();
        this.battingOrder = new Batsman[playersCount];
        for(int i=0; i<playersCount; i++) {
            String input = br.readLine().trim();
            battingOrder[i] = new Batsman(input);
        }
        this.onStrike = battingOrder[0];
        this.offStrike = battingOrder[1];
    }

    public void play(Integer overs) throws IOException {
        while(overs>0) {
            System.out.println("Over: ");
            deliverOver();
            System.out.println("ScoreBoard: ");
            displayScoreBoard();
            overs -= 1;
            if(gameOver) {
                return;
            }
        }
    }

    public void displayScoreBoard() {
        System.out.println("ScoreBoard For Team: " + battingTeam.getName());
        System.out.println("PName Score 4s 6s Balls");
        for(int i=0; i<battingTeam.getPlayersCount(); i++) {
            Batsman batsman = battingOrder[i];
            System.out.println(batsman.name + "    " + batsman.getScore() + "  " + batsman.getUnitCount(FOUR)
                    + "  " + batsman.getUnitCount(SIX) + "  " + batsman.getBalls());
        }
        System.out.println("Total: "+ battingTeam.getScore());
        System.out.println("Over: ");
    }

    private int deliverOver() throws IOException {
        for(int ballCount=0; ballCount<BALLS_IN_OVER; ballCount++) {
            String input =  br.readLine();
            DeliveryOutput deliveryOutput = DeliveryOutputFactory.getOutput(input);
            switch (deliveryOutput.getOutputType()) {
                case RUN:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    this.onStrike.updateScore(deliveryOutput.getScore());
                    if(deliveryOutput.getScore()%2 == 1) {
                        togglePlayers();
                    }
                    break;
                case NO_BALL:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    this.onStrike.updateScore(deliveryOutput.getScore()-1);
                    if(deliveryOutput.getScore()%2 == 1) {
                        togglePlayers();
                    }
                    ballCount -= 1;
                    break;
                case WIDE:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    ballCount -= 1;
                    break;
                case WICKET:
                    this.battingTeam.updateWicketsDown();
                    this.onStrike.updateScore(deliveryOutput.getScore());
                    if(!getInningStatus()) {
                        return ballCount+1;
                    }
                    this.onStrike = this.battingOrder[this.battingTeam.getWicketsDown()+1];
                    break;
                default:
                    break;
            }
            if(inningNumber == 2 && battingTeam.getScore() > bowlingTeam.getScore()) {
                this.gameOver = true;
                break;
            }
        }
        if(!this.gameOver) {
            togglePlayers();
        }
        return BALLS_IN_OVER;
    }

    private void togglePlayers() {
        Batsman p = onStrike;
        onStrike = offStrike;
        offStrike = p;
    }

    private boolean getInningStatus() {
        return !battingTeam.getWicketsDown().equals(battingTeam.getPlayersCount()-1);
    }
}
