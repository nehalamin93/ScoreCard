package com.nehal.model;

import com.nehal.constants.BatsmanStatus;
import com.nehal.interfaces.ManageBatsman;

import java.util.Arrays;

public class Batsman extends Player implements ManageBatsman {

    private Integer score;
    private Integer[] runUnit;
    private Integer balls;

    private BatsmanStatus status;

    public Batsman(String name) {
        super(name);
        this.runUnit = new Integer[20];
        this.balls =  0;
        this.score = 0;
        Arrays.fill(runUnit, 0);
        this.status = BatsmanStatus.NOT_PLAYED;
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

    public Integer getRunUnitVsCount(Integer unit) {
        return this.runUnit[unit];
    }

    public BatsmanStatus getStatus() {
        return status;
    }

    public void setStatus(BatsmanStatus status) {
        this.status = status;
    }
}
