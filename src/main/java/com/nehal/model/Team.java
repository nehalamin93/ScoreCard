package com.nehal.model;

public class Team {
    private String name;
    private Integer playersCount;
    private Integer score;
    private Integer wicketsDown;

    public Team(String name, Integer playersCount) {
        this.name = name;
        this.playersCount = playersCount;
        score = 0;
        wicketsDown = 0;
    }

    public void updateScore(Integer run) {
        score += run;
    }

    public void updateWicketsDown() {
        wicketsDown += 1;
    }

    public Integer getPlayersCount() {
        return playersCount;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getWicketsDown() {
        return wicketsDown;
    }

    public String getName() {
        return name;
    }
}
