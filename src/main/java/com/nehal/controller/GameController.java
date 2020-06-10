package com.nehal.controller;

import com.nehal.engine.GameEngine;
import com.nehal.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
    private GameEngine gameEngine;

    private BufferedReader br;

    public void start() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("No. of players for each team: ");
        String playerCount = br.readLine();

        Team team1 = new Team("Team 1", Integer.parseInt(playerCount));
        Team team2 = new Team("Team 2", Integer.parseInt(playerCount));

        System.out.println("No. of overs: ");
        String overs = br.readLine();

        gameEngine = new GameEngine(team1, team2, Integer.parseInt(overs));
        gameEngine.start();
    }
}
