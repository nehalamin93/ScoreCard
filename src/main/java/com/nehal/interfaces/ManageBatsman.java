package com.nehal.interfaces;

public interface ManageBatsman {
    Integer getScore();
    Integer getBalls();
    Integer getRunUnitVsCount(Integer unit);
    String getName();
    void updateScore(Integer run);
}
