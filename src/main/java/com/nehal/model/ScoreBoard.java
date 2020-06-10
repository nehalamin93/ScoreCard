package com.nehal.model;

import com.nehal.factory.DeliveryOutputFactory;
import com.nehal.interfaces.ManageBatsman;
import com.nehal.interfaces.ManageScoreBoard;
import com.nehal.model.output.DeliveryOutput;
import com.nehal.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.nehal.constants.Constants.*;

public class ScoreBoard implements ManageScoreBoard {
    private Batsman[] battingOrder;
    private BufferedReader br;
    private Batsman onStrike;
    private Batsman offStrike;
    private Team battingTeam;
    private Team bowlingTeam;
    private boolean gameOver;
    private Integer inningNumber;
    private Integer totalOvers;
    private Integer currOver;
    private Integer currDelivery;

    public ScoreBoard(Team battingTeam, Team bowlingTeam, Integer inningNumber, Integer totalOvers) {
        br = new BufferedReader(new InputStreamReader(System.in));
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.inningNumber = inningNumber;
        this.gameOver = false;
        this.totalOvers = totalOvers;
    }

    public void setupScoreBoard() throws IOException {
        setUpBattingOrder();
    }

    private void setUpBattingOrder() throws IOException {
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

    public void play() throws IOException {
        for(int overCount=1; overCount<=this.totalOvers; overCount++){
            System.out.println("Over " + overCount + ":");
            deliverOver();
            if(this.currDelivery.equals(BALLS_IN_OVER)) {
                this.currOver = overCount;
            }
            System.out.println("ScoreBoard: ");
            displayScoreBoard();
            if(gameOver) {
                return;
            }
        }
    }

    public void displayScoreBoard() {
        System.out.println("ScoreBoard For Team: " + battingTeam.getName());
        System.out.println("PName Score 4s 6s Balls");
        for(int i=0; i<battingTeam.getPlayersCount(); i++) {
            ManageBatsman batsman = battingOrder[i];
            System.out.println(batsman.getName() + "    " + batsman.getScore() + "  " + batsman.getRunUnitVsCount(FOUR)
                    + "  " + batsman.getRunUnitVsCount(SIX) + "  " + batsman.getBalls());
        }
        System.out.println("Total: "+ battingTeam.getScore() + "/" + battingTeam.getWicketsDown());
        if(this.currDelivery.equals(BALLS_IN_OVER)) {
            System.out.println("Over: " + this.currOver);
        } else {
            System.out.println("Over: " + this.currOver + "." + this.currDelivery);
        }
    }

    private void deliverOver() throws IOException {
        for(int ballCount=0; ballCount<BALLS_IN_OVER; ballCount++) {
            String input =  this.getDeliveryOutput();
            DeliveryOutput deliveryOutput = DeliveryOutputFactory.getOutput(input);
            switch (deliveryOutput.getOutputType()) {
                case RUN:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    this.onStrike.updateScore(deliveryOutput.getScore());
                    if(deliveryOutput.getScore()%2 == 1) {
                        togglePlayers();
                    }
                    this.currDelivery = ballCount+1;
                    break;
                case NO_BALL:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    this.onStrike.updateScore(deliveryOutput.getScore()-1);
                    if(deliveryOutput.getScore()%2 == 1) {
                        togglePlayers();
                    }
                    this.currDelivery = ballCount;
                    ballCount -= 1;
                    break;
                case WIDE:
                    this.battingTeam.updateScore(deliveryOutput.getScore());
                    this.currDelivery = ballCount;
                    ballCount -= 1;
                    break;
                case WICKET:
                    this.battingTeam.updateWicketsDown();
                    this.onStrike.updateScore(deliveryOutput.getScore());
                    this.currDelivery = ballCount+1;
                    if(!getInningStatus()) {
                        return;
                    }
                    this.onStrike = this.battingOrder[this.battingTeam.getWicketsDown()+1];
                    break;
                default:
                    break;
            }
            if(inningNumber == 2 && battingTeam.getScore() > bowlingTeam.getScore()) {
                this.gameOver = true;
                return;
            }
        }
        if(!this.gameOver) {
            togglePlayers();
        }
    }

    private void togglePlayers() {
        Batsman p = onStrike;
        onStrike = offStrike;
        offStrike = p;
    }

    private String getDeliveryOutput() throws IOException {
        String deliveryOutput = br.readLine();
        while(!Validation.validateDeliveryOutput(deliveryOutput)) {
            System.out.println("Invalid Input, Retry!");
            deliveryOutput = br.readLine();
        }
        return deliveryOutput;
    }

    private boolean getInningStatus() {
        return !battingTeam.getWicketsDown().equals(battingTeam.getPlayersCount()-1);
    }
}
