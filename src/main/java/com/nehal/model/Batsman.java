package com.nehal.model;

import java.util.Arrays;

public class Batsman extends Player {

    private Integer score;
    private Integer[] runUnit;
    private Integer balls;

    public Batsman(String name) {
        super(name);
        this.runUnit = new Integer[7];
        this.balls =  0;
        this.score = 0;
        Arrays.fill(runUnit, 0);
    }

    public void updateScore(Integer run) {
        this.runUnit[run] += 1;
        this.balls += 1;
        this.score += run;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer getBalls() {
        return this.balls;
    }

    public Integer getUnitCount(Integer unit) {
        return this.runUnit[unit];
    }
}
